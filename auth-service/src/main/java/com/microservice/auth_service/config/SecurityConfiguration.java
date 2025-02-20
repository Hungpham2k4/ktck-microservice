package com.microservice.auth_service.config;
import com.microservice.auth_service.model.Role;
//import com.microservice.auth_service.oauth2.handler.OAuth2AuthenticationFailureHandler;
//import com.microservice.auth_service.oauth2.handler.OAuth2AuthenticationSuccessHandler;
//import com.microservice.auth_service.oauth2.repository.HttpCookieOAuthorizationRequestRepository;
//import com.microservice.auth_service.oauth2.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh-token"
    };

//    @Autowired
//    private CustomOAuth2UserService customOAuth2UserService;

//    @Autowired
//    private HttpCookieOAuthorizationRequestRepository httpCookieOAuthorizationRequestRepository;

//    @Autowired
//    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//
//    @Autowired
//    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

//    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(WHITE_LIST_URL)
                            .permitAll()
                            .requestMatchers("/api/v1/accounts").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                            .anyRequest()
                            .authenticated();
                })
//                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                .oauth2Login()
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorise")
//                .authorizationRequestRepository(httpCookieOAuthorizationRequestRepository)
//                .and()
//                .redirectionEndpoint()
//                .and()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
//                .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .failureHandler(oAuth2AuthenticationFailureHandler);
        return http.build();
    }

}