package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoderPw() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .antMatchers("/user/**").authenticated()
                                .antMatchers("/manager/**").hasRole("ADMIN")
//                                .antMatchers("/manager/**").hasRole("MANAGER")
                                .anyRequest().permitAll()

                )
                .formLogin((formLogin) ->
                                formLogin.loginPage("/loginForm")
                                        .loginProcessingUrl("/login")
                                        .defaultSuccessUrl("/")
                        )
        ;
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
//                        .requestMatchers("/", "/login").permitAll()
//                        .requestMatchers("/test", "test1").authenticated()
//                        .anyRequest().authenticated())
////                .formLogin((formLogin) ->
////                        formLogin.loginPage("/login")
////                                .usernameParameter("id")
////                                .passwordParameter("password")
////                                .loginProcessingUrl("/loginproc")
////                                .defaultSuccessUrl("/success", true))
////                .logout((logoutConfig) ->
////                        logoutConfig.logoutSuccessUrl("/"))
////                .userDetailsService(myUserDetailService)
//        ;
//
//        return http.build();
//    }

}
