package com.gzj.orders.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gzj.orders.bean.Order;
import com.gzj.orders.bean.OrderExample;
import com.gzj.orders.bean.OrderExample.Criteria;
import com.gzj.orders.dao.OrderMapper;

@Service
public class OrderService {


	@Autowired
	OrderMapper orderMapper;
	
	public List<Order> selectByUser(int orderId)
	{
		System.out.println("right......");
		return orderMapper.selectByUser(orderId);
	}
	  
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		order.setOrderTime(new Date());
		order.setIsPaid("N");
		orderMapper.insertSelective(order);
	}
	
	public Order getOrder(Integer id) {
		// TODO Auto-generated method stub
		Order order = orderMapper.selectByPrimaryKey(id);
		return order;
	}
	
	/**
	 * 订单更新
	 * @param employee
	 */
	public void updateEmp(Order order) {
		// TODO Auto-generated method stub
		order.setOrderTime(new Date());
		orderMapper.updateByPrimaryKeySelective(order);
	}
	
	/**
	 * 订单删除
	 * @param id
	 */
	public void deleteOrder(Integer id) {
		// TODO Auto-generated method stub
		orderMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		OrderExample example = new OrderExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andOrderIdIn(ids);
	//	criteria.andEmpIdIn(ids);
		orderMapper.deleteByExample(example);
	}
}
