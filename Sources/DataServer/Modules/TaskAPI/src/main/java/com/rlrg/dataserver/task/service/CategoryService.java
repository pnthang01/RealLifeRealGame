package com.rlrg.dataserver.task.service;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.task.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Transient
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryRepository categoryRepository;
}