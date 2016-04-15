package cn.edu.ahpu.tpc.framework.core.support;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.FactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObjectMapperFactory implements FactoryBean<ObjectMapper> {

	@Override
	public ObjectMapper getObject() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		objectMapper.setDateFormat(dateFormat);
		return objectMapper;
	}

	@Override
	public Class<ObjectMapper> getObjectType() {
		return ObjectMapper.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}