package com.longtop.springmvc.Bs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsPlmBasService {
	
	protected Logger logger = LoggerFactory.getLogger(AbsPlmBasService.class);
	
	/**
	 * 模板的前置调用
	 */
	public void beforeHandle(){
		
		logger.info("前置方法被调用！");
	}
	
	public abstract Object doHandle();
	
	/**
	 * 模板的后置调用
	 */
	public void afterHandle(){
		
		logger.info("后置方法被调用！");
	}
	
	
	
	public void run(){
		this.beforeHandle();
		
		doHandle();
		
		this.afterHandle();
	}
	
	
	
}
