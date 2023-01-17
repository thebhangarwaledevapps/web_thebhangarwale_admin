package com.app.admin.datasource.local;

import com.app.admin.entity.BhangarwaleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BhangarwaleConfigDataSource extends JpaRepository<BhangarwaleConfig, String> {

	@Query(nativeQuery=true, value="SELECT facebook_acess_token FROM bhangarwale_config")
	public String getFacebookAcessToken();

}
