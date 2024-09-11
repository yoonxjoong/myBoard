package com.example.myboard.domain.user.repository;

import com.example.myboard.domain.user.Role;
import com.example.myboard.domain.user.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.logging.log4j.MarkerManager.clear;
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
    public void after() {
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
    void 오류_회원가입시_아이디가_없음() throws Exception {
        User user = User.builder()
                .password("password")
                .nickname("user")
                .age(20)
                .city("Seoul")
                .role(Role.USER)
                .build();

        assertThrows(Exception.class, () -> userRepository.save(user));
    }

    @Test
    void 오류_회원가입시_닉네입이_없음() throws Exception {
        User user = User.builder()
                .email("user@example.com")
                .password("password")
                .age(20)
                .city("Seoul")
                .role(Role.USER)
                .build();

        assertThrows(Exception.class, () -> userRepository.save(user));
    }

    @Test
    void 오류_회원가입시_중복된_아이디가_있음() throws Exception {
        // Given
        User user_1 = User.builder()
                .email("user@example.com")
                .password("password1")
                .nickname("user1")
                .age(21)
                .city("Seoul")
                .role(Role.USER)
                .build();

        User user_2 = User.builder()
                .email("user@example.com")
                .password("password2")
                .nickname("user2")
                .age(22)
                .city("Seoul")
                .role(Role.USER)
                .build();

        userRepository.save(user_1);
        clear();

        // when, then
        assertThrows(Exception.class, () -> userRepository.save(user_2));
    }

    @Test
    void 성공_회원삭제() throws Exception {
        // Given
        User user = User.builder()
                .email("user@example.com")
                .password("password")
                .nickname("user")
                .age(20)
                .city("Seoul")
                .role(Role.USER)
                .build();
        userRepository.save(user);
        clear();

        // When
        userRepository.delete(user);
        clear();

        assertThrows(Exception.class, () -> userRepository.findById(user.getId()).orElseThrow(Exception::new));
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