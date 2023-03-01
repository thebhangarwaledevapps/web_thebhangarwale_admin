package com.app.admin.datasource.local;

import com.app.admin.datasource.local.dao.BhangarTypeAndPriceDao;
import com.app.admin.datasource.local.dao.BhangarwaleConfigDao;
import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.entity.BhangarwaleConfig;
import com.app.admin.repository.RepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BhangarTypeAndPriceDataSourceTest {


    @Autowired
    BhangarwaleConfigDao bhangarwaleConfigDao;

    @Test
    public void test_bhangarwale_config(){
        bhangarwaleConfigDao.deleteAll();
        bhangarwaleConfigDao.save(new BhangarwaleConfig(
                "EAAxHBTDkA3UBAFW92ZBAMEqNuC5GZA7KAYozzCuQGqGJSs2XorY7ZBNJcJC4hxtWzyTtOprzBs0SyTo4wbZBZCSCne9DeP9lmaIB86ljmSaJcpeqPkSwLqo7ELzWB8E5ZAb8X0rnpAq0pZCrxji01I8vshZA58Qrwva2ckZBZAwjw8Y41wHDAZBXRtC"
        ));
    }


}