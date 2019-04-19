package beans.factory.impl;

import beans.TypeConverter;
import beans.factory.exception.TypeMismatchException;
import beans.factory.support.SimpleTypeConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @Author: Marcus
 * @Date: 2019/4/19 15:10
 * @Version 1.0
 */
public class TypeConverterTest {
    @Test
    void toInt() throws TypeMismatchException {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i =converter.convertIfNecessary("3", Integer.class);
        assertEquals(3, i.intValue());

        try {
            converter.convertIfNecessary("3.1", Integer.class);
        } catch (Exception e) {
            return;
        }
        fail();
    }

    @Test
    void toBoolean() throws TypeMismatchException {
        TypeConverter converter = new SimpleTypeConverter();
        Boolean i= converter.convertIfNecessary("true", Boolean.class);
        assertEquals(true, i.booleanValue());

        try {
            converter.convertIfNecessary("3.1", Boolean.class);
        } catch (Exception e) {
            return;
        }
        fail();
    }
}
