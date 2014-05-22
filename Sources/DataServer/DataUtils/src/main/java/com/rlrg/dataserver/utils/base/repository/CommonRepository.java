package com.rlrg.dataserver.utils.base.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rlrg.dataserver.utils.base.controller.BaseUtils;
import com.rlrg.dataserver.utils.base.domain.User;
import com.rlrg.dataserver.utils.base.exception.RepositoryException;

@Repository
public class CommonRepository {
	
	private static final String PARAMETERS_NULL = "Parameters are null";
	private static final String UNCHECKED = "unchecked";
	private static final Logger LOG = LoggerFactory.getLogger(CommonRepository.class);
	
	@Autowired
	protected EntityManager entityManager;
	
	public int update(User user) throws RepositoryException{
		int result;
		try {
			entityManager.joinTransaction();
			final Query updateQuery = entityManager.createNamedQuery("user.Update");
			//
			updateQuery.setParameter("token", user.getToken());
			updateQuery.setParameter("id", user.getId());
			//
			result = updateQuery.executeUpdate();
		} catch (RuntimeException ex) {
			LOG.error(BaseUtils.getExceptionStackTrace(ex));
			throw new RepositoryException(ex);
		}
		return result;
	}
}
