package com.longtop.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.longtop.springmvc.Bs.AbsPlmbasDao;


public class PlmBasDao extends AbsPlmbasDao {

	@Autowired
	public RedisTemplate<Object,Object> redisTemplate;
	
	
	
}
