package com.longtop.springmvc.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.longtop.springmvc.Bs.AbsPlmBasService;
import com.longtop.springmvc.Bs.IPlmBasService;
import com.longtop.springmvc.dao.PlmBasDao;

@Component
public class PlmBasService extends AbsPlmBasService{
	
	
	@Override
	public Object doHandle() {
		System.out.println(22222222);
		return null;
	}
	
	
}
