package beans.factory.impl;

import beans.factory.propertyeditors.CustomNumberEditor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 13:43
 * @Version 1.0
 */
public class NumberEditorTest {
    @Test
    void main() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
        editor.setAsText("3");
        Object value = editor.getValue();
        assertTrue(value instanceof Integer);
        assertEquals(3, ((Integer) editor.getValue()).intValue());

        editor.setAsText("");
        assertTrue(editor.getValue() == null);
        try {
            editor.setAsText("3.1");
        } catch (Exception e) {
            return;
        }
        fail();
    }
}
