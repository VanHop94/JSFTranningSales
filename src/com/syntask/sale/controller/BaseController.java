package com.syntask.sale.controller;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.core.Conversation;

import com.syntask.sale.dao.BaseDao;

public abstract class BaseController {
	
	@In
	private EntityManager entityManager; 
	
	@Create
	public void init() throws Exception{
		getDao().setEntityManager(entityManager);
		entityManager.setFlushMode(FlushModeType.AUTO);
		Conversation.instance().begin();
		initData();
	}
	
	@Destroy
	public void destroy() {
		Conversation.instance().end();
	}
	
	public abstract BaseDao<?,?> getDao();
	public abstract void initData() throws Exception;
	
}
