package com.mine.library.demo.core.ssh.service;

import org.hibernate.LockMode;

import java.util.List;

public interface ICoreService {
	public Object getEntityById(Class entityClass, int id);

	public Object getEntityById(Class entityClass, int id, LockMode lockMode);

	public Object getEntityById(String entityName, int id);

	public Object getEntityById(String entityName, int id, LockMode lockMode);

	public Object loadEntityById(Class entityClass, int id);

	public Object loadEntityById(Class entityClass, int id, LockMode lockMode);

	public Object loadEntityById(String entityName, int id);

	public Object loadEntityById(String entityName, int id, LockMode lockMode);

	public void loadEntity(Object entity, int id);

	public List loadEntityAll(Class entityClass);

	public List find(String queryString);

	public List find(String queryString, Object value);

	public List find(String queryString, Object[] values);
	
	public void flush();
	
	public void clear();
}
