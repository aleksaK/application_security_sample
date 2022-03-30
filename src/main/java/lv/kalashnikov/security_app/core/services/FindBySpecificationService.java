package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.database.JpaPersonRepository;
import lv.kalashnikov.security_app.core.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
                .reduce(Specification::and).orElse(null));
    }

    private Specification<Person> createSpec(Filter filter) {

        Map<Predicate<Filter>, Specification<Person>> operators = ofEntries(
                entry(test -> test.getOperator().equals(">") && test.getIntegerTarget() != null,
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.greaterThan(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getOperator().equals(">=") && test.getIntegerTarget() != null,
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getOperator().equals("<") && test.getIntegerTarget() != null,
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.lessThan(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getOperator().equals("<=") && test.getIntegerTarget() != null,
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.lessThanOrEqualTo(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getOperator().equals("=") && test.getIntegerTarget() != null,
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.equal(root.get(filter.getField()), filter.getIntegerTarget())),
                entry(test -> test.getOperator().equals("=") && test.getStringTarget() != null,
                        (root, query, criteriaBuilder) ->
                                criteriaBuilder.like(root.get(filter.getField()), filter.getStringTarget() + "%"))
        );

        return operators.entrySet().stream()
                .filter(entry -> entry.getKey().test(filter))
                .map(Entry::getValue)
                .findAny().orElse(null);

    }

    private List<Specification<Person>> getSpecificationListSample() {

        Filter filter1 = new Filter();
        filter1.setField("age");
        filter1.setOperator("<=");
        filter1.setIntegerTarget(60);

        Filter filter2 = new Filter();
        filter2.setField("email");
        filter2.setOperator("=");
        filter2.setStringTarget("user1");

        Filter filter4 = new Filter();
        filter4.setField("gender");
        filter4.setOperator("=");
        filter4.setIntegerTarget(Gender.MALE.ordinal());

        Filter filter3 = new Filter();
        filter3.setField("name");
        filter3.setOperator("=");
        filter3.setStringTarget("A");

        List<Filter> filters = List.of(filter1, filter2, filter3, filter4);
        return filters.stream()
                .map(this::createSpec)
                .toList();

    }

}