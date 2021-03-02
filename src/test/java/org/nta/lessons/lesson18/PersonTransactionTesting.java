package org.nta.lessons.lesson18;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nta.lessons.lesson18.config.TransactionalConfig;
import org.nta.lessons.lesson18.dao.PersonDao;
import org.nta.lessons.lesson18.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TransactionalConfig.class)
public class PersonTransactionTesting {

    @Autowired
    public PersonDao personDao;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "animal", "person");
    }

    @Test
    public void nonTransactionUpdate() {
        Person personIvan = new Person();
        personIvan.setAge(48);
        personIvan.setName("Иван");
        personIvan = personDao.createPerson(personIvan);

        Person personVasily = new Person();
        personVasily.setAge(49);
        personVasily.setName("Василий");
        personVasily = personDao.createPerson(personVasily);

        personDao.showPersons();
        try {
            personDao.updateAge(personIvan, 49);
            personDao.updateAge(personVasily, 50);
        } catch (Exception e) {
            System.err.println("Error");
        }
        personDao.showPersons();
    }

    @Test
    public void manualTransactionUpdate() {
        Person personIvan = new Person();
        personIvan.setAge(48);
        personIvan.setName("Иван");
        personIvan = personDao.createPerson(personIvan);

        Person personVasily = new Person();
        personVasily.setAge(49);
        personVasily.setName("Василий");
        personVasily = personDao.createPerson(personVasily);

        personDao.showPersons();
        personDao.manualTransactionUpdateAge(Arrays.asList(personIvan, personVasily));
        personDao.showPersons();
    }

    @Test
    public void templateTransactionUpdate() {
        Person personIvan = new Person();
        personIvan.setAge(48);
        personIvan.setName("Иван");
        personIvan = personDao.createPerson(personIvan);

        Person personVasily = new Person();
        personVasily.setAge(49);
        personVasily.setName("Василий");
        personVasily = personDao.createPerson(personVasily);

        personDao.showPersons();
        try {
            personDao.templateTransactionUpdateAge(Arrays.asList(personIvan, personVasily));
        } catch (Exception e) {
            System.err.println("Error");
        }

        personDao.showPersons();
    }

    @Test
    public void annotationTransactionUpdate() {
        Person personIvan = new Person();
        personIvan.setAge(48);
        personIvan.setName("Иван");
        personIvan = personDao.createPerson(personIvan);

        Person personVasily = new Person();
        personVasily.setAge(49);
        personVasily.setName("Василий");
        personVasily = personDao.createPerson(personVasily);

        personDao.showPersons();
        final Person finalPersonIvan = personIvan;
        final Person finalPersonVasily = personVasily;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> personDao.annotationUpdateAge(Arrays.asList(finalPersonIvan, finalPersonVasily)));

        personDao.showPersons();
    }
}
