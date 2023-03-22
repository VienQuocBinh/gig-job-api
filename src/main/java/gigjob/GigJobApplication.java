package gigjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class GigJobApplication {
    public static void main(String[] args) {
        try {
            System.setProperty("spring.devtools.restart.enabled", "false");
            SpringApplication.run(GigJobApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

