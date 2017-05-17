package springmvc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.longtop.springmvc.Bs.IPlmBasService;
import com.longtop.springmvc.service.PlmBasService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/application.xml")
public class SpringTest {
	@Autowired
	RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	PlmBasService plmBasService2;
	
	
	
	@Test
	public void test() {
		assertNotNull(plmBasService2);
		plmBasService2.run();
		
		
	//	service.runService();
//		assertNotNull(service);
	}
	
}
