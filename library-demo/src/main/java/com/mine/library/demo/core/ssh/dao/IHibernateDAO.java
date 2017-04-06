package com.mine.library.demo.core.ssh.dao;

import org.hibernate.LockMode;

import java.util.List;

public interface IHibernateDAO {
	public Object get(String entityName, int id);

	public Object get(String entityName, int id, LockMode lockMode);

	public Object get(Class<Object> entityClass, int id);

	public Object get(Class<Object> entityClass, int id, LockMode lockMode);

	public Object load(String entityName, int id);

	public Object load(String entityName, int id, LockMode lockMode);

	public Object load(Class<Object> entityClass, int id);

	public Object load(Class<Object> entityClass, int id, LockMode lockMode);

	public void load(Object entity, int id);

	public List loadAll(Class<Object> entityClass);

	public List find(String queryString);

	public List find(String queryString, Object value);

	public List find(String queryString, Object[] values);

	public void flush();
	
	public void clear();
}
