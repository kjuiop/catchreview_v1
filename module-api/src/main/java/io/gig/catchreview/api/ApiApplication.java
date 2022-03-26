package io.gig.catchreview.api;

import io.gig.catchreview.core.config.ComponentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author : JAKE
 * @date : 2022/03/26
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@Import(ComponentConfig.class)
@RequiredArgsConstructor
public class ApiApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

}
