package org.smart4j.framework.aop.annotation;

import java.lang.annotation.*;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/8/23 21:19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 包名
     */
    String pkg() default "";

    /**
     * 类名
     */
    String cls() default "";

    /**
     * 注解
     *
     * @since 2.2
     */
    Class<? extends Annotation> annotation() default Aspect.class;
}
