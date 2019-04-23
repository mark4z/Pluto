package beans.factory.support;

import beans.BeanDefinition;
import context.annotation.ScannedGenericBeanDefinition;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 15:07
 * @Version 1.0
 */
public interface BeanNameGenerator {
   String generateBeanName(BeanDefinition sbd, BeanDefinitionRegistry registry);
}
