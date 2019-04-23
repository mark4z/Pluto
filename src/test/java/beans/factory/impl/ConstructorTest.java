package beans.factory.impl;

import beans.BeanDefinition;
import beans.factory.support.ConstructorResolver;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;
import core.io.ClassPathResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.TestService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/22 14:00
 * @Version 1.0
 */
public class ConstructorTest {
    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @BeforeEach
    void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
    }

    @Test
    void main() {
        BeanDefinition bd=factory.getBeanDefinition("testServiceC");
        ConstructorResolver resolver=new ConstructorResolver(factory);
        TestService testService = (TestService) resolver.autowireConstructor(bd);

        assertEquals(2,testService.getVersion());
        assertNotNull(testService.getTestDao());
    }
}
