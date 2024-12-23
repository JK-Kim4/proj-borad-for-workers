/*
package com.changbi.tradeunion.boardforworkers.common;

import com.changbi.tradeunion.boardforworkers.common.dto.MailSenderDto;
import com.changbi.tradeunion.boardforworkers.common.utility.ChangbiMailSender;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("loc")
public class MailSenderTest {

    @Autowired
    ChangbiMailSender mailSender;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class changbi_mailsender_test{

        @Nested
        @DisplayName("창비 메일러를 통해 메일을 발송한다.")
        class changbi_mail_send_test{

            @Test @Transactional
            @DisplayName("MailSenderDto객체를 생성하여 메일을 전송한다.")
            void mail_send_test(){
                //given
                MailSenderDto mailSenderDto = MailSenderDto.builder()
                        .body("메일전송 테스트")
                        .to("jw.kim@changbi.com")
                        .title("창비메일러 메일 전송 테스트")
                        .from("dev@changbi.com")
                        .build();

                //when
                boolean result = mailSender.sendMail(mailSenderDto);


                //then
                Assertions.assertTrue(result);
            }

        }
    }

}
*/
