<%@ page pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%	
	request.setAttribute("ctx", request.getContextPath());//上下文路径
%>
<jsp:directive.include file="includes/top.jsp" />
<html>
<head>  
    <meta charset="UTF-8"/>  
    <title>CAS单点登录系统</title>  
    <link rel="icon" type="image/x-icon" href="${ctx}/favicon.ico"/>  
    <script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>  
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.2.min.js"></script>  
    <script type="text/javascript" src="${ctx}/js/cas.js"></script>  
    <!--[if lt IE 9]>  
        <script src="${ctx}/js/html5shiv-3.7.2.min.js" type="text/javascript"></script>  
    <![endif]-->  
</head>  
  
<style>  
body {
background-color: #CBE0C9;
margin:50px auto;
}  
#msg {padding:20px; margin-bottom:10px;}  
#msg.errors {border:1px dotted #BB0000; color:#BB0000; padding-left:100px; background:url(${ctx}/images/error.gif) no-repeat 20px center;}  
</style>  
  
<body style="text-align:center;">  
<c:if test="${not pageContext.request.secure}">  
    <div id="msg" class="errors">  
        <h2>Non-secure Connection</h2>  
        <p>You are currently accessing CAS over a non-secure connection.  Single Sign On WILL NOT WORK.  In order to have single sign on work, you MUST log in over HTTPS.</p>  
    </div>  
</c:if>  
<center>
<form:form method="post" commandName="${commandName}" htmlEscape="true">  
    <!--   
    cssClass用于指定表单元素CSS样式名,相当于HTML元素的class属性  
    cssStyle用于指定表单元素样式,相当于HTML元素的style属性  
    cssErrorClass用于指定表单元素发生错误时对应的样式  
    path属性用于绑定表单对象的属性值,它支持级联属性,比如path="user.userName"将调用表单对象getUser.getUserName()绑定表单对象的属性值  
     -->  
    <form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false"/>  
    <input type="hidden" name="lt" value="${loginTicket}"/>  
    <input type="hidden" name="execution" value="${flowExecutionKey}"/>  
    <input type="hidden" name="_eventId" value="submit"/>  
    <table border="9">  
        <tr>  
            <td>  
                <c:if test="${not empty sessionScope.openIdLocalId}">  
                    <strong>${sessionScope.openIdLocalId}</strong>  
                    <input type="hidden" name="username" value="${sessionScope.openIdLocalId}"/>  
                </c:if>  
                <c:if test="${empty sessionScope.openIdLocalId}">  
                    <form:input tabindex="1" path="username" placeholder="帐号" htmlEscape="true" maxlength="16" size="25"/>  
                </c:if>  
            </td>  
        </tr>  
        <tr>  
            <td>  
                <form:password tabindex="2" path="password" placeholder="密码" htmlEscape="true" maxlength="16" size="25"/>  
            </td>  
        </tr> 
         <tr>  
            <td>  
                <form:input tabindex="3" path="captcha" placeholder="验证码" htmlEscape="true" maxlength="4" size="15"/>  
                <img style="cursor:pointer; vertical-align:middle;" src="captcha.jsp" onClick="this.src='captcha.jsp?time'+Math.random();">  
            </td>  
        </tr>  
         <tr>  
            <td>  
                <input type="checkbox" tabindex="4" name="rememberMe" value="true"/>  
                <label for="warn">记住我</label>  
            </td>  
         </tr>   
    
        <tr>  
            <td>  
                <input type="submit" tabindex="5" value="登录"/>  
            </td>  
        </tr>  
    </table>  
</form:form> 
</center> 
</body>  
</html>  