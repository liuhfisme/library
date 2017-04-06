package com.mine.library.demo.core.ssh.service.impl;

import com.mine.library.demo.core.ssh.dao.IHibernateDAO;
import com.mine.library.demo.core.ssh.service.ICoreService;
import org.hibernate.LockMode;

import java.util.List;


public class CoreServiceImpl implements ICoreService {
	private IHibernateDAO hibernateDAO;

	public IHibernateDAO getHibernateDAO() {
		return hibernateDAO;
	}

	public void setHibernateDAO(IHibernateDAO hibernateDAO) {
		this.hibernateDAO = hibernateDAO;
	}

	@Override
	public Object getEntityById(Class entityClass, int id) {
		return hibernateDAO.get(entityClass, id);
	}

	@Override
	public Object getEntityById(Class entityClass, int id,
			LockMode lockMode) {
		return hibernateDAO.get(entityClass, id, lockMode);
	}

	@Override
	public Object getEntityById(String entityName, int id) {
		return hibernateDAO.get(entityName, id);
	}

	@Override
	public Object getEntityById(String entityName, int id, LockMode lockMode) {
		return hibernateDAO.get(entityName, id, lockMode);
	}

	@Override
	public Object loadEntityById(Class entityClass, int id) {
		return hibernateDAO.load(entityClass, id);
	}

	@Override
	public Object loadEntityById(Class entityClass, int id, LockMode lockMode) {
		return hibernateDAO.load(entityClass, id, lockMode);
	}

	@Override
	public Object loadEntityById(String entityName, int id) {
		return hibernateDAO.load(entityName, id);
	}

	@Override
	public Object loadEntityById(String entityName, int id, LockMode lockMode) {
		return hibernateDAO.load(entityName, id, lockMode);
	}
	
	@Override
	public void loadEntity(Object entity, int id) {
		hibernateDAO.load(entity, id);
	}
	
	@Override
	public List loadEntityAll(Class entityClass) {
		return hibernateDAO.loadAll(entityClass);
	}

	@Override
	public List find(String queryString) {
		return hibernateDAO.find(queryString);
	}

	@Override
	public List find(String queryString, Object value) {
		return hibernateDAO.find(queryString, value);
	}

	@Override
	public List find(String queryString, Object[] values) {
		return hibernateDAO.find(queryString, values);
	}
	
	@Override
	public void flush() {
		hibernateDAO.flush();
	}
	
	@Override
	public void clear() {
		hibernateDAO.clear();
	}

}
