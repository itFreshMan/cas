package cn.edu.ahpu.sso.authentication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.ahpu.sso.model.User;

public class UserStubPersonAttributeDao extends StubPersonAttributeDao {  
    @Autowired  
    private UserDaoJdbc userDaoJdbc;  
      
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override  
    public IPersonAttributes getPerson(String uid) {  
        Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();  
		try {
			 User user = userDaoJdbc.getByLoginAcct(uid);  
			 attributes.put("userId", Collections.singletonList((Object)user.getUserId())); 
			attributes.put("loginAcct", Collections.singletonList((Object)user.getLoginAcct()));  
			attributes.put("username", Collections.singletonList((Object)URLEncoder.encode(user.getUsername(), "UTF-8")));
			attributes.put("visitTime", Collections.singletonList((Object)sdf.format(new Date()))); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        return new AttributeNamedPersonImpl(attributes);  
    }  
}
