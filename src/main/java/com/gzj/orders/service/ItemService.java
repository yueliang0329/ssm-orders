package com.gzj.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzj.orders.bean.Item;
import com.gzj.orders.dao.ItemMapper;

@Service
public class ItemService {

	@Autowired
	ItemMapper itemMapper;
	
	public List<Item> getItems() {
		// TODO Auto-generated method stub
		List<Item> list = itemMapper.selectByExample(null);
		return list;
	}

}
