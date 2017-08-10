package com.test.util;


import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD, ANNOTATION_TYPE }) //定义约束所支持的目标元素类型
@Retention(RUNTIME) //指定此类型的注释在运行时将通过反射方式可用
@Constraint(validatedBy = CheckCaseValidator.class) //将注释类型标记为约束注释，并指定用于验证注释的元素的验证器
@Documented //使用@CheckCase它将被包含在JavaDoc中的元素注释
public @interface CheckCase {

    String message() default "{com.mycompany.constraints.checkcase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    CaseMode value();//许指定所需的案例模式

}