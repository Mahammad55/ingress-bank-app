package az.ingress.bankapp;

import az.ingress.bankapp.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BankAppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
