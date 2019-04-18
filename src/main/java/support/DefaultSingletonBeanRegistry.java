package support;

import config.SingletonBeanRegistry;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Map;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 15:58
 * @Version 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjects = new ConcurrentReferenceHashMap<String, Object>();

    public void registerSingleton(String beanName, Object object) {
        Assert.notNull(beanName, "bean id must not be null");
        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject != null) {
            throw new IllegalStateException("Can not register bean,bean has bean created!");
        }
        this.singletonObjects.put(beanName, object);
    }

    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
