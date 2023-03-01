package com.app.admin.repository;

import com.app.admin.entity.BhangarwaleFacebookFeed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepositoryImplTest {

    @Autowired
    private Repository repositoryImpl;

    @Test
    void test_getBhangarwaleFacebookFeed(){
        try {
            Optional<BhangarwaleFacebookFeed> o = repositoryImpl.getBhangarwaleFacebookFeed(null);
            System.out.println(o);
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

}