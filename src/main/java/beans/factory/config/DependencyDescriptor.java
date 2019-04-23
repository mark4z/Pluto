package beans.factory.config;

import org.springframework.util.Assert;

import java.lang.reflect.Field;

/**
 * @Author: Marcus
 * @Date: 2019/4/23 16:25
 * @Version 1.0
 */
public class DependencyDescriptor {
    private Field field;
    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        Assert.notNull(field,"Field must does not be null");
        this.field=field;
        this.required=required;
    }

    public Class<?> getDependencyType(){
        if(this.field!=null){
            return field.getType();
        }
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return required;
    }
}
