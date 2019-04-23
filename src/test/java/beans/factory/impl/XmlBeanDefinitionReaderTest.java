package beans.factory.impl;


import beans.BeanDefinition;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;
import context.annotation.ScannedGenericBeanDefinition;
import core.annotation.AnnotationAttributes;
import core.annotation.Component;
import core.io.ClassPathResource;
import core.io.Resource;
import core.type.AnnotationMetadata;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class XmlBeanDefinitionReaderTest {

    @Test
    public void testParseScanedBean() {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("beansAuto.xml");
        reader.loadBeanDefinitions(resource);
        String annotation = Component.class.getName();

        //下面的代码和ClassPathBeanDefinitionScannerTest重复，该怎么处理？
        {
            BeanDefinition bd = factory.getBeanDefinition("testService");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();


            assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            assertEquals("testService", attributes.get("value"));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("testDao");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();
            assertTrue(amd.hasAnnotation(annotation));
        }
    }
}

