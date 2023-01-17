package com.app.admin.datasource.local;

import com.app.admin.entity.BhangarTypeAndPrice;
import com.app.admin.repository.RepositoryImpl;
import com.app.admin.result.Success;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BhangarTypeAndPriceDataSourceTest {

    @Autowired
    RepositoryImpl repository;

    @Autowired
    BhangarTypeAndPriceDataSource bhangarTypeAndPriceDataSource;

    @Test
    public void test_avoid_repeatation_of_bhangartype(){
        bhangarTypeAndPriceDataSource.deleteAll();

        repository.saveBhangarTypeAndPrice("Loha","kg",10.0);
        repository.saveBhangarTypeAndPrice("Loha","kg",40.0);

        BhangarTypeAndPrice bhangarTypeAndPrice = bhangarTypeAndPriceDataSource.findByBhangarType("Loha").get();
        Assertions.assertEquals(40.0,bhangarTypeAndPrice.getBhangarPrice());
    }

    @Test
    public void test_exception_if_bhangar_item_not_found(){
        bhangarTypeAndPriceDataSource.deleteAll();

        //final BhangarTypeAndPrice bhangarTypeAndPrice = repository.saveBhangarTypeAndPrice("Loha","kg",10.0);

        try {
            Success<BhangarTypeAndPrice> success =  repository.getBhangarItemInfo(2L);
            System.out.println(success);
        }catch (Exception exception){
            System.out.println(exception);
        }


    }

}