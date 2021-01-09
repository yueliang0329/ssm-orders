package com.gzj.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzj.orders.bean.Item;
import com.gzj.orders.bean.Msg;
import com.gzj.orders.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private  ItemService itemService;
	
	@RequestMapping("/items")
	@ResponseBody
	public Msg getItems(){
		//查出的所有部门信息
		List<Item> list = itemService.getItems();
		return Msg.success().add("items", list);
	}
}
