package com.mine.library.demo.core.ssh.struts;

import com.mine.library.demo.core.ssh.service.ICoreService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

public class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6216311777185176564L;
	public ICoreService coreService;
	
	public ICoreService getCoreService() {
		return coreService;
	}
	public void setCoreService(ICoreService coreService) {
		this.coreService = coreService;
	}
	public final Logger logger=Logger.getLogger(getClass());
}
