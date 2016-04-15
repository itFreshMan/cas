package cn.edu.ahpu.sso.client.web.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ahpu.sso.client.web.user.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserAction {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index")
	public String index(){
		return "/user/userPage";
	}
	
	@RequestMapping(value={"/qryUsers.action","/qryUsers.do"})
	@ResponseBody
	@RequiresPermissions(value={"user:read"})
	public List<Map<String, Object>> qryUsers(String loginAcct){
		return userService.qryUsers(loginAcct);
	}
}
