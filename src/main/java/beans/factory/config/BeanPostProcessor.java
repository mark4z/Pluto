package beans.factory.config;

import beans.factory.exception.BeansException;

public interface BeanPostProcessor {

	Object beforeInitialization(Object bean, String beanName) throws BeansException;

	
	Object afterInitialization(Object bean, String beanName) throws BeansException;

}