package factory.impl;

import core.io.ClassPathResource;
import factory.BeanDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.Hello;
import support.DefaultBeanFactory;
import support.XmlBeanDefinitionReader;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBeanFactoryTest {
    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @BeforeEach
    void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }


    @Test
    void getSBean() {
        reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        BeanDefinition bd = factory.getBeanDefinition("pojo");
        assertTrue(bd.isSingleton());
        assertFalse(bd.isProtoType());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, bd.getScope());
        assertEquals("pojo.Hello", bd.getBeanClassName());
        Hello hello = (Hello) factory.getBean("pojo");
        Hello hello1 = (Hello) factory.getBean("pojo");
        assertNotNull(hello);
        assertEquals(hello, hello1);
    }
    @Test
    void getPBean() {
        reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        BeanDefinition bd = factory.getBeanDefinition("pojo1");
        assertEquals("pojo.Hello", bd.getBeanClassName());
        Hello hello = (Hello) factory.getBean("pojo1");
        Hello hello1 = (Hello) factory.getBean("pojo1");
        assertNotEquals(hello, hello1);
    }
}