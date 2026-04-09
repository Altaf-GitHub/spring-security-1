package com.erdinfotech.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager securityManager() {
        UserDetails user1 = User.
                withUsername("altaf")
                .password(encoder().encode("altaf123"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.
                withUsername("faizi")
                .password(encoder().encode("faizi123"))
                .roles("MANAGER")
                .build();

        UserDetails user3 = User.
                withUsername("liyakat")
                .password(encoder().encode("liyakat123"))
                .roles("USER")
                .build();

        UserDetails user4 = User.
                withUsername("asif")
                .password(encoder().encode("asif123"))
                .roles("STUDENT")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }

    /* @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity.authorizeHttpRequests(req -> req.requestMatchers("/contact","/about").permitAll()
                         .anyRequest().authenticated())
                 .httpBasic(Customizer.withDefaults())
                 .formLogin(Customizer.withDefaults());

         return  httpSecurity.build();
     }*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // permit the urls which doesnt requires security
        //authenticate the urls which requires security
        httpSecurity.authorizeHttpRequests(req ->
                        req.requestMatchers("/public/**").permitAll()

                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                //  .requestMatchers("/manager/**").hasRole("MANAGER")
                                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")

                                .requestMatchers("/student/**").hasRole("STUDENT")
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
