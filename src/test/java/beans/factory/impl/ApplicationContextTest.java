package beans.factory.impl;

import context.ApplicationContext;
import org.junit.jupiter.api.Test;
import src.TestService;
import beans.factory.support.ClassPathXmlApplicationContext;
import beans.factory.support.FileSystemXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 13:51
 * @Version 1.0
 */
class ApplicationContextTest {
    @Test
    void getBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
    }

    @Test
    void getBean2() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("C:\\Users\\mengchao.lv\\Desktop\\doc\\Pluto\\src\\test\\resources\\beans.xml");
    }

    @Test
    void setter() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        TestService testService = (TestService) ctx.getBean("testService");
        assertNotNull(testService.getTestDao());
        assertEquals(2,testService.getVersion());
    }
}
