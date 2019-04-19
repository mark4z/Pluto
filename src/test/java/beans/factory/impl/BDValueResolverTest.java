package beans.factory.impl;

import beans.factory.config.RuntimeBeanReference;
import beans.factory.config.TypedStringValue;
import core.io.ClassPathResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.TestDao;
import beans.factory.support.BeanDefinitionValueResolver;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 10:56
 * @Version 1.0
 */
public class BDValueResolverTest {
    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @BeforeEach
    void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
    }

    @Test
    void ref() {
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        RuntimeBeanReference reference = new RuntimeBeanReference("testDao");
        Object value = resolver.resolveValueIfNecessary(reference);

        assertNotNull(value);
        assertTrue(value instanceof TestDao);
    }

    @Test
    void typed() {
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        TypedStringValue stringValue = new TypedStringValue("test");
        Object value = resolver.resolveValueIfNecessary(stringValue);

        assertNotNull(value);
        assertEquals("test", value);
    }
}
