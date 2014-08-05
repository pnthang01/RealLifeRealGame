package com.rlrg.dataserver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.entity.Config;
import com.rlrg.dataserver.repository.ConfigRepository;
import com.rlrg.dataserver.utillities.Constants;

@Service
public class ConfigService extends BaseService<Config, Config> {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigService.class); 
    
	@Autowired
	private ConfigRepository configRepo;
	
    private static Map<String, String> CONFIG = new HashMap<String, String>();

	private void loadConfig() {
		List<String> keys = new ArrayList<String>();
		keys.add(Constants.PAGE_SIZE_KEY);
		keys.add(Constants.TOTAL_STATISTIC_ROWS_KEY);
		//
		Map<String, String> configs = new HashMap<String, String>();
		List<Config> configEntity = this.getConfigsByKeys(keys);
		for (Config config : configEntity) {
			configs.put(config.getKey(), config.getValue());
		}
		//
		synchronized (CONFIG) {
			CONFIG = configs;
		}
	}

	public String getConfig(String key) {
		if(null == CONFIG || CONFIG.isEmpty()){
			loadConfig();
		}
		return CONFIG.get(key);
	}

	public Config getConfigByKey(String key) {
		return configRepo.getConfigByKey(key);
	}

	public List<Config> getConfigsByKeys(List<String> keys) {
		return configRepo.getConfigsByKeys(keys);
	}

	public Config convertEntityToDTO(Config data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Config revertDTOToEntity(Config dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Config> getVClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
