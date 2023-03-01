package com.app.admin.service;

import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.exception.*;
import com.app.admin.repository.Repository;
import com.app.admin.result.ClientError;
import com.app.admin.result.Result;
import com.app.admin.result.ServerError;
import com.app.admin.result.Success;
import com.app.admin.util.internationalization.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AdminService {

    @Autowired
    private Repository repositoryImpl;

    @Autowired
    private Translator translator;

    public Result getBhangarwaleFacebookFeed(String nextPageAccessToken) {
        return repositoryImpl.getBhangarwaleFacebookFeed(nextPageAccessToken)
                .map((Function<BhangarwaleFacebookFeed, Result<BhangarwaleFacebookFeed>>) Success::new)
                .orElse(new ServerError(null));
    }

    public Result saveBhangarTypeAndPrice(String bhangarType, String bhangarUnit, Double bhangarPrice) {
        Result result = null;
        try {
            if (bhangarType == null || bhangarType.isEmpty())
                throw new InvalidBhangarTypeException(translator.toLocale("error_valid_bhangar_type"));
            if (bhangarUnit == null || bhangarUnit.isEmpty())
                throw new InvalidBhangarUnitException(translator.toLocale("error_valid_bhangar_unit"));
            if (bhangarPrice <= 0)
                throw new InvalidBhangarPriceException(translator.toLocale("error_valid_bhangar_price"));
            result = Optional.ofNullable(repositoryImpl.saveBhangarTypeAndPrice(
                    getCapitalizeWord(bhangarType),
                    bhangarUnit,
                    bhangarPrice
            )).map((Function<BhangarTypeAndPrice, Result<String>>) bhangarTypeAndPrice -> new Success<>(
                    translator.toLocale("success_bhangar_save"))
            ).orElse(new ServerError(new Exception(translator.toLocale("error_something_went_wrong"))));
        } catch (InvalidBhangarTypeException | InvalidBhangarUnitException | InvalidBhangarPriceException e) {
            result = new ClientError(e);
        }
        return result;
    }

    public Result getBhangarList() {
        return repositoryImpl.getBhangarList()
                .map((Function<List<BhangarTypeAndPrice>, Result<List<BhangarTypeAndPrice>>>) Success::new)
                .orElse(new ServerError(new Exception(translator.toLocale("error_no_bhangar_found"))));
    }

    public Result getBhangarItemInfo(Long itemId) {
        return repositoryImpl.getBhangarItemInfo(itemId)
                .map((Function<BhangarTypeAndPrice, Result<BhangarTypeAndPrice>>) Success::new)
                .orElse(new ClientError(new InvalidBhangarItemIdException(translator.toLocale("error_valid_bhangar_id"))));
    }

    String getCapitalizeWord(String bhangarType) {
        try {
            String words[] = bhangarType.split("\\s");
            String capitalizeWord = "";
            for (String w : words) {
                String first = w.substring(0, 1);
                String afterfirst = w.substring(1);
                capitalizeWord += first.toUpperCase() + afterfirst + " ";
            }
            return capitalizeWord.trim();
        } catch (Exception exception) {
            return bhangarType;
        }
    }

    public boolean validatedAdminId(String adminId) {
        return repositoryImpl.validatedAdminId(adminId);
    }

    public Result updateBhangarTypeAndPrice(Long bhangarId, String bhangarType, String bhangarUnit, Double bhangarPrice) {
        Result result = null;
        try {
            if (bhangarType == null || bhangarType.isEmpty())
                throw new InvalidBhangarTypeException(translator.toLocale("error_valid_bhangar_type"));
            if (bhangarUnit == null || bhangarUnit.isEmpty())
                throw new InvalidBhangarUnitException(translator.toLocale("error_valid_bhangar_unit"));
            if (bhangarPrice <= 0)
                throw new InvalidBhangarPriceException(translator.toLocale("error_valid_bhangar_price"));
            result = Optional.ofNullable(repositoryImpl.updateBhangarTypeAndPrice(
                    bhangarId,
                    getCapitalizeWord(bhangarType),
                    bhangarUnit,
                    bhangarPrice
            )).map((Function<BhangarTypeAndPrice, Result<String>>) bhangarTypeAndPrice -> new Success<>(
                    translator.toLocale("success_bhangar_update"))
            ).orElse(new ServerError(new Exception(translator.toLocale("error_something_went_wrong"))));
        } catch (InvalidBhangarTypeException | InvalidBhangarUnitException | InvalidBhangarPriceException e) {
            result = new ClientError(e);
        }
        return result;
    }

    public Result deleteBhangarTypeAndPrice(Long bhangarId){
        return Optional.ofNullable(repositoryImpl.deleteById(bhangarId))
                .map((Function<Long, Result<String>>) aLong -> new Success<>(
                        translator.toLocale("success_bhangar_delete")))
                .orElse(new ServerError(new Exception(translator.toLocale("error_something_went_wrong"))));
    }
}
