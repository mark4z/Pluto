package beans.factory.impl;

import beans.factory.annotation.AutowiredFieldElement;
import beans.factory.annotation.InjectionElement;
import beans.factory.annotation.InjectionMetadata;
import beans.factory.support.DefaultBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;
import core.io.ClassPathResource;
import core.io.Resource;
import org.junit.jupiter.api.Test;
import test.TestDao;
import test.TestService;

import java.lang.reflect.Field;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InjectionMetadataTest {

	@Test
	public void testInjection() throws Exception{
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("beansAuto.xml");
		reader.loadBeanDefinitions(resource);
		
		Class<?> clz = TestService.class;
		LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
		
		{
			Field f = TestService.class.getDeclaredField("testDao");
			InjectionElement injectionElem = new AutowiredFieldElement(f,true,factory);
			elements.add(injectionElem);
		}
		
		InjectionMetadata metadata = new InjectionMetadata(clz,elements);

		TestService testService = new TestService();
		
		metadata.inject(testService);
		
		assertTrue(testService.getTestDao() instanceof TestDao);

	}
}