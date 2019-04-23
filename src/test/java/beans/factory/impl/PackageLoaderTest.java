package beans.factory.impl;

import core.io.Resource;
import core.io.support.PackageResourceLoader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 10:06
 * @Version 1.0
 */
public class PackageLoaderTest {
    @Test
    void test(){
        PackageResourceLoader loader=new PackageResourceLoader();
        Resource[] resources=loader.getResources("test");
        assertEquals(2,resources.length);
    }
}
