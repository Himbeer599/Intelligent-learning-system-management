//package com.learn.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // 禁用 CSRF
//                .cors(config -> config.configurationSource(corsConfigurationSource)) // 使用自定义的 CORS 配置
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login").permitAll() // 放行 /login 接口
//                        .anyRequest().authenticated() // 其他接口需要认证
//                );
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://localhost:5173"); // 允许的前端域名
//        config.addAllowedMethod("*"); // 允许所有 HTTP 方法
//        config.addAllowedHeader("*"); // 允许所有请求头
//        config.setAllowCredentials(true); // 是否允许发送 Cookie
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // 配置应用到所有路径
//        return source;
//    }
//}