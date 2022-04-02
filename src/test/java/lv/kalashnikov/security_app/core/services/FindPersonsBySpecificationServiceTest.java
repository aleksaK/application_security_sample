package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.domain.wrappers.Filter;
import lv.kalashnikov.security_app.core.domain.Gender;
import lv.kalashnikov.security_app.core.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/schema_.sql")
public class FindPersonsBySpecificationServiceTest {

    @Autowired private FindPersonsBySpecificationService service;

    @Test
    public void findPersonsBySingleParameter1() {

        Filter filter1 = new Filter();
        filter1.setField("name");
        filter1.setOperator("=");
        filter1.setStringTarget("Alexander");
        List<Filter> filters = List.of(filter1);

        List<Person> actual = service.execute(filters);

        assertEquals(14, actual.size());
        assertTrue(actual.stream().allMatch(person -> person.getName().equals("Alexander")));
        assertTrue(actual.stream().allMatch(person -> person.getGender().ordinal() == 0));

    }

    @Test
    public void findPersonsBySingleParameter2() {

        Filter filter1 = new Filter();
        filter1.setField("email");
        filter1.setOperator("=");
        filter1.setStringTarget("user1");
        List<Filter> filters = List.of(filter1);

        List<Person> actual = service.execute(filters);

        assertEquals(112, actual.size());
        assertTrue(actual.stream().allMatch(person -> person.getEmail().startsWith("user1")));

    }

    @Test
    public void findPersonsByMultipleParameters1() {

        Filter filter1 = new Filter();
        filter1.setField("age");
        filter1.setOperator(">");
        filter1.setIntegerTarget(30);
        Filter filter2 = new Filter();
        filter2.setField("name");
        filter2.setOperator("=");
        filter2.setStringTarget("K");
        List<Filter> filters = List.of(filter1, filter2);

        List<Person> actual = service.execute(filters);

        assertEquals(61, actual.size());
        assertTrue(actual.stream().allMatch(person -> person.getName().startsWith("K")));
        assertTrue(actual.stream().allMatch(person -> person.getAge() > 30));

    }

    @Test
    public void findPersonsByMultipleParameters2() {

        Filter filter1 = new Filter();
        filter1.setField("age");
        filter1.setOperator("<=");
        filter1.setIntegerTarget(60);
        Filter filter2 = new Filter();
        filter2.setField("email");
        filter2.setOperator("=");
        filter2.setStringTarget("user1");
        Filter filter3 = new Filter();
        filter3.setField("name");
        filter3.setOperator("=");
        filter3.setStringTarget("A");
        Filter filter4 = new Filter();
        filter4.setField("gender");
        filter4.setOperator("=");
        filter4.setIntegerTarget(Gender.MALE.ordinal());
        List<Filter> filters = List.of(filter1, filter2, filter3, filter4);

        List<Person> expected = List.of(
                new Person("user120", "Artyom", Gender.MALE, 58, "user120@mail.com", "+371 94583007"),
                new Person("user148", "Anton", Gender.MALE, 55, "user148@mail.com", "+371 86380563"),
                new Person("user156", "Artyom", Gender.MALE, 46, "user156@mail.com", "+371 87511025")
        );

        assertEquals(expected, service.execute(filters));

    }

    @Test
    public void findPersonsByNoParameters() {

        //empty filters
        Filter filter1 = new Filter();
        Filter filter2 = new Filter();
        Filter filter3 = new Filter();
        List<Filter> filters = List.of(filter1, filter2, filter3);

        List<Person> actual = service.execute(filters);

        assertEquals(1000, actual.size());

    }

}