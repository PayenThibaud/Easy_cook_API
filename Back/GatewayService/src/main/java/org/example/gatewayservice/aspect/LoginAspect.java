package org.example.gatewayservice.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class LoginAspect {
    @Before("execution(* org.example.gatewayservice.controller.loginController.*.*(..))")
    public void testToken() {
        RestClient<String> restClient = new RestClient<>("http://localhost:"+ PortAPI.portAuth +"/api/test");
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = servletRequest.getHeader("Authorization");
        if(!restClient.testToken(token, String.class)) {
            throw new RuntimeException();
        }
    }
}
