package cn.edu.ahpu.sso.client.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public enum ConfigUtil {  
    INSTANCE;  
      
    private Properties config;  
      
    private ConfigUtil(){  
        config = new Properties();  
        try {  
            config.load(ConfigUtil.class.getResourceAsStream("/system_config.properties"));  
//            System.out.println("Load /system_config.properties SUCCESS...");  
        } catch (IOException e) {  
//            System.out.println("Load /system_config.properties Error...");  
            e.printStackTrace();  
            throw new ExceptionInInitializerError("加载系统配置文件失败...");  
        }  
    }  
  
    public String getProperty(String key){  
        return config.getProperty(key);  
    }  
      
    public int getPropertyForInt(String key){  
        return Integer.parseInt(config.getProperty(key));  
    }  
      
    /** 
     * 配置文件的键值中含系统属性时的获取方式 
     * @see 若配置文件的某个键值含类似于${user.dir}的写法,如log=${user.dir}/app.log 
     * @see 则可以通过该方法使用系统属性中user.dir的值,替换掉配置文件键值中的${user.dir} 
     * @create 2015-2-2 下午05:22:03 
     * @author 玄玉<http://blog.csdn.net/jadyer> 
     */  
    public String getPropertyBySysKey(String key){  
        String value = config.getProperty(key);  
        if(null!=value && Pattern.compile("\\$\\{\\w+(\\.\\w+)*\\}").matcher(value).find()){  
            String sysKey = value.substring(value.indexOf("${")+2, value.indexOf("}"));  
            value = value.replace("${"+sysKey+"}", System.getProperty(sysKey));  
        }  
        return value;  
    }  
}  