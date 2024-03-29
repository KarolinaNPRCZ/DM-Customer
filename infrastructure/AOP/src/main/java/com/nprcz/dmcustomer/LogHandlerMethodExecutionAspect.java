package com.nprcz.dmcustomer;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LogHandlerMethodExecutionAspect {
    @Before("@annotation(logHandlerMethodExec)")
    public void logMethodEntry(LogHandlerMethodExec logHandlerMethodExec) {
        Logger logger = Logger.getLogger(logHandlerMethodExec.loggerName());
        String message = logHandlerMethodExec.handlerClazz().getSimpleName()
                + " caught "
                + logHandlerMethodExec.caughtException().getSimpleName();
        logger.warning(message);
    }

    @After("@annotation(logHandlerMethodExec)")
    public void logMethodExit(LogHandlerMethodExec logHandlerMethodExec) {
        Logger logger = Logger.getLogger(logHandlerMethodExec.loggerName());
        String message = logHandlerMethodExec.handlerClazz().getSimpleName()
                + " successfully handled "
                + logHandlerMethodExec.caughtException().getSimpleName();
        logger.info(message);
    }
}
