package com.changbi.tradeunion.boardforworkers.member.application;

import com.changbi.tradeunion.boardforworkers.common.domain.enum_type.Platform;
import com.changbi.tradeunion.boardforworkers.member.domain.OAuthMember;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.NaverTokenResponse;
import com.changbi.tradeunion.boardforworkers.member.presentation.dto.NaverUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NaverOAuth2Service {

    @Value("${naver.client-id}")
    private String clientId;

    @Value("${naver.client-secret}")
    private String clientSecret;

    @Value("${naver.redirect-uri}")
    private String redirectUri;

    @Value("${naver.oauth-uri}")
    private String clientOauthUri;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAuthRequestUri(String code) throws UnsupportedEncodingException {

        UUID uuid = UUID.randomUUID();

        //naver 로그인
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(clientOauthUri)
                .queryParam("response_type", code)
                .queryParam("redirect_uri", URLEncoder.encode(redirectUri, "UTF-8"))
                .queryParam("state", URLEncoder.encode(uuid.toString(), "UTF-8"))
                .queryParam("client_id", clientId)
                .build();

        return uriComponents.toString();
    }

    public String requestAccessToken(String code, String state) throws UnsupportedEncodingException {

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("https://nid.naver.com/oauth2.0/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", code)
                .queryParam("state", state)
                .build();
        ResponseEntity<NaverTokenResponse> naverResponse =
                restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, null, NaverTokenResponse.class);

        return naverResponse.getBody().getAccessToken();
    }

    public void requestUserProfile(String accessToken){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<NaverUserResponse> response =
                restTemplate.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.GET, request, NaverUserResponse.class);

        System.out.println(response.getBody().toString());

    }
}
