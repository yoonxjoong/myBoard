package com.example.myboard.domain.user.repository;

import com.example.myboard.domain.user.SocialType;
import com.example.myboard.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByRefreshToken(String refreshToken);

    /**
     * 소셜 타입과 소셜의 식별값으로 회원을 찾는 메소드
     * 정보 제공을 동의한 순간 DB에 저장해야하지만, 아직 추가 정보(도시, 나이)는 입력받지 않았으므로 유저 객체는 DB에 있지만 추가 정보가 빠진 상태
     * 추가 정보를 받어 회원 가입을 진행 할때 소셜 타입, 식별자로 해당 회원을 찾기 위한 메소드
     * @param socialType 소셜 타입
     * @param socialId 소셜 아이디
     * @return 회원
     */
    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

}
