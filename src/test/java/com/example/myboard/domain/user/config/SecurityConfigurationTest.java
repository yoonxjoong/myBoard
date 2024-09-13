package com.example.myboard.domain.user.config;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SecurityConfigurationTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    @AfterEach
    public void after() {
        em.clear();
    }

    @Test
    void 패스워드_암호화() throws Exception {
        String password = "테스트평문";

        String encryptedPassword = passwordEncoder.encode(password);

        assertThat(encryptedPassword).isNotEqualTo(password);
    }

    @Test
    void 패스워드_솔트값_랜덤_암호화() throws Exception {
        String password = "테스트평문";

        String encryptedPassword1 = passwordEncoder.encode(password);
        String encryptedPassword2 = passwordEncoder.encode(password);

        assertThat(encryptedPassword1).isNotEqualTo(encryptedPassword2);
    }

    @Test
    void 패스워드_일치_확인() throws Exception {
        String password = "테스트평문";
        String encodedPassword = passwordEncoder.encode(password);

        boolean match = passwordEncoder.matches(password, encodedPassword);

        assertThat(match).isTrue();
    }

}