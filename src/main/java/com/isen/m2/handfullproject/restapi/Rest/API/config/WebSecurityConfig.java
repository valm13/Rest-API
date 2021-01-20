package com.isen.m2.handfullproject.restapi.Rest.API.config;

import com.isen.m2.handfullproject.restapi.Rest.API.service.DbUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DbUserDetailsService userDetailsService;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/register").permitAll();
        http.authorizeRequests()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/user/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/user/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "GET"))
                    .invalidateHttpSession(true)
                    .permitAll()
                    .and();


    }
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(encoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public UserDetailsService getUserDetails(){
        return new DbUserDetailsService(); // Implementation class
    }
}
