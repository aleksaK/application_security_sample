package lv.kalashnikov.security_app;

import lv.kalashnikov.security_app.web.SpringWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AppDemo {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebConfig.class);
    }

}