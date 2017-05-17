package com.longtop.springmvc.Bs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbsPlmBasService {
	
	protected Logger logger = LoggerFactory.getLogger(AbsPlmBasService.class);
	
	/**
	 * ģ���ǰ�õ���
	 */
	public void beforeHandle(){
		
		logger.info("ǰ�÷��������ã�");
	}
	
	public abstract Object doHandle();
	
	/**
	 * ģ��ĺ��õ���
	 */
	public void afterHandle(){
		
		logger.info("���÷��������ã�");
	}
	
	
	
	public void run(){
		this.beforeHandle();
		
		doHandle();
		
		this.afterHandle();
	}
	
	
	
}
