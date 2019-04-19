package beans.factory.support;

import beans.PropertyValue;
import beans.factory.config.ConfigurableBeanFactory;
import beans.factory.exception.BeanCreationException;
import beans.BeanDefinition;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
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
        Object bean = instantiateBean(beanDefinition);
        populateBean(bean, beanDefinition);
        return bean;
    }

    private Object instantiateBean(BeanDefinition beanDefinition) {
        ClassLoader cl = this.getBeanClassLoader();
        try {
            String beanClassName = beanDefinition.getBeanClassName();
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Bean Definition does not exist!");
        }
    }

    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.isEmpty()) {
            return;
        }
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        SimpleTypeConverter converter = new SimpleTypeConverter();
        try {
            for (PropertyValue i : propertyValues) {
                String propertyName = i.getName();
                Object originalValue = i.getValue();
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor descriptor : descriptors) {
                    if (descriptor.getName().equals(propertyName)) {
                        Object convertedValue = converter.convertIfNecessary(resolvedValue, descriptor.getPropertyType());
                        descriptor.getWriteMethod().invoke(bean, convertedValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class[" + beanDefinition.getBeanClassName() + "]");
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
