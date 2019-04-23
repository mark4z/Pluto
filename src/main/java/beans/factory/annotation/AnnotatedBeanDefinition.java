package beans.factory.annotation;

import beans.BeanDefinition;
import core.type.AnnotationMetadata;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 14:58
 * @Version 1.0
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {
    AnnotationMetadata getMetadata();
}
