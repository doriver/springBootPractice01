package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_sns")
@Getter
@Setter
public class UserSns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "loginId", nullable = false, length = 16)
    private String loginId;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "nickName", nullable = false, length = 16)
    private String nickName;

    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Column(name = "profileImage", length = 128)
    private String profileImage;

    @Column(name = "profileStatusMessage", length = 128)
    private String profileStatusMessage;

    @Column(name = "location", length = 64)
    private String location;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt; // 이렇게하니까 save할때 null로 들어가 버리네?? 왜그럴까?

    @Column(name = "updatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    
    /*
     * columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
     * 
     * TIMESTAMP : 데이터 타입
     * DEFAULT CURRENT_TIMESTAMP : (CURRENT_TIMESTAMP)을 기본값으로 설정 , 새로운 레코드가 삽입될 때 이 컬럼에 값이 지정되지 않으면 자동으로 현재 시각
     */
}
