package lv.kalashnikov.security_app.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = Map.ofEntries(
                Map.entry("bcrypt", new BCryptPasswordEncoder(12)));
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    public InMemoryUserDetailsManager getUserDetailsService() {
        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users
                .username("admin")
                .password("{bcrypt}$2a$12$KCbxeRnh164V3lNgBOBH.e.JdmFyg0HjGrGKy7oETLuKVyV0bBX26") //raw password: admin
                .roles("ADMIN")
                .build());
        manager.createUser(users
                .username("user1")
                .password("{bcrypt}$2a$10$SjH4sPLN7wdEEQygLcm7XOP1VLnFgbNlY0CuzUHfIukmD9Opqj8CS") //raw password: user1
                .roles("USER")
                .build());
        manager.createUser(users
                .username("user2")
                .password("{bcrypt}$2a$12$usHOXWGwmz5xxD4iNLzHl.o2mWNW2FJBM8UTNVZwejTkcqbFZRSK") //raw password: user2
                .roles("USER")
                .build());
        manager.createUser(users
                .username("user3")
                .password("{bcrypt}$2a$12$ePXtRP8G39gKqrTPjRrElubUlnje0E9aQCLAG9yt4l5FP9nFIirHG") //raw password: user3
                .roles("USER")
                .build());
        manager.createUser(users
                .username("user4")
                .password("{bcrypt}$2a$12$msJ//RNxCqXcuW7hqKMv8uNh66U0RQzwwbr8lZ2nwgAGDHTTRfP5K") //raw password: user4
                .roles("USER")
                .build());
        return manager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService()).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }

}