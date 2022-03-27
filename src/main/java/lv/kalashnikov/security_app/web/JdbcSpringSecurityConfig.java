package lv.kalashnikov.security_app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class JdbcSpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private DataSource dataSource;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        Map<String, PasswordEncoder> encoders = Map.ofEntries(
                Map.entry("bcrypt", new BCryptPasswordEncoder(12)));
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }

    @Bean
    public UserDetailsManager getUserDetailsManager() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(getUserDetailsManager())
                .and()
                .jdbcAuthentication()
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();
    }

}