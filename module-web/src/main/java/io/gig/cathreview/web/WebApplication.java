package io.gig.cathreview.web;

import io.gig.cathreview.web.config.ComponentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author : Jake
 * @date : 2021/10/30
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@Import(ComponentConfig.class)
@RequiredArgsConstructor
public class WebApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
