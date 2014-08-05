package com.rlrg.dataserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.entity.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, String>, JpaSpecificationExecutor<Config>{

	@Query("SELECT c FROM Config c WHERE c.key = :key")
	public Config getConfigByKey(@Param("key") String key);
	
	@Query("SELECT c FROM Config c WHERE c.key IN :keys")
	public List<Config> getConfigsByKeys(@Param("keys") List<String> keys);
}
