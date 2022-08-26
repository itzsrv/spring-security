package com.demo.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(auth);

        auth.inMemoryAuthentication()
            .withUser("clerk")
            .password("pass")
            .roles("store_clerk")
            .and()
            .withUser("deptm")
            .password("pass")
            .roles("dept_manager")
            .and()
            .withUser("storem")
            .password("pass")
            .roles("store_manager");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(http);
        http.authorizeRequests()
            .antMatchers("/store").hasRole("store_manager")
            .antMatchers("/dept").hasAnyRole("dept_manager","store_manager")
            .antMatchers("/user").hasAnyRole("store_clerk","dept_manager","store_manager")
            .antMatchers("/","static/css","static/js").permitAll()
            .and()
            .formLogin();

    }
}
