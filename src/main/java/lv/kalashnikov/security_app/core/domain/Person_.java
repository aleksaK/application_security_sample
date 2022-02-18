package lv.kalashnikov.security_app.core.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
public class Person_ {

    public static volatile SingularAttribute<Person, String> nickname;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Gender> gender;
    public static volatile SingularAttribute<Person, Integer> age;
    public static volatile SingularAttribute<Person, String> email;
    public static volatile SingularAttribute<Person, String> phoneNumber;

}