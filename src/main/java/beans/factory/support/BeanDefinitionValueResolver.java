package beans.factory.support;

import beans.factory.config.RuntimeBeanReference;
import beans.factory.config.TypedStringValue;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 11:13
 * @Version 1.0
 */
public class BeanDefinitionValueResolver {
    private final DefaultBeanFactory beanFactory;

    public BeanDefinitionValueResolver(DefaultBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object val) {
        if (val instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) val;
            String refName = ref.getBeanName();
            return this.beanFactory.getBean(refName);
        } else if (val instanceof TypedStringValue) {
            TypedStringValue typed = (TypedStringValue) val;
            return typed.getValue();
        } else {
            //TODO
            throw new RuntimeException("the value " + val + " has not implemented");
        }
    }
}
