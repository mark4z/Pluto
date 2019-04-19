package beans.factory.support;

import beans.BeanDefinition;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 11:37
 * @Version 1.0
 */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String id);

    void registerBeanDefinition(String id, BeanDefinition bd);
}
