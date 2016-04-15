package cn.edu.ahpu.sso.authentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.ahpu.sso.model.User;
import cn.edu.ahpu.sso.utils.PasswordUtils;

@Repository 
public class UserDaoJdbc {
	 private static final String SQL_USER_INFO = "SELECT * FROM sys_user_INFO WHERE LOGIN_ACCT=? ";  
	 	
	@Autowired
    private JdbcTemplate jdbcTemplate;  
	  
    /** 
     * 验证用户名和密码是否正确 
     * @create 2015-7-17 下午3:56:54 
     * @author 玄玉<http://blog.csdn.net/jadyer> 
     */ 
	@SuppressWarnings("rawtypes")
    public boolean verifyAccount(String loginAcct, String password){  
    	boolean flag = false;
        try{  
             Map userDetails = this.jdbcTemplate.queryForMap(SQL_USER_INFO, new Object[]{loginAcct});  
             if(userDetails != null){
            	 String passwordDb= MapUtils.getString(userDetails, "password");
                 String salt= MapUtils.getString(userDetails, "salt");
         		 String encryptedPassword = PasswordUtils.entryptPassword(password, salt);
//         		 System.out.println(password +"=========="+encryptedPassword);
         		 if(encryptedPassword.equals(passwordDb)){
         			flag = true;
         		 }
             }
        }catch(EmptyResultDataAccessException e){  
        	e.printStackTrace();
        	flag = false;
        }  
        
        return flag;
    }

	public User getByLoginAcct(String loginAcct) {
		return this.jdbcTemplate.queryForObject(SQL_USER_INFO, new Object[]{loginAcct},new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
		        user.setLoginAcct(rs.getString("login_acct"));  
		        user.setUsername(rs.getString("user_name"));  
		        user.setUserId(rs.getLong("user_id"));
		        return user;  
			}
		});
	}  
}
