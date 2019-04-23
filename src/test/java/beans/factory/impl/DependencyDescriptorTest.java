package beans.factory.impl;

import beans.factory.config.DependencyDescriptor;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;
import core.io.ClassPathResource;
import core.io.Resource;
import org.junit.jupiter.api.Test;
import test.TestDao;
import test.TestService;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DependencyDescriptorTest {

	@Test
	public void testResolveDependency() throws Exception{
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("beansAuto.xml");
		reader.loadBeanDefinitions(resource);
		
		Field f = TestService.class.getDeclaredField("testDao");
		DependencyDescriptor  descriptor = new DependencyDescriptor(f,true);
		Object o = factory.resolveDependency(descriptor);
		assertTrue(o instanceof TestDao);
	}

}