package com.example.demo.uu.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType; // JWT에 대한 인증 타입
    /*
     * 단순하고 직관적이며 널리 사용되는 "Bearer" 인증 방식
     * 이 인증 방식은 AccessToken을 HTTP요청의 Authorization헤더에 포함하여 전송한다.
     * ex) Authorization: Bearer <access_token>
     */
    private String accessToken;
    private String refreshToken;
}
