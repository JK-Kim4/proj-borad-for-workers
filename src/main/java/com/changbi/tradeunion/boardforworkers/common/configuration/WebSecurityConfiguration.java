package com.changbi.tradeunion.boardforworkers.common.configuration;

import com.changbi.tradeunion.boardforworkers.common.interceptor.LoginFailHandler;
import com.changbi.tradeunion.boardforworkers.common.interceptor.LoginSuccessHandler;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final LoginFailHandler loginFailHandler;
    private final LoginSuccessHandler loginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .cors(cors -> cors.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(
                        request -> request
                                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                    .requestMatchers("/",
                                            "/css/**", "/js/**", "/image/**", "/member/**", "/plugin/**",
                                            "/api/member/send-auth", "/api/member/pre-member/save").permitAll()
                                    .requestMatchers("/admin/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                                    .anyRequest().authenticated())
                .formLogin(
                        form -> form
                                    .loginPage("/member/sign-in").permitAll()
                                    .loginProcessingUrl("/login/auth")
                                    .failureHandler(loginFailHandler)
                                    .defaultSuccessUrl("/", true)
                                    .successHandler(loginSuccessHandler)
                                    .usernameParameter("memberEmail")
                                    .passwordParameter("memberPassword"))
                .logout(
                        logout -> logout
                                    .invalidateHttpSession(true)
                                    .deleteCookies("JSESSIONID")
                                    .logoutUrl("/logout")
                                    .logoutSuccessUrl("/"))
                .sessionManagement(
                        session-> session
                                    .maximumSessions(1)
                                    .maxSessionsPreventsLogin(false));

        return http.build();
    }
}
