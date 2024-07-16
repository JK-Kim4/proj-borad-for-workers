package com.changbi.tradeunion.boardforworkers.common.utility;

import com.changbi.tradeunion.boardforworkers.common.dto.MailSenderDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class ChangbiMailSender {

    private final String CHANGBI_MAILER_HOST = "mailer.changbimail.com";
    private final String CHANGBI_MAILER_PORT = "6700";

    private final Logger logger = LoggerFactory.getLogger(ChangbiMailSender.class);

    @Value("${changbi.mailsender.username}")
    private String mailSenderUser;

    @Value("${changbi.mailsender.password}")
    private String mailSenderPassword;


    public boolean sendMail(MailSenderDto dto) {
        boolean result = false;
        dto.setMailSenderAccount(mailSenderUser, mailSenderPassword);

        Properties props = this.getMailProperties();

        Session mailSession = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSenderUser, mailSenderPassword);
            }
        });

        Message message = new MimeMessage(mailSession);
        try {

            message.setFrom(new InternetAddress(dto.getFrom(), "테스트메일발송", "utf-8"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.getTo()));
            message.setSubject(dto.getTitle());
            message.setContent(dto.getBody(), dto.getType());

            Transport.send(message);
            result = true;
        }catch (Exception e){
            logger.error("mailSender error", e);
        }

        return result;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", CHANGBI_MAILER_HOST);
        properties.put("mail.smtp.port", CHANGBI_MAILER_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;

    }

}
