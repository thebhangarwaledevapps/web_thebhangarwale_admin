package com.app.admin.datasource.local;

import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DatabaseDataSource {

    String getFacebookAccessToken();

    BhangarwaleConfig saveBhangarwaleConfig(String facebookAccessToken);

    boolean isBhangarTypeAvailable(String bhangarType);

    BhangarTypeAndPrice saveBhangarTypeAndPrice(BhangarTypeAndPrice bhangarTypeAndPrice);

    List<BhangarTypeAndPrice> findBhangarList();

    Optional<BhangarTypeAndPrice> getBhangarItemInfo(Long itemId);

    boolean validatedAdminId(String adminId);

    BhangarTypeAndPrice findBhangarType(Long bhangarId, String bhangarType);

    Long deleteById(Long bhangarId);
}
