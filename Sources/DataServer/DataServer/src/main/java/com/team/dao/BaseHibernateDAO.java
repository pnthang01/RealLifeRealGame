package com.team.dao;

// default package

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.exeption.DaoExeption;
import com.team.util.Utils;

/**
 * Data access object (DAO) for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
@Repository
public abstract class BaseHibernateDAO<T> {

	private static final String PARAMETERS_NULL = "Parameters are null";
	private static final String UNCHECKED = "unchecked";
	private static final Logger LOG = LoggerFactory
			.getLogger(BaseHibernateDAO.class);

	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * @param o
	 * @return
	 * @throws DaoExeption
	 */
	public final Integer create(T o) throws DaoExeption {
		try {
			return (Integer) sessionFactory.getCurrentSession().save(o);
		} catch (Exception ex) {
			LOG.error("Not able to save entity", ex);

			throw new DaoExeption(ex);
		}
	}

	/**
	 * @param o
	 * @throws DaoExeption
	 */
	public final void update(T o) throws DaoExeption {
		try {
			sessionFactory.getCurrentSession().update(o);
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.paktor.api.dao.AbstractHibernateDAO#delete(java.lang.Object)
	 */
	public final void delete(T o) throws DaoExeption {
		try {
			sessionFactory.getCurrentSession().delete(o);
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.paktor.api.dao.AbstractHibernateDAO#merge(java.lang.Object)
	 */
	@SuppressWarnings(UNCHECKED)
	public final T merge(T o) throws DaoExeption {
		try {
			return (T) sessionFactory.getCurrentSession().merge(o);
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.paktor.api.dao.AbstractHibernateDAO#findById(java.lang.Long)
	 */
	@SuppressWarnings(UNCHECKED)
	public final T findById(Integer id) throws DaoExeption {
		T persist = null;
		try {
			if (id != null) {
				try {
					persist = (T) sessionFactory.getCurrentSession().get(
							Class.forName(getClassName()), id);
				} catch (ClassNotFoundException e) {
					LOG.error(Utils.getExceptionStackTrace(e));
					throw new DaoExeption(e);
				}
			}
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}

		return persist;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.paktor.api.dao.AbstractHibernateDAO#findAll()
	 */
	@SuppressWarnings(UNCHECKED)
	public final Collection<T> findAll() throws DaoExeption {
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("from " + getClassName()).list();
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.paktor.api.dao.AbstractHibernateDAO#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@SuppressWarnings(UNCHECKED)
	public final Collection<T> findByCriteria(DetachedCriteria criteria)
			throws DaoExeption {
		try {
			return criteria.getExecutableCriteria(
					sessionFactory.getCurrentSession()).list();
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	@SuppressWarnings("rawtypes")
	private String getClassName() {
		ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return ((Class) type.getActualTypeArguments()[0]).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofrecom.zanba.dao.AbstractHibernateDAO#findByNamedQuery(java.lang
	 * .String, java.util.Map)
	 */
	/*
	 * @SuppressWarnings(UNCHECKED) public final List<T> findByNamedQuery(String
	 * queryName, Map<String, Object> queryParams) throws DaoExeption {
	 * 
	 * String[] params = new String[queryParams.size()]; Object[] values = new
	 * Object[queryParams.size()];
	 * 
	 * int index = 0; for (Entry<String, Object> s : queryParams.entrySet()) {
	 * params[index] = s.getKey(); values[index] = s.getValue(); index++; }
	 * 
	 * try { return getHibernateTemplate().findByNamedQueryAndNamedParam(
	 * queryName, params, values); } catch (RuntimeException ex) {
	 * LOG.error(Utils.getExceptionStackTrace(ex)); throw new DaoExeption(ex);
	 * } }
	 */

	/**
	 * @param propertyName
	 * @param value
	 * @param dtoClass
	 * @return
	 * @throws DaoExeption
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List<T> findByProperty(final String propertyName,
			final Object value, final Class dtoClass) throws DaoExeption {

		final DetachedCriteria criteria = DetachedCriteria.forClass(dtoClass);

		criteria.add(Restrictions.eq(propertyName, value));
		try {
			return criteria.getExecutableCriteria(
					sessionFactory.getCurrentSession()).list();
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/**
	 * @param propertyNames
	 * @param values
	 * @param dtoClass
	 * @return
	 * @throws DaoExeption
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List<T> findByProperties(final String[] propertyNames,
			final Object[] values, final Class dtoClass) throws DaoExeption {

		if (propertyNames == null || values == null) {
			LOG.error(PARAMETERS_NULL);
			throw new DaoExeption(PARAMETERS_NULL);
		}

		if (propertyNames.length != values.length) {
			LOG.error(PARAMETERS_NULL);
			throw new DaoExeption(PARAMETERS_NULL);
		}

		final DetachedCriteria criteria = DetachedCriteria.forClass(dtoClass);

		for (int i = 0; i < propertyNames.length; i++) {
			criteria.add(Restrictions.eq(propertyNames[i], values[i]));
		}

		try {
			return criteria.getExecutableCriteria(
					sessionFactory.getCurrentSession()).list();
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.paktor.api.dao.AbstractHibernateDAO#findByQuery(java.lang.String)
	 */
	@SuppressWarnings(UNCHECKED)
	public final List<T> findByQuery(final String query) throws DaoExeption {
		try {
			return sessionFactory.getCurrentSession().createQuery(query).list();
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.paktor.api.dao.AbstractHibernateDAO#findByQuery(java.lang.String,
	 * java.lang.Object)
	 */
	@SuppressWarnings(UNCHECKED)
	public final List<T> findByQuery(final String query, final Object value)
			throws DaoExeption {

		try {
			return sessionFactory.getCurrentSession().createQuery(query)
					.setParameter(0, value).list();
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.paktor.api.dao.AbstractHibernateDAO#findByQuery(java.lang.String,
	 * java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	public final List<T> findByQuery(final String query, final Object[] values)
			throws DaoExeption {

		try {
			Query q = sessionFactory.getCurrentSession().createQuery(query);
			int position = 0;
			for (Object v : values) {
				q.setParameter(position, v);
				position++;
			}
			return q.list();
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	public T findUniqueByQuery(String query) throws DaoExeption {
		List<T> list = findByQuery(query);

		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public T findUniqueByQuery(String query, Object value) throws DaoExeption {
		List<T> list = findByQuery(query, value);

		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public T findUniqueByQuery(String query, Object[] value) throws DaoExeption {
		List<T> list = findByQuery(query, value);

		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public final void saveOrUpdate(T o) throws DaoExeption {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(o);
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}

	/**
	 * count all items by propertyName and Value
	 */
	@SuppressWarnings("unchecked")
	public final int count(final String propertyName, final Object value)
			throws DaoExeption {
		try {

			StringBuffer hql = new StringBuffer();
			hql.append("select count (cl.id) from ").append(getClassName())
					.append(" cl where cl.").append(propertyName).append("=?");

			List<Long> lst = sessionFactory.getCurrentSession()
					.createQuery(hql.toString()).setParameter(0, value).list();
			if (CollectionUtils.isNotEmpty(lst)) {
				return lst.get(0).intValue();
			}
			return 0;
		} catch (RuntimeException ex) {
			LOG.error(Utils.getExceptionStackTrace(ex));
			throw new DaoExeption(ex);
		}
	}
}