package com.app.admin.datasource.local;

import com.app.admin.datasource.local.dao.BhangarTypeAndPriceDao;
import com.app.admin.datasource.local.dao.BhangarwaleConfigDao;
import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseDataSourceImpl implements DatabaseDataSource {

    @Autowired
    private BhangarwaleConfigDao bhangarwaleConfigDao;

    @Autowired
    private BhangarTypeAndPriceDao bhangarTypeAndPriceDao;

    @Override
    public String getFacebookAccessToken() {
        return bhangarwaleConfigDao.getFacebookAcessToken();
    }

    @Override
    public BhangarwaleConfig saveBhangarwaleConfig(String facebookAccessToken) {
        return bhangarwaleConfigDao.save(new BhangarwaleConfig(facebookAccessToken));
    }

    @Override
    public boolean isBhangarTypeAvailable(String bhangarType) {
        return bhangarTypeAndPriceDao.existsByBhangarType(bhangarType);
    }

    @Override
    public BhangarTypeAndPrice saveBhangarTypeAndPrice(BhangarTypeAndPrice bhangarTypeAndPrice) {
        return bhangarTypeAndPriceDao.save(bhangarTypeAndPrice);
    }

    @Override
    public List<BhangarTypeAndPrice> findBhangarList() {
        return bhangarTypeAndPriceDao.findAll();
    }

    @Override
    public Optional<BhangarTypeAndPrice> getBhangarItemInfo(Long itemId) {
        return bhangarTypeAndPriceDao.findById(itemId);
    }

    @Override
    public boolean validatedAdminId(String adminId) {
        return false;
    }

    @Override
    public BhangarTypeAndPrice findBhangarType(Long bhangarId, String bhangarType) {
        return bhangarTypeAndPriceDao.findByBhangarIdAndBhangarType(bhangarId,bhangarType);
    }

    @Override
    public Long deleteById(Long bhangarId) {
        return bhangarTypeAndPriceDao.deleteByBhangarId(bhangarId);
    }
}
