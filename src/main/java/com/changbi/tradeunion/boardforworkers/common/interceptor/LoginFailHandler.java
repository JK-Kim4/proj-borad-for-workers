package com.changbi.tradeunion.boardforworkers.common.interceptor;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage;

        if(exception instanceof BadCredentialsException
                || exception instanceof UsernameNotFoundException){
            errorMessage = "아이디 또는 비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주세요.";
        }else if(exception instanceof InternalAuthenticationServiceException){
            errorMessage = "내부적으로 발생한 오류로 인해 요청을 처리할 수 없습니다. 지속적인 오류 발생 시 관리자에게 문의해주세요.";
        }else if(exception instanceof AuthenticationCredentialsNotFoundException){
            errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의해주세요.";
        }else{
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의해주세요.";
        }

        errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        setDefaultFailureUrl("/member/sign-in?error=true&errorMessage=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
