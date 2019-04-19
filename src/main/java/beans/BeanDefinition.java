package beans;

import java.util.List;

public interface BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "protoType";
    public static final String SCOPE_DEFAULT = "";

    String getScope();

    void setScope(String scope);

    boolean isSingleton();

    boolean isProtoType();

    String getBeanClassName();

    List<PropertyValue> getPropertyValues();
}