package support;

import context.ApplicationContext;
import core.io.Resource;
import org.springframework.util.ClassUtils;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 15:00
 * @Version 1.0
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String config) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(config);
        reader.loadBeanDefinitions(resource);
        factory.setBeanClassLoader(this.getBeanClassLoader());
    }

    public Object getBean(String var1) {
        return factory.getBean(var1);
    }


    protected abstract Resource getResourceByPath(String path);

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
