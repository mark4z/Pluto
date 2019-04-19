package beans.factory.config;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 10:26
 * @Version 1.0
 */
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
