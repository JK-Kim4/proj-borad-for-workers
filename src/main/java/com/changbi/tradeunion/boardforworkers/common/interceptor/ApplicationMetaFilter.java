package com.changbi.tradeunion.boardforworkers.common.interceptor;

import com.changbi.tradeunion.boardforworkers.application.service.ApplicationMetaService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApplicationMetaFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(ApplicationMetaFilter.class);
    private final ApplicationMetaService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        if(session.getAttribute("serverName") == null){
            service.findAndSetMetaData(session);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
