<%@ page pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%	
	request.setAttribute("ctx", request.getContextPath());//上下文路径
%>
<html>
<head>  
    <meta charset="UTF-8"/>  
    <title>ios单点登录系统</title>  
    <link rel="icon" type="image/x-icon" href="${ctx}/favicon.ico"/>  
    <script type="text/javascript" src="${ctx}/js/jquery-1.10.2.min.js"></script>  
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.2.min.js"></script>  
    <script type="text/javascript" src="${ctx}/js/cas.js"></script>  
    <!--[if lt IE 9]>  
        <script src="${ctx}/js/html5shiv-3.7.2.min.js" type="text/javascript"></script>  
    <![endif]-->  
</head>  
  
<body style="text-align:center;">  
	<h1>IOS登录页面</h1>
</body>  
</html>  