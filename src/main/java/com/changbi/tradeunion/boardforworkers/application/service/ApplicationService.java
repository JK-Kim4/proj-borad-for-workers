package com.changbi.tradeunion.boardforworkers.application.service;

import com.changbi.tradeunion.boardforworkers.application.domain.ApplicationMeta;
import com.changbi.tradeunion.boardforworkers.application.domain.MetaType;
import com.changbi.tradeunion.boardforworkers.application.exception.MetaException;
import com.changbi.tradeunion.boardforworkers.application.repository.ApplicationMetaRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationMetaRepository applicationMetaRepository;

    public void setMetaInformation(HttpSession session, MetaType metaType) {
        ApplicationMeta applicationMeta = this.findApplicationMetaByType(metaType);
        session.setAttribute(metaType.name(), applicationMeta.getMetaValue());
    }

    public ApplicationMeta findApplicationMetaByType(MetaType metaType){
        return applicationMetaRepository.findByType(metaType);
    }

    public String updateApplicationMeta(MetaType metaType, String newValue){
        try {
            ApplicationMeta applicationMeta = findApplicationMetaByType(metaType);
            applicationMeta.updateApplicationMetaValue(newValue);
            return newValue;
        }catch (Exception e){
            logger.error("메타 정보 수정 오류",e);
            throw new MetaException("메타 정보 수정에 실패하였습니다. META_TYPE: " + metaType.name());
        }
    }
}
