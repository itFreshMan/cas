package cn.edu.ahpu.sso.utils;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class PasswordUtils {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	
	/**
	 * 加密算法
	 * @param param
	 * @return
	 */
	public static Map bulidEntryptPassword(Map param){
		String password = MapUtils.getString(param, "PASSWORD");
		if(!StringUtils.isEmpty(password)){
			byte[] salt = Digests.generateSalt(SALT_SIZE);//生成随即salt
			byte[] hashPassword = Digests.sha1(password.toString().getBytes(), salt, HASH_INTERATIONS);
			param.put("PASSWORD", EncodeUtils.hexEncode(hashPassword));
			param.put("SALT", EncodeUtils.hexEncode(salt));
		}
		return param ;
	}
	
	/**
	 * 根据salt+明文密码 获取加密后的密文密码，然后跟数据库的
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String entryptPassword(String password,String salt){
		if(!StringUtils.isEmpty(password) && !StringUtils.isEmpty(salt)){
			byte[] hashPassword = Digests.sha1(password.getBytes(), EncodeUtils.hexDecode(salt), HASH_INTERATIONS);
			return EncodeUtils.hexEncode(hashPassword);
		}
		return "" ;
	}
	
}
