package beans.factory.propertyeditors;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * @Author: Marcus
 * @Date: 2019/4/19 14:53
 * @Version 1.0
 */
public class CustomBooleanEditor extends PropertyEditorSupport {
    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";
    public static final String VALUE_ON = "on";
    public static final String VALUE_OFF = "off";
    public static final String VALUE_YES = "yes";
    public static final String VALUE_NO = "no";
    private final boolean allowEmpty;

    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) {
        String input = (text != null ? text.trim() : null);
        if (this.allowEmpty && !StringUtils.hasLength(text)) {
            setValue(null);
        }
        if ((VALUE_TRUE.equalsIgnoreCase(input)) || (VALUE_ON.equalsIgnoreCase(input)) || (VALUE_YES.equalsIgnoreCase(input))) {
            setValue(Boolean.TRUE);
        } else if ((VALUE_FALSE.equalsIgnoreCase(input)) || (VALUE_OFF.equalsIgnoreCase(input)) || (VALUE_NO.equalsIgnoreCase(input))) {
            setValue(Boolean.FALSE);
        } else {
            throw new IllegalArgumentException("Invalid boolean value['" + text + "']");
        }
    }

    @Override
    public String getAsText() {
        if (Boolean.TRUE.equals(getValue())) {
            return VALUE_TRUE;
        } else if (Boolean.FALSE.equals(getValue())) {
            return VALUE_FALSE;
        }
        return "";
    }
}
