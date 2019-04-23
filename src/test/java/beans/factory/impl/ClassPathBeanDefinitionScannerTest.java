package beans.factory.impl;

import context.annotation.ScannedGenericBeanDefinition;
import core.annotation.AnnotationAttributes;
import core.annotation.Component;
import beans.BeanDefinition;
import beans.factory.support.DefaultBeanFactory;
import context.annotation.ClassPathBeanDefinitionScanner;
import core.type.AnnotationMetadata;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 14:45
 * @Version 1.0
 */
public class ClassPathBeanDefinitionScannerTest {
    @Test
    void test() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackages = "test";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);

        String annotation = Component.class.getName();
        {
            BeanDefinition bd = factory.getBeanDefinition("testService");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();


            assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            assertEquals("testService", attributes.get("value"));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("testDao");
            assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            assertTrue(amd.hasAnnotation(annotation));
        }
    }
}
