package factory.impl;

import context.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import pojo.Hello;
import support.ClassPathXmlApplicationContext;
import support.FileSystemXmlApplicationContext;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 13:51
 * @Version 1.0
 */
public class ApplicationContextTest {
    @Test
    void getBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Hello hello = (Hello) ctx.getBean("pojo");
        Assert.notNull(hello, "null");
    }

    @Test
    void getBean2() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("C:\\Users\\mengchao.lv\\Desktop\\doc\\Pluto\\src\\test\\resources\\beans.xml");
        Hello hello = (Hello) ctx.getBean("pojo");
        Assert.notNull(hello, "null");
    }
}
