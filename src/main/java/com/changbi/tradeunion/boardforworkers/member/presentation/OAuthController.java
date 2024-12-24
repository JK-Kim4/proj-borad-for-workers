package com.changbi.tradeunion.boardforworkers.member.presentation;

import com.changbi.tradeunion.boardforworkers.member.presentation.dto.NaverCallback;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class OAuthController {

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    @Value("${naver.redirect-uri}")
    private String redirectUri;

    @Value("${naver.oauth-uri}")
    private String clientOauthUri;

    @GetMapping("/naver-login")
    public void naverLogin(HttpServletResponse response) throws IOException {

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(clientOauthUri)
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", URLEncoder.encode(redirectUri, "UTF-8"))
                .queryParam("state", URLEncoder.encode("1234", "UTF-8"))
                .queryParam("client_id", clientId)
                .build();

        response.sendRedirect(uriComponents.toString());

    }

    @GetMapping("/oauth/login")
    public void callback(HttpServletRequest request, HttpServletResponse response, NaverCallback naverCallback) throws IOException {

        System.out.println(naverCallback.toString());

    }

}
