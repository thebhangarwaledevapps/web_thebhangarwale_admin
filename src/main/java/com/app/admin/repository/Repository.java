package com.app.admin.repository;

import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleFacebookFeed;
import com.app.admin.exception.InvalidBhangarItemIdException;
import com.app.admin.exception.NoBhangarFoundException;
import com.app.admin.result.Success;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface Repository {

    Success<BhangarwaleFacebookFeed> getBhangarwaleFacebookFeed(String nextPageAccessToken);

    BhangarTypeAndPrice saveBhangarTypeAndPrice(String bhangarType, String bhangarUnit, Double bhangarPrice);

    Success<List<BhangarTypeAndPrice>> getBhangarList() throws NoBhangarFoundException;

    Success<BhangarTypeAndPrice> getBhangarItemInfo(Long itemId) throws InvalidBhangarItemIdException;

}
