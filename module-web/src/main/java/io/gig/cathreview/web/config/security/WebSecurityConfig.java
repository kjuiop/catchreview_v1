package io.gig.cathreview.web.config.security;

import io.gig.catchreview.core.config.security.AbstractSecurityConfiguration;
import io.gig.catchreview.core.config.security.UserDetailsServiceImpl;
import io.gig.catchreview.core.domain.user.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author : Jake
 * @date : 2021-10-30
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends AbstractSecurityConfiguration {

    private static final String LOGIN_URL = "/login";

    private final DataSource dataSource;
    private final MemberService memberService;
    private final AuthenticationFailureHandlerImpl authenticationFailureHandler;
    private final AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(memberService);
    }

    private AjaxAwareAuthenticationEntryPoint ajaxAwareAuthenticationEntryPoint(String url) {
        return new AjaxAwareAuthenticationEntryPoint(url);
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", LOGIN_URL, "/logout", "/sign-up").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(ajaxAwareAuthenticationEntryPoint(LOGIN_URL));

        http.formLogin()
                .loginPage(LOGIN_URL)
                .failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("SESSION")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }
}
