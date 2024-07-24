package com.changbi.tradeunion.boardforworkers.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class PasswordEncodingTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void gen_password(){
        String password = "1234";
        String encode = passwordEncoder.encode(password);

        System.out.println(encode);
    }
}
