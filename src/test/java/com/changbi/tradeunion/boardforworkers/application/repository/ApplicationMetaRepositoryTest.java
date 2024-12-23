package com.changbi.tradeunion.boardforworkers.application.repository;

import com.changbi.tradeunion.boardforworkers.application.domain.ApplicationMeta;
import com.changbi.tradeunion.boardforworkers.application.domain.MetaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.AsynchronousServerSocketChannel;

@SpringBootTest
@ActiveProfiles("loc")
public class ApplicationMetaRepositoryTest {

    @Autowired
    private ApplicationMetaRepository applicationMetaRepository;

    @Test
    @Transactional
    public void application_meta_save_test(){
        String value = "창비그룹 노동조합";
        ApplicationMeta applicationMeta = ApplicationMeta.builder()
                .type(MetaType.SERVICE_NAME)
                .value(value)
                .build();

        applicationMetaRepository.save(applicationMeta);

        ApplicationMeta savedMeta = applicationMetaRepository.findByType(MetaType.SERVICE_NAME);

        Assertions.assertEquals(savedMeta.getMetaValue(), value);
    }
}
