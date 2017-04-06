package com.mine.library.demo.core.ssh.dao.impl;

import com.mine.library.demo.core.ssh.dao.IHibernateDAO;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public class HibernateDAOImpl extends HibernateDaoSupport implements IHibernateDAO {
	/**
	 * Return the persistent instance of the given named entity with the given identifier, or null if there is no such persistent instance.
	 * @param entityName - the entity name
	 * @param id - an identifier
	 * @return a persistent instance or null  
	 * @see Session.get(String, Serializable)
	 */
	@Override
	public Object get(String entityName,int id) { 
		return getHibernateTemplate().get(entityName, id);
	}
	
	/**
	 * Return the persistent instance of the given named entity with the given identifier, or null if there is no such persistent instance. Obtain the specified lock mode if the instance exists.
	 * @param entityName - the entity name
	 * @param id - an identifier
	 * @param lockMode - the lock mode
	 * @return a persistent instance or null 
	 * @see Session.get(String, Serializable, LockMode)
	 */
	@Override
	public Object get(String entityName,int id,LockMode lockMode) {
		return getHibernateTemplate().get(entityName, id, lockMode);
	}
	
	/**
	 * Return the persistent instance of the given entity class with the given identifier, or null if there is no such persistent instance.
	 * @param entityClass - a persistent class
	 * @param id - an identifier
	 * @return a persistent instance or null
	 * @see Session.get(Class, Serializable)
	 */
	@Override
	public Object get(Class<Object> entityClass, int id) {
		return getHibernateTemplate().get(entityClass, id);
	}
	
	/**
	 * Return the persistent instance of the given entity class with the given identifier, or null if there is no such persistent instance. Obtain the specified lock mode if the instance exists.
	 * @param entityClass - a persistent class
	 * @param id - an identifier
	 * @param lockMode - the lock mode
	 * @return a persistent instance or null
	 * @see Session.get(Class, Serializable, LockMode)
	 */
	@Override
	public Object get(Class<Object> entityClass,int id, LockMode lockMode) {
		return getHibernateTemplate().get(entityClass, id, lockMode);
	}
	
	/**
	 * Return the persistent instance of the given named entity with the given identifier, assuming that the instance exists. 
	 * You should not use this method to determine if an instance exists (use get() instead). Use this only to retrieve an instance that you assume exists, where non-existence would be an actual error.
	 * @param entityName - the entity name
	 * @param id - an identifier
	 * @return a persistent instance or proxy
	 * @see Session.get(String, Serializable)
	 */
	@Override
	public Object load(String entityName,int id) {
		return getHibernateTemplate().load(entityName, id);
	}
	
	/**
	 * Return the persistent instance of the given named entity with the given identifier, obtaining the specified lock mode, assuming the instance exists.
	 * @param entityName - the entity name
	 * @param id - an identifier
	 * @param lockMode - the lock mode
	 * @return a persistent instance or proxy
	 * @see Session.get(String, Serializable, LockMode)
	 */
	@Override
	public Object load(String entityName, int id, LockMode lockMode) {
		return getHibernateTemplate().load(entityName, id, lockMode);
	}
	
	/**
	 * Return the persistent instance of the given entity class with the given identifier, assuming that the instance exists. 
	 * You should not use this method to determine if an instance exists (use get() instead). Use this only to retrieve an instance that you assume exists, where non-existence would be an actual error.	 * @param entityName - the entity name
	 * @param entityClass - a persistent class
	 * @param id - an identifier
	 * @return a persistent instance or proxy
	 * @see Session.get(Class, Serializable)
	 */
	@Override
	public Object load(Class<Object> entityClass, int id) {
		return getHibernateTemplate().load(entityClass, id);
	}
	
	/**
	 * Return the persistent instance of the given entity class with the given identifier, obtaining the specified lock mode, assuming the instance exists.
	 * @param entityName - a persistent class
	 * @param id - an identifier
	 * @param lockMode - the lock mode
	 * @return a persistent instance or proxy
	 * @see Session.get(Class, Serializable, LockMode)
	 */
	@Override
	public Object load(Class<Object> entityClass, int id, LockMode lockMode) {
		return getHibernateTemplate().load(entityClass, id, lockMode);
	}
	
	/**
	 * Load the persistent instance with the given identifier into the given object, throwing an exception if not found.
	 * @param entity - the object (of the target class) to load into
	 * @param id - an identifier
	 * @see Session.load(Object, Serializable)
	 */
	@Override
	public void load(Object entity,int id) {
		getHibernateTemplate().load(entity, id);
	}
	
	/**
	 * Return all persistent instances of the given entity class. Note: Use queries or criteria for retrieving a specific subset.
	 * @param entityClass - a persistent class
	 * @return a List containing 0 or more persistent instances
	 * @see Session.createCriteria(Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List loadAll(Class<Object> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	/**
	 * Execute a query for persistent instances.
	 * @param queryString - a query expressed in Hibernate's query language
	 * @return a List containing 0 or more persistent instances
	 * @see Session.createQuery(String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString) {
		return getHibernateTemplate().find(queryString);
	}
	
	/**
	 * Execute a query for persistent instances, binding one value to a "?" parameter in the query string.
	 * @param queryString - a query expressed in Hibernate's query language
	 * @param value - the value of the parameter
	 * @return a List containing 0 or more persistent instances
	 * @see Session.createQuery(String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString,Object value) {
		return getHibernateTemplate().find(queryString, value);
	}
	
	/**
	 * Execute a query for persistent instances, binding a number of values to "?" parameters in the query string.
	 * @param queryString - a query expressed in Hibernate's query language
	 * @param values - the value of the parameter
	 * @return  a List containing 0 or more persistent instances
	 * @see Session.createQuery(String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queryString,Object[] values) {
		return getHibernateTemplate().find(queryString, values);
	}
	
	/**
	 * Flush all pending saves, updates and deletes to the database.
	 * @see Session.flush()
	 */
	@Override
	public void flush() {
		getHibernateTemplate().flush();
	}
	
	/**
	 * Remove all objects from the Session cache, and cancel all pending saves, updates and deletes.
	 * @see Session.clear()
	 */
	@Override
	public void clear() {
		getHibernateTemplate().clear();
	}
	
	/**
	 * Persist the given transient instance.
	 * @param entity - the transient instance to persist
	 * @return the generated identifier
	 * @see Session.save(Object)
	 */
	public Serializable save(Object entity) {
		return getHibernateTemplate().save(entity);
	}
	
	/**
	 * Persist the given transient instance.
	 * @param entityName - the name of a persistent entity
	 * @param entity - the transient instance to persist
	 * @return the generated identifier
	 * @see Session.save(String, Object)
	 */
	public Serializable save(String entityName, Object entity) {
		return getHibernateTemplate().save(entityName, entity);
	}
	
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}
	public void update(Object entity, LockMode lockMode) {
		getHibernateTemplate().update(entity, lockMode);
	}
	
	public void update(String entityName, Object entity) {
		getHibernateTemplate().update(entityName, entity);
	}
	public void update(String entityName, Object entity, LockMode lockMode) {
		getHibernateTemplate().update(entityName, entity, lockMode);
	}
	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	public void saveOrUpdate(String entityName, Object entity) {
		getHibernateTemplate().saveOrUpdate(entityName, entity);
	}
	public void saveOrUpdateAll(Collection<Object> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}
	public void delete(Object entity, LockMode lockMode) {
		getHibernateTemplate().delete(entity, lockMode);
	}
	public void delete(String entityName, Object entity) {
		getHibernateTemplate().delete(entityName, entity);
	}
	public void delete(String entityName, Object entity, LockMode lockMode) {
		getHibernateTemplate().delete(entityName, entity, lockMode);
	}
	public void deleteAll(Collection<Object> entities) {
		getHibernateTemplate().deleteAll(entities);
	}
	
}
