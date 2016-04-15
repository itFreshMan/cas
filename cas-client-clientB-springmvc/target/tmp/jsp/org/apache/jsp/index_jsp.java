package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Map;
import java.net.URLDecoder;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.authentication.AttributePrincipal;
import cn.edu.ahpu.sso.client.utils.ConfigUtil;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("  \r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\t<title>clientB</title>\r\n");
      out.write(" <script>  \r\n");
      out.write("\tfunction ssoLogout(){  \r\n");
      out.write("\t    if(confirm('确定要退出系统吗？')){  \r\n");
      out.write("\t        //top.location.href ='http://sso.jadyer.com:8080/cas-server-web/logout?service=http://blog.csdn.net/jadyer';  \r\n");
      out.write("\t        //top.location.href ='http://sso.jadyer.com:8080/cas-server-web/logout?service=http://sso.jadyer.com:8080/cas-server-web/login';  \r\n");
      out.write("\t        top.location.href ='");
      out.print(ConfigUtil.INSTANCE.getProperty("casServerLogoutUrl"));
      out.write("';  \r\n");
      out.write("\t    }  \r\n");
      out.write("\t}  \r\n");
      out.write("</script> \r\n");
      out.write("</head> \r\n");
      out.write("<body style=\"background-color:#CBE0C9;\">  \r\n");
      out.write("    <span style=\"color:red; font-size:32px; font-weight:bold;\">客户端登录成功-clientB</span>  \r\n");
      out.write("    <br/>\r\n");
      out.write("     <a href=\"javascript:ssoLogout();\">我要登出</a>  \r\n");
      out.write("</body>  \r\n");
      out.write("  \r\n");
      out.write("<hr size=\"2\">  \r\n");
      out.write("  \r\n");
  
    AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();  
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
    }  

      out.write(' ');
      out.write(' ');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
