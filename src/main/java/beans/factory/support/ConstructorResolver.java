package beans.factory.support;

import beans.BeanDefinition;
import beans.ConstructorArgument;
import beans.factory.config.ConfigurableBeanFactory;
import beans.factory.exception.BeanCreationException;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @Author: Marcus
 * @Date: 2019/4/22 14:05
 * @Version 1.0
 */
public class ConstructorResolver {
    private final ConfigurableBeanFactory beanFactory;

    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object autowireConstructor(BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        try {
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(bd.getBeanClassName());
        } catch (Exception e) {
            throw new BeanCreationException(bd.getId() + " Instantiation of bean failed");
        }
        Constructor[] candidates = beanClass.getConstructors();
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory);

        ConstructorArgument cargs = bd.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        for (int i = 0; i < candidates.length; i++) {
            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != cargs.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            boolean result = this.valuesMatchTypes(parameterTypes, cargs.getArgumentValues(), argsToUse, valueResolver, typeConverter);
            if (result) {
                constructorToUse = candidates[i];
                break;
            }
        }
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getId(), "can't find a appropriate constructor");
        }
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(bd.getId(), "can't find a create instance using");
        }
    }

    private boolean valuesMatchTypes(Class<?>[] parameterTypes, List<ConstructorArgument.ValueHolder> valueHolders, Object[] argsToUse, BeanDefinitionValueResolver valueResolver, SimpleTypeConverter typeConverter) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);

            Object originalValue = valueHolder.getValue();
            try {
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
