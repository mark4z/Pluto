package factory.impl;

import exception.BeanCreationException;
import factory.BeanFactory;
import org.junit.jupiter.api.Test;
import pojo.Hello;
import factory.BeanDefinition;
import support.DefaultBeanFactory;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBeanFactoryTest {
    private BeanFactory beanFactory;


    @Test
    void getBean() {
        this.beanFactory = new DefaultBeanFactory("beans.xml");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("pojo");
        assertEquals("pojo.Hello", beanDefinition.getBeanClassName());
        Hello hello = (Hello) beanFactory.getBean("pojo");
        assertNotNull(hello);
    }
    @Test
    void getInvalidBean() {
        this.beanFactory = new DefaultBeanFactory("beans.xml");
        try{
            Hello hello = (Hello) beanFactory.getBean("???");
        }catch (BeanCreationException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getBean1() {
    }

    @Test
    void getBean2() {
    }

    @Test
    void getBean3() {
    }

    @Test
    void getBean4() {
    }

    @Test
    void containsBean() {
    }

    @Test
    void isSingleton() {
    }

    @Test
    void isPrototype() {
    }

    @Test
    void getType() {
    }
}