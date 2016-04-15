<%@ page pageEncoding="UTF-8"%>  
<%@ page import="java.util.*"%>  
<%	
	request.setAttribute("ctx", request.getContextPath());//上下文路径
%>
<head>
	<title>用户列表</title>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.10.2.min.js"></script>  
 <script>  
	function ssoLogout(){  
	    if(confirm('确定要退出系统吗？')){  
	        top.location.href ='<%=cn.edu.ahpu.sso.client.utils.ConfigUtil.INSTANCE.getProperty("shiroCasLoginOutUrl")%>';  
	    }  
	}  
 	var ctx = "${ctx}";
 	$(function(){
 		var $body = $($("#userListTab tbody")[0]);
 		$.post(ctx+"/user/qryUsers.do",{},function(data,response){
 			if(response && response == 'success'){
	 			if(data && data.length > 0){
	 				$(data).each(function(index,item){
	 					var tr = '<tr>' +
			 						'<td>'+item.user_id+'</td>'+
			 						'<td>'+item.login_acct+'</td>'+
			 						'<td>'+item.user_name+'</td>'+
			 						'<td>'+item.email+'</td>'+
		 						'</tr>';
		 						$body.append(tr);
	 				});
	 			}
 			}
 		});
 	});
</script>  
<style>
	table{
	width:80%;
	text-align:center;
	rder-collapse: collapse;
    border: 1px solid #D3D3D3;
    }
    tr{border: 0;}
	td{border:1px solid black};
</style>
</head>
<body style="background-color:#CBE0C9;">  
	<h1>用户列表  </h1>
	<table id="userListTab" >
		<thead>
			<tr>
				<th>ID</th><th>登录账号</th><th>用户名</th><th>email</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
		
	</table>
	<a href="javascript:ssoLogout();">我要登出</a>
</body>  
  
