package com.nprcz.dmcustomer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogHandlerMethodExec {
    String loggerName();

    Class<?> handlerClazz();

    Class<? extends Throwable> caughtException();
}
