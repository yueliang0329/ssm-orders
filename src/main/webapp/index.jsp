<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- 引入jquery -->
<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<!-- 引入bootstrap样式 -->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

	<!-- 员工添加的模态框 -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">用户注册</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">用户名</label>
		    <div class="col-sm-10">
		      <input type="text" name="userName" class="form-control" id="userName_add_input" >
		      <span class="help-block"></span>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">密码</label>
		    <div class="col-sm-10">
		      <input type="text" name="password" class="form-control" id="password_add_input">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label class="col-sm-2 control-label">确认密码</label>
		    <div class="col-sm-10">
		      <input type="text" name="password2" class="form-control" id="password2_add_input">
		      <span class="help-block"></span>
		    </div>
		  </div>
		  
		  
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="user_save_btn">保存</button>
      </div>
    </div>
  </div>
</div>
		<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-5">
				<h1>订单管理</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form action="##" method="post" οnsubmit="return false" role="form"
				id="login-form">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="userName" id="userName_input"
						placeholder="请输入用户名"><span class="help-block"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password" id="password_input"
						placeholder="请输入密码"><span class="help-block"></span>
				</div>
	
				<div class="row">
					<div class="col-xs-6">
						<button type="button" class="btn btn-primary btn-block btn-flat" 
							id="btn-login">登 录</button>
					</div>
					<div class="col-xs-6">
						<button type="button" class="btn btn-danger btn-block btn-flat"
							id="btn-register">注 册</button>
					</div>
				</div>
			</form>
			</div>
		</div>	
	</div>
	
	<script type="text/javascript">
	//显示校验结果的提示信息
	function show_validate_msg(ele,status,msg){
		//清除当前元素的校验状态
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text("");
		if("success"==status){
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text(msg);
		}else if("error" == status){
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	}
	
	//校验表单数据
	function validate_add_form(){
		//1、拿到要校验的数据，使用正则表达式
		var empName = $("#userName_input").val();
		var regName = /(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if(!regName.test(empName)){
			show_validate_msg("#userName_input", "error", "用户名可以是2-5位中文或者2-16位英文和数字的组合");
			return false;
		}else{
			show_validate_msg("#userName_input", "success", "");
		};
		
		//2、密码信息	
		var password = $("#password_input").val();
		if(!regName.test(password)){
			show_validate_msg("#password_input", "error", "密码可以是2-5位中文或者2-16位英文和数字的组合");
			return false;
		}else{
			show_validate_msg("#password_input", "success", "");
		};
		return true;
		
	}

	$("#btn-login").click(function(){
		//1、校验
		if(!validate_add_form()){
			return false;
		};
		//2、发送请求
		$.ajax({
			url:"${APP_PATH}/login",
			type:"POST",
			data:$("#login-form").serialize(),
			success:function(result){
				//alert(result.msg);
				console.log(result);
				if(result.code == 100){
					//成功就跳转到list页面
					//window.location.href="${APP_PATH}/list/"+result.extend.userName;
					window.location.href="${APP_PATH}/listOrder?userName="+result.extend.userName;
				}else{
					//有哪个字段的错误信息就显示哪个字段的；
					if(undefined != result.extend.errorFields)
					{
						if(undefined != result.extend.errorFields.userName){
							//显示用户名
							show_validate_msg("#userName_input", "error", result.extend.errorFields.userName);
						}
						if(undefined != result.extend.errorFields.password){
							//显示密码
							show_validate_msg("#password_input", "error", result.extend.errorFields.password);
						}
					}
					else if(undefined != result.extend.va_msg1)
						{
						show_validate_msg("#userName_input", "error",  result.extend.va_msg1);
						}
					else if(undefined != result.extend.va_msg2)
						{
						show_validate_msg("#password_input", "error",  result.extend.va_msg2);
						}
				}
			}
		});		
	})
	
	//清空表单样式及内容
		function reset_form(ele){
			$(ele)[0].reset();
			//清空表单样式
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
		}
	//点击注册按钮弹出模态框。
		$("#btn-register").click(function(){
			//清除表单数据（表单完整重置（表单的数据，表单的样式））
			reset_form("#empAddModal form");
			//弹出模态框
			$("#empAddModal").modal({
				backdrop:"static"
			});
		});
	
	//校验表单数据
	function validate_login_form(){
		//1、拿到要校验的数据，使用正则表达式
		var userNameAdd = $("#userName_add_input").val();
		var regNameAdd = /(^[a-zA-Z0-9_-]{2,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if(!regNameAdd.test(userNameAdd)){
			show_validate_msg("#userName_add_input", "error", "用户名可以是2-5位中文或者2-16位英文和数字的组合");
			return false;
		}else{
			show_validate_msg("#userName_add_input", "success", "");
		};
		
		//2、密码信息	
		var passwordAdd = $("#password_add_input").val();
		if(!regNameAdd.test(passwordAdd)){
			show_validate_msg("#password_add_input", "error", "密码可以是2-5位中文或者2-16位英文和数字的组合");
			return false;
		}else{
			show_validate_msg("#password_add_input", "success", "");
		};
		//3、确认2次密码是否一致
		var password2Add=$("#password2_add_input").val();
		if (passwordAdd !=password2Add)
			{
			show_validate_msg("#password2_add_input", "error", "2次输入的密码不一致");
			return false;
			}
		return true;
		
	}
	
	//校验用户名是否可用
	$("#userName_add_input").change(function(){
		//发送ajax请求校验用户名是否可用
		var userName = this.value;
		$.ajax({
			url:"${APP_PATH}/checkuserName",
			data:"userName="+userName,
			type:"POST",
			success:function(result){
				if(result.code==100){
					show_validate_msg("#userName_add_input","success","用户名可用");
					$("#user_save_btn").attr("ajax-va","success");
				}else{
					show_validate_msg("#userName_add_input","error",result.extend.va_msg);
					$("#user_save_btn").attr("ajax-va","error");
				}
			}
		});
	});
	
	//点击保存，保存用户。
	$("#user_save_btn").click(function(){
		//1、模态框中填写的表单数据提交给服务器进行保存
		//1、先对要提交给服务器的数据进行校验
		if($(this).attr("ajax-va")=="error"){
			return false;
		}
		if(!validate_login_form()){
			return false;
		};			
		//2、发送ajax请求保存员工
		$.ajax({
			url:"${APP_PATH}/registerUser",
			type:"POST",
			data:$("#empAddModal form").serialize(),
			success:function(result){
				//alert(result.msg);
				if(result.code == 100){
					//注册成功；
					//1、关闭模态框
					$("#empAddModal").modal('hide');					
					
					window.location.href="${APP_PATH}/listOrder?userName="+result.extend.userName;
				}else{
					//显示失败信息
					//console.log(result);
					//有哪个字段的错误信息就显示哪个字段的；
					if(undefined != result.extend.errorFields){
						if(undefined != result.extend.errorFields.userName){
							//用户名
							show_validate_msg("#userName_add_input", "error", result.extend.errorFields.userName);
						}
						if(undefined != result.extend.errorFields.password){
							//密码
							show_validate_msg("#password_add_input", "error", result.extend.errorFields.password);
						}
					}			
				}       
			}
		});
	});
	
	
	
	</script>
</body>
</html>