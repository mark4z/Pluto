package beans.factory.support;

import beans.PropertyValue;
import beans.factory.config.BeanPostProcessor;
import beans.factory.config.ConfigurableBeanFactory;
import beans.factory.config.DependencyDescriptor;
import beans.factory.config.InstantiationAwareBeanPostProcessor;
import beans.factory.exception.BeanCreationException;
import beans.BeanDefinition;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private ClassLoader beanClassLoader;
    List<BeanPostProcessor> beanPostProcessors=new ArrayList<BeanPostProcessor>();

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
        if (beanDefinition.hasContructorArgumentValues()) {
            ConstructorResolver resolver = new ConstructorResolver(this);
            return resolver.autowireConstructor(beanDefinition);
        } else {
            ClassLoader cl = this.getBeanClassLoader();
            try {
                String beanClassName = beanDefinition.getBeanClassName();
                Class<?> clz = cl.loadClass(beanClassName);
                return clz.newInstance();
            } catch (Exception e) {
                throw new BeanCreationException("Bean Definition does not exist!");
            }
        }
    }

    private void populateBean(Object bean, BeanDefinition beanDefinition) {

        for(BeanPostProcessor processor:this.getBeanPostProcessors()){
            ((InstantiationAwareBeanPostProcessor)processor).postProcessPropertyValues(bean, beanDefinition.getId());
        }

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

    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        this.beanPostProcessors.add(postProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public Object resolveDependency(DependencyDescriptor descriptor) {
        Class<?> typeToMatch = descriptor.getDependencyType();
        for (BeanDefinition bd : this.beanDefinitionMap.values()) {
            resolveBeanClass(bd);
            Class<?> beanClass = bd.getBeanClass();
            if (typeToMatch.isAssignableFrom(beanClass)) {
                return this.getBean(bd.getId());
            }
        }
        return null;
    }

    private void resolveBeanClass(BeanDefinition bd) {
        if (bd.hasBeanClass()) {
            return;
        } else {
            try {
                bd.resolveBeanClass(this.getBeanClassLoader());
            } catch (Exception e) {
                throw new RuntimeException("can't load class " + bd.getBeanClassName());
            }
        }
    }
}
