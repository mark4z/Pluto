package beans.factory.config;

import beans.factory.BeanFactory;
import beans.factory.config.DependencyDescriptor;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 16:21
 * @Version 1.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    Object resolveDependency(DependencyDescriptor descriptor);
}
