package org.nta.lessons.lesson18;

import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nta.lessons.lesson18.hw.config.JDBCTemplateConfiguration;
import org.nta.lessons.lesson18.hw.dao.Dao;
import org.nta.lessons.lesson18.hw.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JDBCTemplateConfiguration.class)
public class FirstTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(value = "dishDao")
    private Dao<Dish> dishDao;

    // @Autowired
    // @Qualifier(value = "ingredientsDao")
    // private Dao<String> ingredientsDao;

    @BeforeAll
    public static void startServer() throws SQLException {
        Server.createTcpServer().start();
    }

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "dish");
        JdbcTestUtils.dropTables(jdbcTemplate, "dish");

    }

    Dish dish1 = new Dish();
    Dish dish2 = new Dish();
    Dish dish3 = new Dish();

    public void createTableFromFile() {
        StringBuilder sql = new StringBuilder();
        try (BufferedReader fis = new BufferedReader(new FileReader("C:\\projects\\SberSchool2020\\src\\main\\resources\\dish.sql"))) {
            while (fis.ready()) {
                sql.append(fis.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcTemplate.execute(sql.toString());
    }

    @BeforeEach
    public void createRows() {
        createTableFromFile();
        dish1.setReceiptName("Оливье");
        dish2.setReceiptName("Винегрет");
        dish3.setReceiptName("Запеченный лосось");
        dish1.setNumberOfIngredients(7);
        dish2.setNumberOfIngredients(6);
        dish3.setNumberOfIngredients(3);

        // dish1.setList(Arrays.asList("Картофель","Горошек","Колбаса","Морковь","Огурец","Майонез","Яйца"));
        //  dish1.setList(Arrays.asList("Картофель","Свекла","Капуста","Фасоль","Огурец","Масло подсолнечное"));
        // dish1.setList(Arrays.asList("Филе лосося","Лимон","Оливковое масло"));
        dishDao.create(dish1);
        dishDao.create(dish2);
        dishDao.create(dish3);
    }

    @org.junit.jupiter.api.Test
    public void test() throws SQLException {
        System.out.println("--------------- рецепты ----------------");
        dishDao.showAll();
        System.out.println("--------------- рецепты после DELETE " + dish1.getReceiptName() + " ----------------");
        dishDao.deleteById(1);
        Assertions.assertEquals(dishDao.getList().size(), 2);
        dishDao.showAll();
        dishDao.deleteById(2);
        Assertions.assertEquals(dishDao.getList().size(), 1);
        dishDao.deleteById(3);
        Assertions.assertEquals(dishDao.getList().size(), 0);

    }

    @org.junit.jupiter.api.Test
    public void updateDishesById() {
        System.out.println("--------------- рецепты ----------------");
        dishDao.showAll();
        Dish dish = new Dish();
        dish.setId(2);// который будем заменять
        dish.setReceiptName("Ватрушка");
        dish.setNumberOfIngredients(227);
        dishDao.update(dish);
        System.out.println("--------------- рецепты после UPDATE ----------------");
        dishDao.showAll();
        Assertions.assertEquals(dish, dishDao.getById(dish.getId()));
    }
}
