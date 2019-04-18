package support;

import context.ApplicationContext;
import core.io.ClassPathResource;
import core.io.Resource;
import exception.BeanCreationException;


/**
 * @Author: Marcus
 * @Date: 2019/4/18 14:17
 * @Version 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String config) {
        super(config);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }
}
