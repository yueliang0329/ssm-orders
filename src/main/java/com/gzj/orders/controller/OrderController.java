package com.gzj.orders.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzj.orders.bean.Msg;
import com.gzj.orders.bean.Order;
import com.gzj.orders.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/addOrder",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveOrder(Order order){			
			orderService.saveOrder(order);
			return Msg.success();	
	}
	
	/**
	 * 根据id查询订单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getOrder/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		
		Order order = orderService.getOrder(id);
		return Msg.success().add("order", order);
	}
	
	//保存订单
	@ResponseBody
	@RequestMapping(value="/order/{orderId}",method=RequestMethod.PUT)
	public Msg saveOrder(Order order,HttpServletRequest request){
		orderService.updateEmp(order);
		return Msg.success()	;
	}
	
	/**
	 * 单个批量二合一
	 * 批量删除：1-2-3
	 * 单个删除：1
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteOrder/{ids}",method=RequestMethod.DELETE)
	public Msg deleteOrder(@PathVariable("ids")String ids){
		//批量删除
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			orderService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			orderService.deleteOrder(id);
		}
		return Msg.success();
	}
	
}
