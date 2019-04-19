package beans;

import beans.factory.exception.TypeMismatchException;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 13:39
 * @Version 1.0
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
