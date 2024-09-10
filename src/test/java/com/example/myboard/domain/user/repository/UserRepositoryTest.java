package com.example.myboard.domain.user.repository;

import com.example.myboard.domain.user.Role;
import com.example.myboard.domain.user.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @AfterEach
    public void after(){
        em.clear();
    }

    @Test
    void signUp_Success() throws Exception {
        //given
        User user = User.builder()
                .email("user@example.com")
                .password("password")
                .nickname("user")
                .age(20)
                .city("Seoul")
                .role(Role.USER)
                .build();

        // when
        User savedUser = userRepository.save(user);

        // then
        User findByIdUser = userRepository.findById(savedUser.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        assertThat(findByIdUser).isSameAs(savedUser);
        assertThat(findByIdUser).isEqualTo(user);
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByNickname() {
    }

    @Test
    void findByRefreshToken() {
    }

    @Test
    void findBySocialTypeAndSocialId() {
    }
}