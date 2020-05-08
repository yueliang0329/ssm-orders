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
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getOrder/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		
		Order order = orderService.getOrder(id);
		return Msg.success().add("order", order);
	}
	
	//���涩��
	@ResponseBody
	@RequestMapping(value="/order/{orderId}",method=RequestMethod.PUT)
	public Msg saveOrder(Order order,HttpServletRequest request){
		orderService.updateEmp(order);
		return Msg.success()	;
	}
	
	/**
	 * ������������һ
	 * ����ɾ����1-2-3
	 * ����ɾ����1
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteOrder/{ids}",method=RequestMethod.DELETE)
	public Msg deleteOrder(@PathVariable("ids")String ids){
		//����ɾ��
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			//��װid�ļ���
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
