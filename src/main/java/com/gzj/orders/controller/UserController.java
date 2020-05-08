package com.gzj.orders.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzj.orders.bean.Msg;
import com.gzj.orders.bean.Order;
import com.gzj.orders.bean.User;
import com.gzj.orders.service.OrderService;
import com.gzj.orders.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Msg login(@Valid User user,BindingResult result){
		//System.out.println("�����û���"+user.getUserName()+"--"+user.getPassword());
		if(result.hasErrors()){
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("������ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			//���ݿ��Ƿ���ڸ��û�
			boolean b = userService.checkUser(user.getUserName());
			//System.out.println("�Ƿ񲻴��ڸ��û�:"+b);
			if(b!=true)
			{
				//���ڸ��û��ټ�������Ƿ���ȷ
				boolean c=userService.checkUserPassword(user);
				if(c)
				{
					return Msg.fail().add("va_msg2", "�������");
				}
				else
				{
					return Msg.success().add("userName",user.getUserName());
				}
				
			}
			else {
				return Msg.fail().add("va_msg1", "�û���������");
			}
		}		
	}
	
	@RequestMapping(value="/listOrder",method=RequestMethod.GET)
	public String goList(ModelMap map,@RequestParam("userName") String userName){
		
		User u=userService.selectByUserName(userName);
		//List<Order> orderList=orderService.selectByUser(u.getUserId());
		map.put("user", u);
		return "listOrder" ;
	}
	
	@RequestMapping("/orders")
	@ResponseBody
	public Msg getOrdersWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			@RequestParam(value = "userId") Integer userId) {
		// �ⲻ��һ����ҳ��ѯ
		// ����PageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ���ã�����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// startPage��������������ѯ����һ����ҳ��ѯ
		List<Order> orderList=orderService.selectByUser(userId);
		// ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ������ˡ�
		// ��װ����ϸ�ķ�ҳ��Ϣ,���������ǲ�ѯ���������ݣ�����������ʾ��ҳ��
		//System.out.println(orderList.get(1).getOrderTime());
		PageInfo page = new PageInfo(orderList, 5);
		return Msg.success().add("pageInfo", page);
		
	}
	
	
	/**
	 * ����û����Ƿ����
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuserName")
	public Msg checkuser(@RequestParam("userName")String userName){
		//���ж��û����Ƿ��ǺϷ��ı��ʽ;
		String regx = "(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!userName.matches(regx)){
			return Msg.fail().add("va_msg", "�û���������6-16λ���ֺ���ĸ����ϻ���2-5λ����");
		}
		
		//���ݿ��û����ظ�У��
		boolean b = userService.checkUser(userName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "�û���������");
		}
	}
	
	
	/**
	 * ע���û�
	 * 1��֧��JSR303У��
	 * 2������Hibernate-Validator
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid User user,BindingResult result){
		if(result.hasErrors()){
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("������ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			userService.saveUser(user);
			return Msg.success().add("userName",user.getUserName());
		}
		
	}

}
