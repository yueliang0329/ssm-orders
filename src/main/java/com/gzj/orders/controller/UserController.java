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
		//System.out.println("传入用户："+user.getUserName()+"--"+user.getPassword());
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			//数据库是否存在该用户
			boolean b = userService.checkUser(user.getUserName());
			//System.out.println("是否不存在该用户:"+b);
			if(b!=true)
			{
				//存在该用户再检查密码是否正确
				boolean c=userService.checkUserPassword(user);
				if(c)
				{
					return Msg.fail().add("va_msg2", "密码错误");
				}
				else
				{
					return Msg.success().add("userName",user.getUserName());
				}
				
			}
			else {
				return Msg.fail().add("va_msg1", "用户名不存在");
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
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Order> orderList=orderService.selectByUser(userId);
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		//System.out.println(orderList.get(1).getOrderTime());
		PageInfo page = new PageInfo(orderList, 5);
		return Msg.success().add("pageInfo", page);
		
	}
	
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuserName")
	public Msg checkuser(@RequestParam("userName")String userName){
		//先判断用户名是否是合法的表达式;
		String regx = "(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!userName.matches(regx)){
			return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
		}
		
		//数据库用户名重复校验
		boolean b = userService.checkUser(userName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "用户名不可用");
		}
	}
	
	
	/**
	 * 注册用户
	 * 1、支持JSR303校验
	 * 2、导入Hibernate-Validator
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid User user,BindingResult result){
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else{
			userService.saveUser(user);
			return Msg.success().add("userName",user.getUserName());
		}
		
	}

}
