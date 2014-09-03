package com.rlrg.dataserver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.dto.ConfigDTO;
import com.rlrg.dataserver.entity.Config;
import com.rlrg.dataserver.repository.ConfigRepository;
import com.rlrg.dataserver.utillities.Constants;

@Service
public class ConfigService extends BaseService<Config, ConfigDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigService.class); 
    
	@Autowired
	private ConfigRepository configRepo;
	
    private static Map<String, String> CONFIG = new HashMap<String, String>();

    /**
     * Load default configurations for system
     */
	private void loadConfig() {
		List<String> keys = new ArrayList<String>();
		keys.add(Constants.PAGE_SIZE_KEY);
		keys.add(Constants.TOTAL_STATISTIC_ROWS_KEY);
		keys.add(Constants.STATIC_RESOURCES_URI_KEY);
		//
		Map<String, String> configs = new HashMap<String, String>();
		List<Config> configEntity = configRepo.getConfigsByKeys(keys);
		for (Config config : configEntity) {
			configs.put(config.getKey(), config.getValue());
		}
		//
		synchronized (CONFIG) {
			CONFIG = configs;
		}
	}

	/**
	 * Get value of the config by key
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		if(null == CONFIG || CONFIG.isEmpty()){
			loadConfig();
		}
		return CONFIG.get(key);
	}

	/**
	 * Get all Config with paging
	 * @param pageNumber
	 * @return
	 */
	public List<ConfigDTO> getAllConfig(Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Integer.valueOf(this.getConfig(Constants.PAGE_SIZE_KEY)));
		//
		return configRepo.getAllConfig(pageRequest);
	}
	
	/**
	 * Count all config in database
	 * @return
	 */
	public Long countAllConfg(){
		return configRepo.countAllConfig();
	}
	
	/**
	 * Search config by keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	public List<ConfigDTO> searchConfigsByKeyword(String keyword, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Integer.valueOf(this.getConfig(Constants.PAGE_SIZE_KEY)));
		//
		return configRepo.searchConfigsByKeyword(keyword, pageRequest);
	}
	
	/**
	 * Count configs by keyword
	 * @param keyword
	 * @return
	 */
	public Long countConfigsByKeyword(String keyword){
		return configRepo.countConfigsByKeyword(keyword);
	}
	
	/**
	 * Return ConfigDTO by key
	 * @param key
	 * @return
	 */
	public ConfigDTO getConfigDTOByKey(String key) {
		return configRepo.getConfigDTOByKey(key);
	}

	/**
	 * Return list of ConfigDTO by keys
	 * @param keys
	 * @return
	 */
	public List<ConfigDTO> getConfigDTOByKeys(List<String> keys) {
		return configRepo.getConfigDTOByKeys(keys);
	}


	public Config revertDTOToEntity(Config dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConfigDTO convertEntityToDTO(Config data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Config revertDTOToEntity(ConfigDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<ConfigDTO> getVClass() {
		return ConfigDTO.class;
	}


}
