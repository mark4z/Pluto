package beans.factory.support;

import core.io.ClassPathResource;
import core.io.Resource;


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
