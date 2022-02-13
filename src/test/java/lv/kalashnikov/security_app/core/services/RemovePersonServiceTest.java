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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RemovePersonServiceTest {

    @Autowired private AddPersonService addPersonService;
    @Autowired private RemovePersonService removePersonService;
    @Autowired private UserDetailsManager manager;
    @Autowired private JpaPersonRepository repository;
    @Autowired private PasswordEncoder encoder;

    private final Person person1 = new Person("user5", "John", Gender.MALE, 45,
            "user5@mail.com", "+371 55555555");
    private final Password password1 = new Password("user5");
    private final Person person2 = new Person("user6", "Anna", Gender.FEMALE, 34,
            "user6@mail.com", "+371 66666666");
    private final Password password2 = new Password("user6");

    @Before
    public void init() {
        addPersonService.execute(person1, password1);
        addPersonService.execute(person2, password2);
    }

    @After
    public void clear() {
        repository.deleteAll();
        manager.deleteUser("user5");
        manager.deleteUser("user6");
    }

    @Test
    @Transactional
    public void testUserRemovedFromDatabase1() {
        removePersonService.execute("user5");
        assertTrue(repository.existsById("user6"));
        assertFalse(repository.existsById("user5"));
    }

    @Test
    public void testUserRemovedFromUserDetailsManager1() {
        removePersonService.execute("user5");
        assertTrue(manager.userExists("user6"));
        assertFalse(manager.userExists("user5"));
    }

    @Test
    @Transactional
    public void testUserRemovedFromDatabase2() {
        removePersonService.execute("user5");
        removePersonService.execute("user6");
        assertFalse(repository.existsById("user6"));
        assertFalse(repository.existsById("user5"));
    }

    @Test
    public void testUserRemovedFromUserDetailsManager2() {
        removePersonService.execute("user5");
        removePersonService.execute("user6");
        assertFalse(manager.userExists("user6"));
        assertFalse(manager.userExists("user5"));
    }

}