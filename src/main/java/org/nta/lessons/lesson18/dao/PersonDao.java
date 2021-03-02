package org.nta.lessons.lesson18.dao;

import org.nta.lessons.lesson18.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonDao {
    Person createPerson(Person person);

    void manualTransactionUpdateAge(List<Person> people);

    void updateAge(Person person, int age);

    void showPersons();

    void templateTransactionUpdateAge(List<Person> people);

    @Transactional
    void annotationUpdateAge(List<Person> people);
}
