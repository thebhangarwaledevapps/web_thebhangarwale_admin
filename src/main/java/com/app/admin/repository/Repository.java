package com.app.admin.repository;

import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.exception.InvalidBhangarItemIdException;
import com.app.admin.exception.NoBhangarFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface Repository {

    Optional<BhangarwaleFacebookFeed> getBhangarwaleFacebookFeed(String nextPageAccessToken);

    BhangarTypeAndPrice saveBhangarTypeAndPrice(String bhangarType, String bhangarUnit, Double bhangarPrice);

    Optional<List<BhangarTypeAndPrice>> getBhangarList();

    Optional<BhangarTypeAndPrice> getBhangarItemInfo(Long itemId);

    boolean validatedAdminId(String adminId);

    BhangarTypeAndPrice updateBhangarTypeAndPrice(Long bhangarId, String bhangarType, String bhangarUnit, Double bhangarPrice);

    Long deleteById(Long bhangarId);
}
