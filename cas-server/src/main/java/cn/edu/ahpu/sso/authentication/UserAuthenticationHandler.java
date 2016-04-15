package cn.edu.ahpu.sso.authentication;

import javax.annotation.Resource;
import javax.security.auth.login.FailedLoginException;

import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
  
public class UserAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {  
	
    @Resource  
    private UserDaoJdbc userDaoJdbc;  
      
    /** 
     * 认证用户名和密码是否正确 
     * @throws FailedLoginException 
     * @see UsernamePasswordCredential参数包含了前台页面输入的用户信息 
     */  
    @Override
	protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential) throws FailedLoginException {
	 	String username = transformedCredential.getUsername();  
        String password = transformedCredential.getPassword();  
         if(userDaoJdbc.verifyAccount(username, password)){  
             return createHandlerResult(transformedCredential, new SimplePrincipal(username), null);  
         }  
         throw new FailedLoginException();  
	}

}  