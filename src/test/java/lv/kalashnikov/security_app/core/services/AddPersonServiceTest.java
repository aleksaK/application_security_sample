package lv.kalashnikov.security_app.core.services;

import lv.kalashnikov.security_app.core.database.JpaPersonRepository;
import lv.kalashnikov.security_app.core.domain.Gender;
import lv.kalashnikov.security_app.core.domain.Password;
import lv.kalashnikov.security_app.core.domain.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddPersonServiceTest {

    @Autowired private AddPersonService service;
    @Autowired private UserDetailsManager manager;
    @Autowired private JpaPersonRepository repository;
    @Autowired private PasswordEncoder encoder;

    private final Person person = new Person("user5", "John", Gender.MALE, 45,
            "user5@mail.com", "+371 55555555");
    private final Password password = new Password("user5");

    @Before
    public void init() {
        service.execute(person, password);
    }

    @After
    public void clear() {
        repository.deleteAll();
        manager.deleteUser("user5");
    }

    @Test
    @Transactional
    public void testNewUserAddedToDatabase() {
        Person expected = repository.getById("user5");
        assertEquals(expected, person);
    }

    @Test
    public void testNewUserAddedToUserDetailsManager() {
        UserDetails userDetails = manager.loadUserByUsername("user5");
        assertEquals("user5", userDetails.getUsername());
        assertTrue(encoder.matches("user5", userDetails.getPassword()));
    }

}