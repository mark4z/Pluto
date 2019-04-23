package beans.factory.impl;

import beans.factory.annotation.AutowiredAnnotationProcessor;
import beans.factory.annotation.AutowiredFieldElement;
import beans.factory.annotation.InjectionElement;
import beans.factory.annotation.InjectionMetadata;
import beans.factory.config.DependencyDescriptor;
import beans.factory.support.DefaultBeanFactory;
import org.junit.jupiter.api.Test;
import test.TestDao;
import test.TestService;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AutowiredAnnotationProcessorTest {
	TestDao testDao=new TestDao();;
	DefaultBeanFactory beanFactory = new DefaultBeanFactory(){
		@Override
		public Object resolveDependency(DependencyDescriptor descriptor){
			if(descriptor.getDependencyType().equals(TestDao.class)){
				return testDao;
			}
			throw new RuntimeException("can't support types except AccountDao and ItemDao");
		}
	};
	
	

	@Test
	public void testGetInjectionMetadata(){
		
		AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
		processor.setBeanFactory(beanFactory);
		InjectionMetadata injectionMetadata = processor.buildAutowiringMetadata(TestService.class);
		List<InjectionElement> elements = injectionMetadata.getInjectionElements();
		assertEquals(1, elements.size());
		
		assertFieldExists(elements,"testDao");
		TestService testService = new TestService();
		
		injectionMetadata.inject(testService);
		
		assertTrue(testService.getTestDao() instanceof  TestDao);
	}

	private void assertFieldExists(List<InjectionElement> elements ,String fieldName){
		for(InjectionElement ele : elements){
			AutowiredFieldElement fieldEle = (AutowiredFieldElement)ele;
			Field f = fieldEle.getField();
			if(f.getName().equals(fieldName)){
				return;
			}
		}
		fail(fieldName + "does not exist!");
	}
	
	

}