//package com.example.emtlab2.config;
//
//import com.example.emtlab2.service.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Order(200)
//@Configuration
//@Profile("jwt")
//@AllArgsConstructor
//public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder;
//    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
//    private final UserService userService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/assets/**", "/register", "/products", "/api/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//    }
//}
