package com.reminder.mcp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamNullable {
	/**
	 * 是否可以为空
	 * @return
	 */
	public boolean value() default false;
	/**
	 * 参数默认值
	 * @return
	 */
	public String defaultValue() default "";
}
