package beans.factory.config;

import beans.factory.BeanFactory;

import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 15:15
 * @Version 1.0
 */
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();

    void addBeanPostProcessor(BeanPostProcessor postProcessor);

    List<BeanPostProcessor> getBeanPostProcessors();
}
