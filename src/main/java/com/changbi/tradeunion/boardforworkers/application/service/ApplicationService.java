package com.changbi.tradeunion.boardforworkers.application.service;

import com.changbi.tradeunion.boardforworkers.application.domain.ApplicationMeta;
import com.changbi.tradeunion.boardforworkers.application.domain.MetaType;
import com.changbi.tradeunion.boardforworkers.application.repository.ApplicationMetaRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationMetaRepository applicationMetaRepository;

    public void fileUploadToS3(){

    }

    public void setMetaInformation(HttpSession session, MetaType metaType) {
        ApplicationMeta applicationMeta = this.findApplicationMetaByType(metaType);
        session.setAttribute(metaType.name(), applicationMeta.getMetaValue());
    }

    public ApplicationMeta findApplicationMetaByType(MetaType metaType){
        return applicationMetaRepository.findByType(metaType);
    }

    public void updateApplicationMeta(MetaType metaType, String newValue){
        ApplicationMeta applicationMeta = findApplicationMetaByType(metaType);
        applicationMeta.updateApplicationMetaValue(newValue);
    }

}
