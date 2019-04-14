package support;

import factory.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {
    private String id;
    private String className;

    public GenericBeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getBeanClassName() {
        return this.className;
    }
}
