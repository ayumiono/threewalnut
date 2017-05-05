package mhao.threewalnut.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class DynamicDeployBeans implements ApplicationContextAware{

	protected static final Log logger = LogFactory.getLog(DynamicDeployBeans.class);

	private ApplicationContext ctx;
	private DefaultListableBeanFactory beanFactory;

	@Autowired
	public void setApplicationContext(ApplicationContext ctx) {
		if (!DefaultListableBeanFactory.class.isAssignableFrom(ctx
				.getAutowireCapableBeanFactory().getClass())) {
			throw new IllegalArgumentException(
					"BeanFactory must be DefaultListableBeanFactory type");
		}
		this.ctx = ctx;
		this.beanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
	}

	public void registerBean(Class<?> beanClass) {
		registerBean(null, beanClass);
	}

	public void registerBean(String beanName, Class<?> beanClass) {
		Assert.notNull(beanClass, "register bean class must not null");
		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(beanClass);

		if (StringUtils.hasText(beanName)) {
			beanFactory.registerBeanDefinition(beanName, bd);
		} else {
			BeanDefinitionReaderUtils
					.registerWithGeneratedName(bd, beanFactory);
		}
	}
}