package beans.factory.support;

import beans.ConstructorArgument;
import beans.PropertyValue;
import beans.factory.config.RuntimeBeanReference;
import beans.factory.config.TypedStringValue;
import context.annotation.ClassPathBeanDefinitionScanner;
import core.io.Resource;
import beans.factory.exception.BeansDefinitionStoreException;
import beans.BeanDefinition;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

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
    private static final String PROPERTY_ATTRIBUTE = "property";
    private static final String REF_ATTRIBUTE = "ref";
    private static final String NAME_ATTRIBUTE = "name";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
    private static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";

    private static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";

    private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";


    private BeanDefinitionRegistry registry;
    private static Logger logger = Logger.getLogger(XmlBeanDefinitionReader.class);

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream is;
        try {
            is = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            //<beans>
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator();
            while (iter.hasNext()) {
                Element element = (Element) iter.next();
                String namespaceUri = element.getNamespaceURI();
                if (this.isDefaultNamespace(namespaceUri)) {
                    parseDefaultElement(element);
                } else if (this.isContextNamespace(namespaceUri)) {
                    parseComponentElement(element);
                }
            }

        } catch (Exception e) {
            logger.debug(e.toString());
            throw new BeansDefinitionStoreException("Io Exception parsing XML document");
        }
    }

    private void parseComponentElement(Element element) {
        String basePackages=element.attributeValue(BASE_PACKAGE_ATTRIBUTE);
        ClassPathBeanDefinitionScanner scanner=new ClassPathBeanDefinitionScanner(registry);
        scanner.doScan(basePackages);
    }

    private void parseDefaultElement(Element element) {
        String id = element.attributeValue(ID_ATTRIBUTE);
        String className = element.attributeValue(CLASS_ATTRIBUTE);
        BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
        if (element.attributeValue(SCOPE_ATTRIBUTE) != null) {
            beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
        }
        parserConstructorArgElements(element, beanDefinition);
        parserPropertyElement(element, beanDefinition);
        this.registry.registerBeanDefinition(id, beanDefinition);
    }

    private void parserConstructorArgElements(Element element, BeanDefinition beanDefinition) {
        Iterator iter = element.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iter.hasNext()) {
            Element ele = (Element) iter.next();
            parserConstructorArgElement(ele, beanDefinition);
        }
    }

    private void parserConstructorArgElement(Element ele, BeanDefinition beanDefinition) {
        Object value = parserPropertyValue(ele, beanDefinition, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        beanDefinition.getConstructorArgument().addArgumentValues(valueHolder);
    }

    private void parserPropertyElement(Element element, BeanDefinition beanDefinition) {
        Iterator iter = element.elementIterator(PROPERTY_ATTRIBUTE);
        while (iter.hasNext()) {
            Element propEle = (Element) iter.next();
            String propertyName = propEle.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasText(propertyName)) {
                logger.fatal("Tag 'Property' must have a 'name' attribute");
                return;
            }
            Object val = parserPropertyValue(propEle, beanDefinition, propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName, val);
            beanDefinition.getPropertyValues().add(propertyValue);
        }
    }

    private Object parserPropertyValue(Element element, BeanDefinition beanDefinition, String propertyName) {
        String elementName = (propertyName != null) ? "<property> element for property '" + propertyName + "'" : "<constructor-arg> element";
        boolean hasRefAttribute = (element.attributeValue(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (element.attributeValue(VALUE_ATTRIBUTE) != null);
        if (hasRefAttribute) {
            String refName = element.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + "contains empty 'ref' attribute ");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(element.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        } else {
            throw new RuntimeException(elementName + " muset specify a ref or value");
        }
    }

    private boolean isDefaultNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
    }

    private boolean isContextNamespace(String namespaceUri) {
        return (!StringUtils.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
    }
}
