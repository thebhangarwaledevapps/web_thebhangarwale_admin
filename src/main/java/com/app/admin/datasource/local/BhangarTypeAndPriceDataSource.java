package com.app.admin.datasource.local;

import com.app.admin.entity.BhangarTypeAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BhangarTypeAndPriceDataSource extends JpaRepository<BhangarTypeAndPrice, Long> {

    Optional<BhangarTypeAndPrice> findByBhangarType(String bhangarType);

    //boolean existsByToken(String token);
}
