package beans.factory.impl;

import beans.PropertyValue;
import core.io.ClassPathResource;
import beans.BeanDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 10:11
 * @Version 1.0
 */
public class BeanDefTest {
    private DefaultBeanFactory factory;
    private XmlBeanDefinitionReader reader;

    @BeforeEach
    void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    void testGetBD() {
        reader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        BeanDefinition bd = factory.getBeanDefinition("testService");
        List<PropertyValue> pvs = bd.getPropertyValues();

        PropertyValue pv = this.getPropertyValue("testDao", pvs);

        assertNotNull(pv);
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs) {
        for (PropertyValue i : pvs) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }
}
