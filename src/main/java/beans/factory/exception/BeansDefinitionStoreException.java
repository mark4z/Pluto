package beans.factory.exception;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 10:20
 * @Version 1.0
 */
public class BeansDefinitionStoreException extends RuntimeException {
    public BeansDefinitionStoreException(String msg) {
        super(msg);
    }

    public BeansDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
