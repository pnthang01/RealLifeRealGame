package com.rlrg.dataserver.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.dto.ConfigDTO;
import com.rlrg.dataserver.entity.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, String>, JpaSpecificationExecutor<Config>{

	@Query("SELECT NEW com.rlrg.dataserver.dto.ConfigDTO (" +
			"c.key, c.value" +
			") FROM Config c WHERE c.key = :key")
	public ConfigDTO getConfigDTOByKey(@Param("key") String key);
	
	@Query("SELECT NEW com.rlrg.dataserver.dto.ConfigDTO (" +
			"c.key, c.value" +
			") FROM Config c WHERE c.key IN :keys")
	public List<ConfigDTO> getConfigDTOByKeys(@Param("keys") List<String> keys);
	
	@Query("SELECT c FROM Config c WHERE c.key IN :keys")
	public List<Config> getConfigsByKeys(@Param("keys") List<String> keys);
	
	@Query("SELECT NEW com.rlrg.dataserver.dto.ConfigDTO (" +
			"c.key, c.value" +
			") FROM Config c")
	public List<ConfigDTO> getAllConfig(Pageable pageable);
	
	@Query("SELECT COUNT(c) FROM Config c")
	public Long countAllConfig();
	
	@Query("SELECT NEW com.rlrg.dataserver.dto.ConfigDTO (" +
			"c.key, c.value" +
			") FROM Config c WHERE" +
			" c.key LIKE CONCAT('%', :keyword, '%')" +
			" OR c.value LIKE CONCAT('%', :keyword, '%')")
	public List<ConfigDTO> searchConfigsByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("SELECT COUNT(c) FROM Config c WHERE" +
			" c.key LIKE CONCAT('%', :keyword, '%')" +
			" OR c.value LIKE CONCAT('%', :keyword, '%')")
	public Long countConfigsByKeyword(@Param("keyword") String keyword);
	
}
