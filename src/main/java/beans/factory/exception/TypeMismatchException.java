package beans.factory.exception;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 15:22
 * @Version 1.0
 */
public class TypeMismatchException extends Exception {
    private transient Object value;
    private Class<?> requiredType;

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value: " + value + " to type " + requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
