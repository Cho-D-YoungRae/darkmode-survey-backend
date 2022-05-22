package sejong.hci.darkmodesurveybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DarkmodeSurveyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarkmodeSurveyBackendApplication.class, args);
    }

}
