package com.changbi.tradeunion.boardforworkers.common.interceptor;

import com.changbi.tradeunion.boardforworkers.application.domain.MetaType;
import com.changbi.tradeunion.boardforworkers.application.service.ApplicationService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApplicationMetaFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(ApplicationMetaFilter.class);
    private final ApplicationService service;

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

        if(session.getAttribute(MetaType.SERVICE_NAME.name()) == null){
            service.setMetaInformation(session, MetaType.SERVICE_NAME);
        }

        if(session.getAttribute(MetaType.SERVICE_ICON.name()) == null){
            service.setMetaInformation(session, MetaType.SERVICE_ICON);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
