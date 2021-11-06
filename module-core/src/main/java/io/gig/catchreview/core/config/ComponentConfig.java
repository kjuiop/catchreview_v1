package io.gig.catchreview.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author : Jake
 * @date : 2021-10-30
 */
@Configuration
@EntityScan(basePackages = "io.gig.catchreview.core")
@ComponentScan(basePackages ="io.gig.catchreview.core")
@EnableJpaRepositories(basePackages = "io.gig.catchreview.core")
public class ComponentConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
