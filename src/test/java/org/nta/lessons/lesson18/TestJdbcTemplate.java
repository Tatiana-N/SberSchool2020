package org.nta.lessons.lesson18;


import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nta.lessons.lesson18.config.JdbcTemplateConfig;
import org.nta.lessons.lesson18.dao.AnimalDao;
import org.nta.lessons.lesson18.model.Animal;
import org.nta.lessons.lesson18.model.ZZZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JdbcTemplateConfig.class)
public class TestJdbcTemplate {

    @BeforeAll
    public static void startServer() throws SQLException {
        Server.createTcpServer().start();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "ANIMAL", "PERSON");
    }

    @Autowired
    private AnimalDao animalDao;

    @Test
    public void showAnimals() {
        Animal catMurka = new Animal();
        catMurka.setName("Мурка");
        catMurka.setType("кот");

        Animal dogJack = new Animal();
        dogJack.setName("Джек");
        dogJack.setType("собака");

        animalDao.createAnimalSimple(catMurka);
        animalDao.createAnimalSimple(dogJack);

        animalDao.showAnimals();
    }

    @Test
    public void createBatchAnimals() {
        Animal crocodileGena = new Animal();
        crocodileGena.setName("Гена");
        crocodileGena.setType("крокодил");

        Animal pigeonGulya = new Animal();
        pigeonGulya.setName("Гуля");
        pigeonGulya.setType("голубь");

        animalDao.createAnimals(crocodileGena, pigeonGulya);

        animalDao.showAnimals();
    }

    @Test
    public void createNamedBatchAnimals() {
        Animal crocodileGena = Animal.create("Гев", "кро");
        Animal pigeonGulya = Animal.create("Гуля2", "голубь");

        animalDao.createNamedAnimals(Arrays.asList(crocodileGena, pigeonGulya));
        animalDao.createNamedAnimals(Arrays.asList(ZZZ.create("513412", "11111"), ZZZ.create("3333", "11111")));

        animalDao.showAnimals();
        animalDao.showZZZ();
    }

    private List<Animal> getSomeAnimal(int cnt) {
        return IntStream.rangeClosed(0, cnt).mapToObj(i -> {
            Animal animal = new Animal();
            animal.setName("A_" + i);
            animal.setType("сущность с номером:" + i);
            return animal;
        }).collect(Collectors.toList());
    }

    @Test
    public void getAllAnimals() {
        List<Animal> animals = getSomeAnimal(10);
        animalDao.createNamedAnimals(animals);

        System.out.println(animalDao.getAllAnimals());
    }

    @Test
    public void customException() {
        Assertions.assertTrue(Assertions.assertThrows(DataAccessException.class, () -> animalDao.errorSelect())
                .getMessage().contains("Запрос в неизвестную таблицу"));
    }

    @Test
    public void createAnimalWithInserter() {
        Animal mooseShisha = new Animal();
        mooseShisha.setName("Шиша");
        mooseShisha.setType("лось");

        Animal bySimpleInsert = animalDao.createAnimalBySimpleInsert(mooseShisha);
        Assertions.assertNotNull(bySimpleInsert.getId());

        System.out.println(bySimpleInsert);
    }

    @Test
    public void createAnimalWithPhoto() {
        Animal animal = new Animal();
        animal.setName("Дружок");
        animal.setType("собака");
        animal.setPhoto(new byte[] {2, 4, 5, 127, 44, -2});
        animalDao.createAnimalWithPhoto(animal);
        System.out.println(animalDao.getAllAnimals());
    }

    @Test
    public void sqlInjection() {
        animalDao.createNamedAnimals(getSomeAnimal(10));

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");

        String arg = "A_1'; DROP table animal; --";
        jdbcTemplate.query("select * from animal where name=?", new Object[] {arg},
                (RowCallbackHandler) System.out::println);

//        Assertions.assertThrows(EmptyResultDataAccessException.class,
//                () -> jdbcTemplate.queryForMap("select * from animal where name=?", arg));

//        jdbcTemplate.queryForMap("select * from animal where name='" + arg + "'")
//                .values().forEach(System.out::print);

        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!");

        animalDao.showAnimals();
    }
}
