package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.database.JpaPersonRepository;
import lv.kalashnikov.security_app.core.domain.*;
import lv.kalashnikov.security_app.core.domain.wrappers.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.Map.*;

@Service
@Transactional
public class FindPersonsBySpecificationService {

    @Autowired private JpaPersonRepository repository;

    public List<Person> execute(List<Filter> filters) {

        List<Specification<Person>> specifications = filters.stream()
                .map(this::createSpec)
                .filter(Objects::nonNull)
                .toList();

        return repository.findAll(finalSpecs(specifications));

    }

    private Specification<Person> finalSpecs(List<Specification<Person>> list) {
        return Specification.where(IntStream.rangeClosed(0, list.size() - 1)
                .mapToObj(list::get)
                .reduce(Specification::and).orElse(null));
    }

    private Specification<Person> createSpec(Filter filter) {

        Map<Predicate<Filter>, Specification<Person>> operators = ofEntries(
                entry(test -> test.getIntegerTarget() != null && test.getOperator().equals(">"),
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.greaterThan(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getIntegerTarget() != null && test.getOperator().equals(">="),
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getIntegerTarget() != null && test.getOperator().equals("<"),
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.lessThan(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getIntegerTarget() != null && test.getOperator().equals("<="),
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.lessThanOrEqualTo(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getIntegerTarget() != null && test.getOperator().equals("="),
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.equal(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getStringTarget() != null,
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.like(root.get(filter.getField()), filter.getStringTarget() + "%"))
        );

        return operators.entrySet().stream()
                .filter(entry -> entry.getKey().test(filter))
                .map(Entry::getValue)
                .findAny().orElse(null);

    }

}