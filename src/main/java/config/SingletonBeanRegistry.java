package config;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 15:57
 * @Version 1.0
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object object);

    Object getSingleton(String beanName);
}
