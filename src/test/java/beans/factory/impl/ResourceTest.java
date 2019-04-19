package beans.factory.impl;

import core.io.ClassPathResource;
import core.io.FileSystemResource;
import core.io.Resource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 14:24
 * @Version 1.0
 */

public class ResourceTest {
    @Test
    void classPathTest() {
        Resource resouce = new ClassPathResource("beans.xml");
        try {
            InputStream is=resouce.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void fileSysTest() {
        Resource resouce = new FileSystemResource("C:\\Users\\mengchao.lv\\Desktop\\doc\\Pluto\\src\\test\\resources\\beans.xml");
        try {
            InputStream is=resouce.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
