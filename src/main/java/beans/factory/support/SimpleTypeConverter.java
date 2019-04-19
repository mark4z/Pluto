package beans.factory.support;

import beans.TypeConverter;
import beans.factory.exception.TypeMismatchException;
import beans.factory.propertyeditors.CustomBooleanEditor;
import beans.factory.propertyeditors.CustomNumberEditor;
import org.springframework.util.ClassUtils;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 15:17
 * @Version 1.0
 */
public class SimpleTypeConverter implements TypeConverter {
    private Map<Class<?>, PropertyEditor> defaultEditors;

    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException {
        if (ClassUtils.isAssignableValue(requiredType, value)) {
            return (T) value;
        } else if (value instanceof String) {
            PropertyEditor editor = findDefaultEditor(requiredType);
            try {
                editor.setAsText((String) value);
            } catch (IllegalArgumentException e) {
                throw new TypeMismatchException(value, requiredType);
            }
            return (T) editor.getValue();
        } else {
            throw new RuntimeException("Todo: cna not convert value for " + value + " class: " + requiredType);
        }
    }

    private <T> PropertyEditor findDefaultEditor(Class<T> requiredType) {
        PropertyEditor editor = this.getDefaultEditor(requiredType);
        if (editor == null) {
            throw new RuntimeException("Editor for " + requiredType + " has not been implemented");
        }
        return editor;
    }

    private PropertyEditor getDefaultEditor(Class<?> requiredType) {
        if (this.defaultEditors == null) {
            createDefaultEditors();
        }
        return this.defaultEditors.get(requiredType);
    }

    private void createDefaultEditors() {
        this.defaultEditors = new HashMap<Class<?>, PropertyEditor>();
        this.defaultEditors.put(boolean.class, new CustomBooleanEditor(false));
        this.defaultEditors.put(Boolean.class, new CustomBooleanEditor(true));
        this.defaultEditors.put(int.class, new CustomNumberEditor(Integer.class,false));
        this.defaultEditors.put(Integer.class, new CustomNumberEditor(Integer.class,true));
    }
}
