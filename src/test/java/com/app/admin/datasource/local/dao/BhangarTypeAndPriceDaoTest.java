package com.app.admin.datasource.local.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BhangarTypeAndPriceDaoTest {

    @Autowired
    BhangarTypeAndPriceDao bhangarTypeAndPriceDao;

    @Test
    public void test_delete_bhangar_type_and_price() {
        try {
            Long result = bhangarTypeAndPriceDao.deleteByBhangarId(1L);
            System.out.println(result);
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

}