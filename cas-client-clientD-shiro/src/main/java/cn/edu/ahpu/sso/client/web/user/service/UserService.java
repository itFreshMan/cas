package cn.edu.ahpu.sso.client.web.user.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Set<String> findRoles(String username) {
		return null;
	}

	public Set<String> findPermissions(String username) {
		return null;
	}
	
	public List<Map<String, Object>> qryUsers(String loginAcct) {
		String sql = "SELECT * FROM sys_user_INFO WHERE 1 = 1 ";
		if(!StringUtils.isEmpty(loginAcct)){
			sql += "LOGIN_ACCT='"+loginAcct+"'";
		}
		return jdbcTemplate.queryForList(sql);
	}

}
