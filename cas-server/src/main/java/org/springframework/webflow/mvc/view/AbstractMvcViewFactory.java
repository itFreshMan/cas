package org.springframework.webflow.mvc.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.binding.convert.ConversionService;
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.ExpressionParser;
import org.springframework.util.StringUtils;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.ViewFactory;

public abstract class AbstractMvcViewFactory implements ViewFactory {
	private Expression viewId;
	private FlowViewResolver viewResolver;
	private ExpressionParser expressionParser;
	private ConversionService conversionService;
	private Validator validator;
	private BinderConfiguration binderConfiguration;
	private String eventIdParameterName;
	private String fieldMarkerPrefix;
	private MessageCodesResolver messageCodesResolver;

	public AbstractMvcViewFactory(Expression viewId, FlowViewResolver viewResolver, ExpressionParser expressionParser,
			ConversionService conversionService, BinderConfiguration binderConfiguration,
			MessageCodesResolver messageCodesResolver) {
		this.viewId = viewId;
		this.viewResolver = viewResolver;
		this.expressionParser = expressionParser;
		this.conversionService = conversionService;
		this.binderConfiguration = binderConfiguration;
		this.messageCodesResolver = messageCodesResolver;

		initSsoLoginPageConfigCache(); // 增加初始化方法;
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

	public HashMap<String, String> ssoLoginPageConfigCache = new HashMap<String, String>();

	/**
	 * 加载根据不同的关键字，指定不同的viewId ####配置如下#### iosCasLoginView=ios,iphone,apple
	 */
	private  void initSsoLoginPageConfigCache() {
		Properties props = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream("app_loign_page_config.properties");
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for (Entry entry : props.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			ssoLoginPageConfigCache.put(key, value);
		}
	}

	/**
	 * 判断service中参数中,是否存在关键字key,重定向;
	 * 
	 * @param url
	 * @param defaultViewId
	 * @return
	 */
	private String getConfigedAppLoginPage(String url, String defaultViewId) {
		if (!StringUtils.isEmpty(url)) {
			for (Entry<String, String> entry : ssoLoginPageConfigCache.entrySet()) {
				String viewId = entry.getKey();
				String val = entry.getValue();
				if (!StringUtils.isEmpty(val)) {
					String[] keys = val.split(",");
					if (keys != null && keys.length > 0) {
						for (String key : keys) {
							if (url.contains(key)) {
								return viewId;
							}
						}
					}

				}

			}
		}

		return defaultViewId;
	}

	public org.springframework.webflow.execution.View getView(RequestContext context) {
		String viewId = (String) this.viewId.getValue(context);

		// 根据不同的key,指定不同的login页面 -------------begin
		if ("login".equals(context.getActiveFlow().getId())) {
			String service = context.getRequestParameters().get("service");
			viewId = getConfigedAppLoginPage(service, viewId);
		}
		// 根据不同的key,指定不同的login页面 ######################end

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
		ViewActionStateHolder stateHolder = (ViewActionStateHolder) context.getFlashScope().get("viewUserEventState");
		if (stateHolder != null) {
			mvcView.restoreState(stateHolder);
		}
		return mvcView;
	}

	protected abstract AbstractMvcView createMvcView(org.springframework.web.servlet.View paramView,
			RequestContext paramRequestContext);
}