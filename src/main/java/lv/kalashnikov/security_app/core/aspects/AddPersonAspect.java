package lv.kalashnikov.security_app.core.aspects;

import lv.kalashnikov.security_app.core.domain.Password;
import lv.kalashnikov.security_app.core.domain.Person;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddPersonAspect {

    @Autowired private UserDetailsManager manager;
    @Autowired private PasswordEncoder encoder;

    @Pointcut("execution(* lv.kalashnikov.security_app.core.services.AddPersonService.execute(" +
            "lv.kalashnikov.security_app.core.domain.Person, lv.kalashnikov.security_app.core.domain.Password))" +
            " && args(person, password)")
    public void performance(Person person, Password password) {
    }

    @AfterReturning("performance(person, password)")
    public void updateAuthorizationData(Person person, Password password) {
        manager.createUser(User
                .withUsername(person.getNickname())
                .password(encoder.encode(password.getPassword()))
                .roles("USER")
                .build());
    }

}