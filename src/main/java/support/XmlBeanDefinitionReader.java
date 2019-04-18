package support;

import core.io.Resource;
import exception.BeansDefinitionStoreException;
import factory.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @Author: Marcus
 * @Date: 2019/4/18 11:33
 * @Version 1.0
 */
public class XmlBeanDefinitionReader {
    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream is;
        try {
            is = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            //<Beans>
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator();
            while (iter.hasNext()) {
                Element element = (Element) iter.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String className = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
                if (element.attributeValue(SCOPE_ATTRIBUTE) != null) {
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                this.registry.registerBeanDefinition(id, beanDefinition);
            }

        } catch (Exception e) {
            throw new BeansDefinitionStoreException("Io Exception parsing XML document");
        }
    }
}
