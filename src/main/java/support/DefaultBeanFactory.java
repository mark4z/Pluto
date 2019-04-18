package support;

import config.ConfigurableBeanFactory;
import exception.BeanCreationException;
import factory.BeanDefinition;
import factory.BeanFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private ClassLoader beanClassLoader;

    public Object getBean(String var1) {
        BeanDefinition beanDefinition = this.getBeanDefinition(var1);
        if (StringUtils.isEmpty(var1)) {
            throw new BeanCreationException("Bean name can't be null!");
        }
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(var1);
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registerSingleton(var1, bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        ClassLoader cl = this.getBeanClassLoader();
        try {
            String beanClassName = beanDefinition.getBeanClassName();
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Bean Definition does not exist!");
        }
    }

    public BeanDefinition getBeanDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    public void registerBeanDefinition(String id, BeanDefinition bd) {
        this.beanDefinitionMap.put(id, bd);
    }


    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
