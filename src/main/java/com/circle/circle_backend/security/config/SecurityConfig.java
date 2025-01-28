package com.circle.circle_backend.security.config;

import com.circle.circle_backend.security.filter.CorsFilter;
import com.circle.circle_backend.security.filter.JwtAuthenticationFilter;
import com.circle.circle_backend.security.filter.JwtAuthorizationFilter;
import com.circle.circle_backend.security.service.UserDetailsServiceImpl;
import com.circle.circle_backend.security.utils.JwtTokenUtils;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final CorsFilter corsFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtTokenUtils);
        filter.setAuthenticationManager(authenticationManager()); // AuthenticationManager 설정
        filter.setFilterProcessesUrl("/api/login"); // 커스텀 로그인 URL 설정
        return filter;
    }

    @Bean
    public Filter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtTokenUtils, userDetailsServiceImpl);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // 기본 설정인 SessionCreationPolicy을 사용하지 않고 JWT 사용하기 위함
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 요정 인증 설정
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                        .requestMatchers("/api/**").permitAll() // `/api/users`는 인증 없이 접근 가능
                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                )
                // 필터 순서 설정
                .addFilterBefore(corsFilter, ChannelProcessingFilter.class) // CORS 필터를 가장 앞단에 배치
                .addFilterBefore(jwtAuthenticationFilter(), JwtAuthorizationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
