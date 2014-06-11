package com.rlrg.utillities.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonDTO {
	public String singularName() default "";
	public String pluralName() default "";
}
