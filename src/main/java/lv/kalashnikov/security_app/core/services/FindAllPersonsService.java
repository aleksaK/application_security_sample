package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.database.JpaPersonRepository;
import lv.kalashnikov.security_app.core.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FindAllPersonsService {

    @Autowired private JpaPersonRepository repository;
    private @Value("${properties.page.size}") int pageSize;
    private final Sort sort = Sort.by("name").ascending();

    public Page<Person> execute(String page) {
        return repository.findAll(PageRequest.of(Integer.parseInt(page), pageSize, sort));
    }

}