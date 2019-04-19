package beans.factory.support;

import core.io.FileSystemResource;
import core.io.Resource;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 14:17
 * @Version 1.0
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {
    public FileSystemXmlApplicationContext(String config) {
        super(config);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
