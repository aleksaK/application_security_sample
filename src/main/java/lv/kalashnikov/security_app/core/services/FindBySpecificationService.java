package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.database.JpaPersonRepository;
import lv.kalashnikov.security_app.core.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Map.*;

@Service
@Transactional
public class FindBySpecificationService {

    @Autowired private JpaPersonRepository repository;

    public List<Person> execute() {
        return repository.findAll(finalSpecs(getSpecificationListSample()));
    }

    private Specification<Person> finalSpecs(List<Specification<Person>> list) {
        return Specification.where(IntStream.rangeClosed(0, list.size() - 1)
                .mapToObj(list::get)
                .reduce(Specification::and).get());
    }

    private Specification<Person> createSpec(Filter filter) {
        Map<String, Specification<Person>> operators = ofEntries(
                entry(">", (root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThan(root.get(filter.getField()), filter.getTarget())),
                entry(">=", (root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getField()), filter.getTarget())),
                entry("<", (root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThan(root.get(filter.getField()), filter.getTarget())),
                entry("<=", (root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThanOrEqualTo(root.get(filter.getField()), filter.getTarget())),
                entry("=", (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(filter.getField()), filter.getTarget()))
        );
        return operators.get(filter.getOperator());
    }

    private List<Specification<Person>> getSpecificationListSample() {
        List<Filter> filters = List.of(
                new Filter("age", ">", 30),
                new Filter("gender", "=", Gender.MALE.ordinal())
        );
        return filters.stream()
                .map(this::createSpec)
                .toList();
    }

    //    public static Specification<Person> hasAgeOfMoreThen(Integer age) {
//        return ((root, query, criteriaBuilder) ->
//                criteriaBuilder.greaterThan(root.get(Person_.age), age));
//    }
//
//    public static Specification<Person> hasGender(Gender gender) {
//        return ((root, query, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get(Person_.gender), gender));
//    }

}