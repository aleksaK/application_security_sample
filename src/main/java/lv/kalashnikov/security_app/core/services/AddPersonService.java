package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.database.JpaPersonRepository;
import lv.kalashnikov.security_app.core.domain.Password;
import lv.kalashnikov.security_app.core.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddPersonService {

    @Autowired private JpaPersonRepository repository;

    public Person execute(Person person, Password password) {
        return repository.save(person);
    }

}