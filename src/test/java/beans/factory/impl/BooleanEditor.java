package beans.factory.impl;


import beans.factory.propertyeditors.CustomBooleanEditor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 14:52
 * @Version 1.0
 */
public class BooleanEditor {
    @Test
    void main() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);
        editor.setAsText("true");
        assertEquals(true, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("false");
        assertEquals(false, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("on");
        assertEquals(true, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("off");
        assertEquals(false, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("yes");
        assertEquals(true, ((Boolean) editor.getValue()).booleanValue());
        editor.setAsText("no");
        assertEquals(false, ((Boolean) editor.getValue()).booleanValue());
        try {
            editor.setAsText("adfasdf");
        } catch (Exception e) {
            return;
        }
        fail();
    }
}
