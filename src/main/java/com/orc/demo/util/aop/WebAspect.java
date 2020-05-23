package com.orc.demo.util.aop;

import com.orc.demo.common.LogOptEnum;
import com.orc.demo.util.annotation.LogAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author orcki
 */
@Aspect
@Component
public class WebAspect {

    private static final Logger LOG = LoggerFactory.getLogger(WebAspect.class);

    @Pointcut("execution(public * com.orc.demo.controller.*.*(..)) && @annotation(com.orc.demo.util.annotation.LogAnnotation)")
    public void webAspect() {}

    @Order(1)
    @After(value = "webAspect()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        ServletRequestAttributes arr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(arr).getRequest();
        String methodName = joinPoint.getSignature().getName();
        String[] str = request.getParameterValues("userName");

        LOG.info("方法执行完成, 方法：{}, 参数：{}", methodName, str);
    }

    @AfterReturning(value = "webAspect()", returning = "ret")
    public void afterReturn(JoinPoint joinPoint, Object ret) {
        // 获取注解
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method == null) {
            return;
        }
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        if (annotation == null) {
            return;
        }

        String methodName = joinPoint.getSignature().getName();
        String params = joinPoint.getArgs()[0].toString();
        String annoValue = annotation.annoValue();
        LogOptEnum optEnum = annotation.optEnum();

        LOG.info("AOP获取注解成功，方法名称：{}. 传参：{}. 注解值：{}. 类型：{}. 返回值：{}", methodName, params, annoValue, optEnum.getMessage(), ret);
    }
}
