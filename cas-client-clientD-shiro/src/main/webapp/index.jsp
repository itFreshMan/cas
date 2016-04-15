<%@ page pageEncoding="UTF-8"%>  
<%@ page import="java.util.*"%>  
<%@ page import="java.net.URLDecoder"%>  
<%@ page import="org.apache.shiro.subject.*"%>  
<%@ page import="cn.edu.ahpu.sso.client.utils.ConfigUtil"%>  
<head>
	<title>clientD</title>
 <script>  
	function ssoLogout(){  
	    if(confirm('确定要退出系统吗？')){  
	        //top.location.href ='http://sso.jadyer.com:8080/cas-server-web/logout?service=http://blog.csdn.net/jadyer';  
	        //top.location.href ='http://sso.jadyer.com:8080/cas-server-web/logout?service=http://sso.jadyer.com:8080/cas-server-web/login';  
	        top.location.href ='<%=ConfigUtil.INSTANCE.getProperty("shiroCasLoginOutUrl")%>';  
	    }  
	}  
</script>  
</head>
<body style="background-color:#CBE0C9;">  
    <span style="color:red; font-size:32px; font-weight:bold;">客户端登录成功-clientD</span>  
    <br/>
     <a href="javascript:ssoLogout();">我要登出</a>  
</body>  
  
<hr size="2">  
  
<%  

Subject subject = org.apache.shiro.SecurityUtils.getSubject(); 
List list = subject.getPrincipals().asList();  
String name = (String) list.get(0);  
Map<String, Object> info = (Map<String, Object>)list.get(1);  
for(java.util.Map.Entry<String,Object> entry : info.entrySet()){  
    //服务端返回中文时需要encode,客户端接收显示中文时需要decode,否则会乱码  
    out.print(entry.getKey() + "=" + URLDecoder.decode(entry.getValue().toString(), "UTF-8") + "<br/>");  
}  

/* java.security.Principal principal = request.getUserPrincipal();
out.print("request.getRemoteUser()=" +request.getRemoteUser() + "<br/>");  
out.print("principal.getName()=" + principal.getName() + "<br/>");
//out.print("登录用户：" + attributes.get("userId") + "<br/>");  
out.print("登录时间：" + AssertionHolder.getAssertion().getAuthenticationDate() + "<br/>");  */ 
/* Principal principal = (Principal)request..getUserPrincipal();
out.print("principal.getName()=" + principal.getName() + "<br/>");  
out.print("request.getRemoteUser()=" + request.getRemoteUser() + "<br/>"); */   
   /* AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();  
    Map<String, Object> attributes = principal.getAttributes();  
    out.print("principal.getName()=" + principal.getName() + "<br/>");  
    out.print("request.getRemoteUser()=" + request.getRemoteUser() + "<br/>");  
    out.print("登录用户：" + attributes.get("userId") + "<br/>");  
    out.print("登录时间：" + AssertionHolder.getAssertion().getAuthenticationDate() + "<br/>");  
    out.print("-----------------------------------------------------------------------<br/>");  
    for(Map.Entry<String,Object> entry : attributes.entrySet()){  
        //服务端返回中文时需要encode,客户端接收显示中文时需要decode,否则会乱码  
        out.print(entry.getKey() + "=" + URLDecoder.decode(entry.getValue().toString(), "UTF-8") + "<br/>");  
    }  
    out.print("-----------------------------------------------------------------------<br/>");  
    Map<String, Object> attributes22 = AssertionHolder.getAssertion().getAttributes();  
    for(Map.Entry<String,Object> entry : attributes22.entrySet()){  
        out.print(entry.getKey() + "=" + entry.getValue() + "<br/>");  
    }  
    out.print("-----------------------------------------------------------------------<br/>");  
    Map<String, Object> attributes33 = AssertionHolder.getAssertion().getPrincipal().getAttributes();  
    for(Map.Entry<String,Object> entry : attributes33.entrySet()){  
        out.print(entry.getKey() + "=" + entry.getValue() + "<br/>");  
    }    */
%>  