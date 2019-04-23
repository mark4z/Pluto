package context.annotation;

import beans.factory.annotation.AnnotatedBeanDefinition;
import beans.factory.support.GenericBeanDefinition;
import core.type.AnnotationMetadata;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 14:56
 * @Version 1.0
 */

public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;


    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();

        this.metadata = metadata;

        setClassName(this.metadata.getClassName());
    }


    public final AnnotationMetadata getMetadata() {
        return this.metadata;
    }

}