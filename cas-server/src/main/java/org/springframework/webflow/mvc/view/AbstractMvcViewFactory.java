package org.springframework.webflow.mvc.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.ExpressionParser;
import org.springframework.util.StringUtils;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.ViewFactory;

public abstract class AbstractMvcViewFactory
  implements ViewFactory
{
  private Expression viewId;
  private FlowViewResolver viewResolver;
  private ExpressionParser expressionParser;
  private ConversionService conversionService;
  private Validator validator;
  private BinderConfiguration binderConfiguration;
  private String eventIdParameterName;
  private String fieldMarkerPrefix;
  private MessageCodesResolver messageCodesResolver;

  public AbstractMvcViewFactory(Expression viewId, FlowViewResolver viewResolver, ExpressionParser expressionParser, ConversionService conversionService, BinderConfiguration binderConfiguration, MessageCodesResolver messageCodesResolver)
  {
    this.viewId = viewId;
    this.viewResolver = viewResolver;
    this.expressionParser = expressionParser;
    this.conversionService = conversionService;
    this.binderConfiguration = binderConfiguration;
    this.messageCodesResolver = messageCodesResolver;
    
    initSsoLoginPageConfigCache(); //增加初始化方法;
  }

  public void setEventIdParameterName(String eventIdParameterName) {
    this.eventIdParameterName = eventIdParameterName;
  }

  public void setFieldMarkerPrefix(String fieldMarkerPrefix) {
    this.fieldMarkerPrefix = fieldMarkerPrefix;
  }

  public void setValidator(Validator validator) {
    this.validator = validator;
  }

 public  HashMap<String,String> ssoLoginPageConfigCache = new HashMap<String,String>();
 /**
  *加载根据不同的关键字，指定不同的viewId
  */
 public void initSsoLoginPageConfigCache() {
	  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("app_loign_page_config.properties");
		Properties props = new Properties();
		try {
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Entry entry : props.entrySet()){
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			ssoLoginPageConfigCache.put(key, value);
		}
		
  }
 
 /**
  * 判断service中参数中,是否存在关键字key;
  * @param url
  * @param key
  * @return
  */
  private boolean isThizAppUrl(String url ,String key){
		 if(!StringUtils.isEmpty(url)){
		    	String thizAppUrlProps = ssoLoginPageConfigCache.get(key);
		    	if(!StringUtils.isEmpty(thizAppUrlProps)){
		    		String[] thizAppUrlKeys = thizAppUrlProps.split(",");
		    		if(thizAppUrlKeys != null && thizAppUrlKeys.length > 0){
		    			for(String urlKey : thizAppUrlKeys){
		    				if(url.contains(urlKey)){
		    					return true;
		    				}
		    			}
		    		}
		    	}
		    }
		
		return false;
	}
  
  public org.springframework.webflow.execution.View getView(RequestContext context) {
    String viewId = (String)this.viewId.getValue(context);
    
    //根据不同的key,指定不同的login页面 -------------begin
    if("login".equals(context.getActiveFlow().getId())){
    	String service = context.getRequestParameters().get("service");
    	if(isThizAppUrl(service, "IOS_LOGIN_URL_KEYS")){
    		viewId = ssoLoginPageConfigCache.get("IOS_LOGIN_VIEW_ID");
    	}
    }
  //根据不同的key,指定不同的login页面 ######################end
    
    org.springframework.web.servlet.View view = this.viewResolver.resolveView(viewId, context);

    AbstractMvcView mvcView = createMvcView(view, context);
    mvcView.setExpressionParser(this.expressionParser);
    mvcView.setConversionService(this.conversionService);
    mvcView.setBinderConfiguration(this.binderConfiguration);
    mvcView.setMessageCodesResolver(this.messageCodesResolver);
    mvcView.setValidator(this.validator);
    if (StringUtils.hasText(this.eventIdParameterName)) {
      mvcView.setEventIdParameterName(this.eventIdParameterName);
    }
    if (StringUtils.hasText(this.fieldMarkerPrefix)) {
      mvcView.setFieldMarkerPrefix(this.fieldMarkerPrefix);
    }
    ViewActionStateHolder stateHolder = (ViewActionStateHolder)context.getFlashScope().get(
      "viewUserEventState");
    if (stateHolder != null) {
      mvcView.restoreState(stateHolder);
    }
    return mvcView;
  }

  protected abstract AbstractMvcView createMvcView(org.springframework.web.servlet.View paramView, RequestContext paramRequestContext);
}