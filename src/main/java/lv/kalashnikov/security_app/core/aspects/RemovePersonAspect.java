package lv.kalashnikov.security_app.core.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RemovePersonAspect {

    @Autowired
    private UserDetailsManager manager;

    @Pointcut("execution(* lv.kalashnikov.security_app.core.services.RemovePersonService.execute(" +
            "java.lang.String))" +
            " && args(nickname)")
    public void performance(String nickname) {
    }

    @AfterReturning("performance(nickname)")
    public void deleteAuthorizationData(String nickname) {
        manager.deleteUser(nickname);
    }

}