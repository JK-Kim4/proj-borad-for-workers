package com.changbi.tradeunion.boardforworkers.application.service;

import com.changbi.tradeunion.boardforworkers.application.domain.ApplicationMeta;
import com.changbi.tradeunion.boardforworkers.application.domain.MetaType;
import com.changbi.tradeunion.boardforworkers.application.repository.ApplicationMetaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
public class ApplicationMetaService {

    private final ApplicationMetaRepository applicationMetaRepository;

    public void findAndSetMetaData(HttpSession session){

        ApplicationMeta serviceName = applicationMetaRepository.findByType(MetaType.SERVICE_NAME);
        ApplicationMeta serviceIcon = applicationMetaRepository.findByType(MetaType.SERVICE_ICON);


        session.setAttribute(MetaType.SERVICE_NAME.name(), serviceName.getMetaValue());
        session.setAttribute(MetaType.SERVICE_ICON.name(), serviceIcon.getMetaValue());
    }

}
