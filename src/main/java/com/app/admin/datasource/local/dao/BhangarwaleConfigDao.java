package com.app.admin.datasource.local.dao;

import com.app.admin.entity.BhangarwaleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BhangarwaleConfigDao extends JpaRepository<BhangarwaleConfig, String> {

	@Query(nativeQuery=true, value="SELECT facebook_acess_token FROM bhangarwale_config")
	public String getFacebookAcessToken();

}
