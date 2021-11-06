package io.gig.catchreview.admin;

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
 * @author : Jake
 * @date : 2021-11-01
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@Import(ComponentConfig.class)
@RequiredArgsConstructor
public class AdminConsoleApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdminConsoleApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
