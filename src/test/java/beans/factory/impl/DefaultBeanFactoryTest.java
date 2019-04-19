package beans.factory.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;

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

    }
    @Test
    void getPBean() {

    }
}