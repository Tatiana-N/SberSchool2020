package org.nta.lessons.lesson18.dao;

import org.nta.lessons.lesson18.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsertOperations insertOperations;
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

    public PersonDaoImpl(DataSource dataSource,
                         PlatformTransactionManager transactionManager,
                         TransactionTemplate transactionTemplate,
                         JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertOperations = new SimpleJdbcInsert(dataSource)
                .withTableName("Person")
                .usingGeneratedKeyColumns("id");
        this.transactionManager = transactionManager;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Person createPerson(Person person) {
        Number returnKey = insertOperations.executeAndReturnKey(new BeanPropertySqlParameterSource(person));

        person.setId((Integer) returnKey);

        return person;
    }

    @Override
    public void manualTransactionUpdateAge(List<Person> people) {
        TransactionStatus transaction = transactionManager.getTransaction(
                new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));

        try {
            people.forEach(person -> updateAge(person, person.getAge() + 1));
            transactionManager.commit(transaction);
            System.err.println("OK!");
        } catch (Exception e) {
            transactionManager.rollback(transaction);
            System.err.println("ERROR");
        }
    }

    @Override
    public void updateAge(Person person, int age) {
        if (age >= 50) {
            throw new IllegalArgumentException("Слишком старый");
        }

        jdbcTemplate.update("update person set age = ? where id = ?", preparedStatement -> {
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, person.getId());
        });
    }

    @Override
    public void showPersons() {
        jdbcTemplate.queryForList("select * from person").forEach(System.out::println);
    }

    @Override
    public void templateTransactionUpdateAge(List<Person> people) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                people.forEach(person -> updateAge(person, person.getAge() + 1));
            }
        });
    }


    @Override
    @Transactional()
    public void annotationUpdateAge(List<Person> people) {
        people.forEach(person -> updateAge(person, person.getAge() + 1));
    }
}
