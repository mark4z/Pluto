package beans.factory.config;

import beans.factory.BeanFactory;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 15:15
 * @Version 1.0
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
