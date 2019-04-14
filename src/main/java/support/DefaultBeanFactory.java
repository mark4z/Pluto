package support;

import exception.BeanCreationException;
import factory.BeanDefinition;
import factory.BeanFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory {
    private static final String ID = "id";
    private static final String CLASS = "class";
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public DefaultBeanFactory(String var1) {
        loadBeanDefinition(var1);
    }

    public Object getBean(String var1) {
        BeanDefinition beanDefinition = this.getBeanDefinition(var1);
        if (StringUtils.isEmpty(var1)) {
            throw new BeanCreationException("Bean name can't be null!");
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        try {
            String beanClassName = beanDefinition.getBeanClassName();
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("Bean Definition does not exist!");
        }
    }

    public BeanDefinition getBeanDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    public <T> T getBean(String var1, Class<T> var2) {
        return null;
    }

    public Object getBean(String var1, Object... var2) {
        return null;
    }

    public <T> T getBean(Class<T> var1) {
        return null;
    }

    public <T> T getBean(Class<T> var1, Object... var2) {
        return null;
    }

    public boolean containsBean(String var1) {
        return false;
    }

    public boolean isSingleton(String var1) {
        return false;
    }

    public boolean isPrototype(String var1) {
        return false;
    }

    public Class<?> getType(String var1) {
        return null;
    }

    private void loadBeanDefinition(String config) {
        InputStream is;
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(config);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            //<Beans>
            Element root = doc.getRootElement();
            Iterator iter = root.elementIterator();
            while (iter.hasNext()) {
                Element element = (Element) iter.next();
                String id = element.attributeValue(ID);
                String className = element.attributeValue(CLASS);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, className);
                this.beanDefinitionMap.put(id, beanDefinition);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
