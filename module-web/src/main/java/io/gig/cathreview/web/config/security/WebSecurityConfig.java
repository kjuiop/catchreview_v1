package io.gig.cathreview.web.config.security;

import io.gig.catchreview.core.config.security.AbstractSecurityConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author : Jake
 * @date : 2021-10-30
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends AbstractSecurityConfiguration {

    private static final String LOGIN_URL = "/login";

//    @Override
//    public UserDetailsService userDetailsService() {
//        return null;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", LOGIN_URL, "/logout", "/sign-up").permitAll()
                .anyRequest().authenticated();

//        http.exceptionHandling()
//                .authenticationEntryPoint(ajaxAwareAuthenticationEntryPoint(LOGIN_URL));

        http.formLogin()
                .loginPage(LOGIN_URL)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("SESSION")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }
}
