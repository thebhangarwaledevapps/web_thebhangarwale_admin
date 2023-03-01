package com.app.admin.datasource.local.dao;

import com.app.admin.entity.BhangarTypeAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BhangarTypeAndPriceDao extends JpaRepository<BhangarTypeAndPrice, Long> {

    boolean existsByBhangarType(String bhangarType);

    BhangarTypeAndPrice findByBhangarIdAndBhangarType(Long id, String bhangarType);

    //https://stackoverflow.com/questions/32269192/spring-no-entitymanager-with-actual-transaction-available-for-current-thread
    @Transactional
    long deleteByBhangarId(Long id);

    //boolean existsByToken(String token);
}
