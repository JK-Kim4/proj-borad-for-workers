/*
package com.changbi.tradeunion.boardforworkers.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
public class HeadTitleAspect {

    @After("execution(String com.changbi.tradeunion.boardforworkers.router..*Router.*(..)) " +
            "&& @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void headTitle(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String menuType;
        String menuDetail;
        String targetUri;


    }

}
*/
