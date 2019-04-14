package factory;


import exception.BeanCreationException;

public interface BeanFactory {
    String FACTORY_BEAN_PREFIX = "&";

    Object getBean(String var1) throws BeanCreationException;

    <T> T getBean(String var1, Class<T> var2);

    Object getBean(String var1, Object... var2);

    <T> T getBean(Class<T> var1);

    <T> T getBean(Class<T> var1, Object... var2);

    boolean containsBean(String var1);

    boolean isSingleton(String var1);

    boolean isPrototype(String var1);

    Class<?> getType(String var1);

    BeanDefinition getBeanDefinition(String id);
}
