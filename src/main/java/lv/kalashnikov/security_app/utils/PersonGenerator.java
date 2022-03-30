package lv.kalashnikov.security_app.utils;

import lv.kalashnikov.security_app.core.domain.Gender;
import lv.kalashnikov.security_app.core.domain.Person;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PersonGenerator {

    private final int quantity;
    private static final Random random = new Random();
    private static final List<String> MALES_NAMES = List.of(
            "'Alexander'", "'Vladimir'", "'Dmitry'", "'Arkadiy'", "'Deniss'",
            "'Andrey'", "'Anton'", "'Artur'", "'Vadim'", "'Maxim'", "'Artyom'",
            "'Mikhail'", "'Ivan'", "'Viktor'", "'Kirill'", "'Ilya'", "'Konstantin'",
            "'Yaroslav'", "'Nikolai'", "'Pavel'", "'Janis'", "'Artis'", "'Aldis'",
            "'Valdis'", "'Karlis'", "'Maris'", "'Martins'", "'Ivars'", "'Dainis'",
            "'Kristaps'", "'Oskars'", "'Kaspars'", "'Peteris'", "'Armands'", "'Pauls'",
            "'Valters'", "'Henrijs'", "'Edgars'"
    );
    private static final List<String> FEMALE_NAMES = List.of(
            "'Tatyana'", "'Marina'", "'Yulia'", "'Natalya'", "'Anastasiya'", "'Karina'",
            "'Olga'", "'Zhanna'", "'Yelena'", "'Inga'", "'Ksenya'", "'Galina'", "'Polina'",
            "'Viktorya'", "'Inna'", "'Maria'", "'Sofya'", "'Anna'", "'Oksana'", "'Yekaterina'",
            "'Liga'", "'Marta'", "'Katrina'", "'Madara'", "'Laura'", "'Maija'", "'Elina'",
            "'Greta'", "'Samanta'", "'Lauma'", "'Anete'", "'Linda'", "'Agnese'", "'Anete'",
            "'Sabine'", "'Paula'", "'Emilija'", "'Amanda'"
    );

    public PersonGenerator(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getMALES_NAMES() {
        return MALES_NAMES;
    }

    public List<String> getFEMALE_NAMES() {
        return FEMALE_NAMES;
    }

//    public static void main(String[] args) {
//
//        PersonGenerator generator = new PersonGenerator(1_000);
//        IntStream.rangeClosed(1, generator.quantity)
//                .mapToObj(PersonGenerator::createPerson)
//                .map(PersonGenerator::createQuery)
//                .forEach(System.out::println);
//
//    }

    public List<Person> generatePersonList() {
        return IntStream.rangeClosed(1, quantity)
                .mapToObj(PersonGenerator::createPerson)
                .toList();
    }

    private static String getName(int gender) {
        return gender == 0 ? MALES_NAMES.get(random.nextInt(MALES_NAMES.size())) :
                FEMALE_NAMES.get(random.nextInt(FEMALE_NAMES.size()));
    }

    private static Person createPerson(int index) {
        Person person = new Person();
        person.setNickname("user" + index);
        person.setGender(Gender.valueOf(random.nextInt(2) == 0 ? "MALE" : "FEMALE"));
        person.setName(getName(person.getGender().ordinal()));
        person.setEmail(person.getNickname() + "@mail.com");
        person.setAge((int) (10 + Math.random() * 70));
        person.setPhoneNumber("+371 " + (int) (10_000_000 + Math.random() * 89_999_999));
        return person;
    }

    private static String createQuery(Person person) {
        return "('" + person.getNickname() + "', " + person.getName() + ", " + person.getGender().ordinal() +
                ", " + person.getAge() + ", '" + person.getEmail() + "', '" + person.getPhoneNumber() + "'),";
    }

}