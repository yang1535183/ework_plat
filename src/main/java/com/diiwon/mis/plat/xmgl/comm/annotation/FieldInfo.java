package com.diiwon.mis.plat.xmgl.comm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldInfo {  /*加上@interface为定义注解*/

    String desc() default ""; //字段描述

    String column() default ""; //字段名称

    boolean isPK() default false;//是否是主键

    String sequence() default "";//sequence主键生成器
}
