package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.database.JpaPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RemovePersonService {

    @Autowired private JpaPersonRepository repository;

    public void execute(String nickname) {
        repository.deleteById(nickname);
    }

}