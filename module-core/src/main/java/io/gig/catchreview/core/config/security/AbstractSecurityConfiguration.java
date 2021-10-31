package io.gig.catchreview.core.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : Jake
 * @date : 2021-10-30
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractSecurityConfiguration  extends WebSecurityConfigurerAdapter {

//    @Bean
//    public abstract UserDetailsService userDetailsService();


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/node_modules/**" ,"/custom-modules/**", "/js/**", "/css/**", "/images/**", "/fonts/**", "/favicon.ico", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/sign-up").permitAll()
                .anyRequest().authenticated();


        http.formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}
