package com.orc.demo.util.interceptor;

import com.orc.demo.util.annotation.LogAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author orckid
 */
@Component
public class AutoInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(AutoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);

        /*if (annotation == null) {
            LOG.info("方法上没有注解，禁止访问....");
            writeReturnMessage(response);
            return false;
        }*/


        return true;
    }

    private void writeReturnMessage(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter writer = response.getWriter()) {
            writer.print("内部错误！");
        } catch (IOException ignored) {}
    }
}
