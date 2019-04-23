package core.annotation;

import java.lang.annotation.*;

/**
 * @Author: Marcus
 * @Date: 2019/4/15 9:55
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Component {
    String value() default "";
}