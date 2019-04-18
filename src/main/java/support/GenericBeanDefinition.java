package support;

import factory.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String className;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;

    public GenericBeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return this.singleton;
    }

    public boolean isProtoType() {
        return this.prototype;
    }

    public String getBeanClassName() {
        return this.className;
    }
}
