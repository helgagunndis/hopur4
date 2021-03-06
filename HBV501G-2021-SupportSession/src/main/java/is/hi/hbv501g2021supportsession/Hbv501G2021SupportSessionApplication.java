package is.hi.hbv501g2021supportsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Hbv501G2021SupportSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hbv501G2021SupportSessionApplication.class, args);
    }

}
