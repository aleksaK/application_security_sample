package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.domain.Gender;
import lv.kalashnikov.security_app.core.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/schema.sql")
public class FindByNicknameServiceTest {

    @Autowired private FindPersonByNicknameService service;
    private final Person person1 = new Person("user3", "Vladimir", Gender.MALE, 65,
            "user3@mail.com", "+371 33333333");

    @Test
    public void findPersonByNicknameSuccess() {
        Optional<Person> result = service.execute("user3");
        assertTrue(result.isPresent());
        assertEquals(person1, result.get());
    }

    @Test
    public void findPersonByNicknameFailed() {
        Optional<Person> result = service.execute("user6");
        assertFalse(result.isPresent());
    }

}