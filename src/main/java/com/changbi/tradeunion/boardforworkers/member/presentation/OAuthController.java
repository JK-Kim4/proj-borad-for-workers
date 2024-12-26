package com.changbi.tradeunion.boardforworkers.member.presentation;

import com.changbi.tradeunion.boardforworkers.member.application.NaverOAuth2Service;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.NaverCallback;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final NaverOAuth2Service naverOAuth2Service;

    @GetMapping("/naver-login")
    public void naverLogin(HttpServletResponse response) throws IOException {

        response.sendRedirect(naverOAuth2Service.getAuthRequestUri("code"));
    }

    @GetMapping("/oauth/login")
    public void callbackAndRequestAccessToken(HttpServletRequest request, HttpServletResponse response, NaverCallback naverCallback) throws IOException {

        //응답값으로 포함된 code로 사용자 정보 요청
        System.out.println(naverCallback.toString());
        String accessToken = naverOAuth2Service.requestAccessToken(naverCallback.getCode(), naverCallback.getState());

        System.out.println(accessToken.toString());

        if(accessToken != null) {
            naverOAuth2Service.requestUserProfile(accessToken);
        }

    }

}
