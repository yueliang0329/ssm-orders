package com.gzj.orders.test;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gzj.orders.bean.Order;
import com.gzj.orders.dao.OrderMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	SqlSession sqlSession;
	

	@Autowired
	OrderMapper orderMapper;
	
	@Test
	public void testCRUD(){

		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		for(int i = 0;i<20;i++){
			mapper.insertSelective(new Order(null,new Date(), 1, 8.12+i, 1,"N"));
		}
			
		}
}
