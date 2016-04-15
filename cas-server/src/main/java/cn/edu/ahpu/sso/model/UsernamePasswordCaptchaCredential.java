package cn.edu.ahpu.sso.model;

import org.jasig.cas.authentication.RememberMeUsernamePasswordCredential;
import org.jasig.cas.authentication.UsernamePasswordCredential;


/** 
 * @see CAS服务端RememberMe 
 * @see ------------------------------------------------------------------------------------------------------------------------ 
 * @see 关于RememberMe,可参考官方文档,网址如下(下面两个网址描述的RememberMe实现都是一样的,只是第二个还有其它描述) 
 * @see http://jasig.github.io/cas/development/installation/Configuring-LongTerm-Authentication.html 
 * @see http://jasig.github.io/cas/4.0.x/installation/Configuring-Authentication-Components.html#long-term-authentication 
 * @see RememberMe也就是平时所说的记住密码的功能,可以让用户登录成功后,关闭浏览器再重新打开浏览器访问应用时不需要再次登录 
 * @see RememberMe与上面的Session超时配置tgt.timeToKillInSeconds是两回事,Session超时是针对一次会话而言,RememberMe则更广 
 * @see 另外本文的CAS-4.0.3服务端源码修改,是在我的以下三篇博文基础上修改的,最终我会在CSDN上提供整体源码下载 
 * @see http://blog.csdn.net/jadyer/article/details/46875393 
 * @see http://blog.csdn.net/jadyer/article/details/46914661 
 * @see http://blog.csdn.net/jadyer/article/details/46916169 
 * @see 具体修改步骤如下 
 * @see 1.cas.properties中新增配置项rememberMeDuration=1209600 
 * @see 2.ticketExpirationPolicies.xml中新增RememberMe过期策略的配置 
 * @see 3.ticketGrantingTicketCookieGenerator.xml中新增属性项p:rememberMeMaxAge="${rememberMeDuration:1209600}" 
 * @see 4.deployerConfigContext.xml 
 * @see 5.casLoginView.jsp表单中增加rememberMe字段 
 * @see 6.login-webflow.xml增加接收表单rememberMe字段的配置 
 * @see 7.UsernamePasswordCaptchaCredential.java集成RememberMeUsernamePasswordCredential使得可以接收表单的rememberMe字段 
 * @see ------------------------------------------------------------------------------------------------------------------------ 
 * @create 2015-7-28 下午7:58:08 
 * @author 玄玉<http://blog.csdn.net/jadyer> 
 */  
/** 
 * 自定义的接收登录验证码的实体类 
 * @create 2015-7-14 下午4:28:33 
 * @author 玄玉<http://blog.csdn.net/jadyer> 
 */  
//public class UsernamePasswordCaptchaCredential extends UsernamePasswordCredential { 
public class UsernamePasswordCaptchaCredential extends  RememberMeUsernamePasswordCredential{  
    private static final long serialVersionUID = 8317889802836113837L;  
      
    private String captcha;  
  
    public String getCaptcha() {  
        return captcha;  
    }  
  
    public void setCaptcha(String captcha) {  
        this.captcha = captcha;  
    }  
}  
