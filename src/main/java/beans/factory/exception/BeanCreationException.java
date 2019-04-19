package beans.factory.exception;

public class BeanCreationException extends BeansException {

    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String beanName, String msg) {
        super("Create Bean:" + beanName + msg);
    }
}
