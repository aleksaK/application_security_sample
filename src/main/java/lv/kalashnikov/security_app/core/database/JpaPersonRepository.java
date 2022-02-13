package lv.kalashnikov.security_app.core.database;

import lv.kalashnikov.security_app.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPersonRepository extends JpaRepository<Person, String> {}