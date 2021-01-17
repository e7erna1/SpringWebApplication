package com.eterna1.SpringWebApplication.configuration;

import static com.eterna1.SpringWebApplication.domain.security.Authority.ADMINAuth;
import static com.eterna1.SpringWebApplication.domain.security.Authority.ADMINRole;

import com.eterna1.SpringWebApplication.domain.application.SampleData;
import com.eterna1.SpringWebApplication.domain.security.User;
import com.eterna1.SpringWebApplication.repository.application.SampleRepository;
import com.eterna1.SpringWebApplication.repository.security.UserRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Setup implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SampleRepository sampleRepository;

    public Setup(UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        SampleRepository sampleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sampleRepository = sampleRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("username");
        user.setPassword(passwordEncoder.encode("password"));
        user.setAuthorities(List.of(ADMINAuth, ADMINRole));
        user.setIsAccountExpired(true);
        user.setIsAccountLocked(true);
        user.setIsCredentialsExpired(true);
        user.setIsEnabled(true);
        userRepository.save(user);

        SampleData sampleData = new SampleData();
        sampleData.setId(1L);
        sampleData.setName("Sample");
        sampleRepository.save(sampleData);
    }
}
