package lv.kalashnikov.security_app.utils;

import lv.kalashnikov.security_app.core.domain.Person;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonGeneratorTest {

    @Test
    public void generatorIsCorrect() {

        PersonGenerator generator = new PersonGenerator(1_000);
        List<Person> persons = generator.generatePersonList();

        assertEquals(1_000, persons.size());
        assertTrue(persons.stream().allMatch(
                person -> person.getGender().ordinal() == 0 ?
                        generator.getMALES_NAMES().contains(person.getName()) :
                        generator.getFEMALE_NAMES().contains(person.getName())));
        assertTrue(persons.stream().allMatch(person -> person.getAge() >= 10 && person.getAge() <= 80));
        assertTrue(persons.stream().allMatch(person -> person.getPhoneNumber().length() == 13));

    }

}