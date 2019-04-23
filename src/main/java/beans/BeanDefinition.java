package beans;

import java.util.List;

public interface BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "protoType";
    public static final String SCOPE_DEFAULT = "";

    String getScope();

    String getId();

    void setScope(String scope);

    boolean isSingleton();

    boolean isProtoType();

    String getBeanClassName();

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    boolean hasContructorArgumentValues();

    Class<?> getBeanClass();

    boolean hasBeanClass();

    Class<?> resolveBeanClass(ClassLoader beanClassLoader) throws ClassNotFoundException;
}