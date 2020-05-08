package com.gzj.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzj.orders.bean.User;
import com.gzj.orders.bean.UserExample;
import com.gzj.orders.bean.UserExample.Criteria;
import com.gzj.orders.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	/**
	 * �����û����Ƿ����
	 * 
	 * @param empName
	 * @return  true������ǰ��������   fasle��������
	 */
	public boolean checkUser(String userName) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(userName);
		long count = userMapper.countByExample(example);
		return count == 0;
	}
	
	public boolean checkUserPassword(User user) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(user.getUserName());
		criteria.andPasswordEqualTo(user.getPassword());
		long count = userMapper.countByExample(example);
		return count == 0;
	}
	
	public User selectByUserName(String userName)
	{
		return userMapper.selectByUserName(userName);
	}
	
	/**
	 * �û�����
	 * @param employee
	 */
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insertSelective(user);
	}
}
