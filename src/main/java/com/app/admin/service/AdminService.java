package com.app.admin.service;

import com.app.admin.exception.*;
import com.app.admin.repository.Repository;
import com.app.admin.result.ClientError;
import com.app.admin.result.Result;
import com.app.admin.result.ServerError;
import com.app.admin.result.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private Repository repositoryImpl;

    public Result getBhangarwaleFacebookFeed(String nextPageAccessToken) {
        return repositoryImpl
                .getBhangarwaleFacebookFeed(nextPageAccessToken);
    }

    public Result saveBhangarTypeAndPrice(String bhangarType, String bhangarUnit, Double bhangarPrice) {
        try {
            if (bhangarType == null || bhangarType.isEmpty())
                throw new InvalidBhangarTypeException();
            if (bhangarUnit == null || bhangarUnit.isEmpty())
                throw new InvalidBhangarUnitException();
            if (bhangarPrice <= 0)
                throw new InvalidBhangarPriceException();
            return Optional.ofNullable(
                    repositoryImpl
                            .saveBhangarTypeAndPrice(getCapitalizeWord(bhangarType), bhangarUnit, bhangarPrice)
            ).map(bhangarTypeAndPrice -> new Success("Data Saved Successfully.")
            ).get();
        } catch (InvalidBhangarTypeException e) {
            return new ClientError(e.getMessage());
        } catch (InvalidBhangarUnitException e) {
            return new ClientError(e.getMessage());
        } catch (InvalidBhangarPriceException e) {
            return new ClientError(e.getMessage());
        }
    }

    public Result getBhangarList() {
        try {
            return repositoryImpl.getBhangarList();
        } catch (NoBhangarFoundException e){
            return new ServerError(e.getMessage());
        }
    }

    public Result getBhangarItemInfo(Long itemId) {
        try {
            return repositoryImpl.getBhangarItemInfo(itemId);
        } catch (InvalidBhangarItemIdException e) {
            return new ClientError(e.getMessage());
        }
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
}
