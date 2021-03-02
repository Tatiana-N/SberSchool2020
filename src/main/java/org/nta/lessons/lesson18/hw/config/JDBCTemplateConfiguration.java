package org.nta.lessons.lesson18.hw.config;

import org.nta.lessons.lesson18.hw.dao.Dao;
import org.nta.lessons.lesson18.hw.dao.DishDaoImpl;
import org.nta.lessons.lesson18.hw.dao.DishMapper;
import org.nta.lessons.lesson18.hw.dao.IngredientsDaoImpl;
import org.nta.lessons.lesson18.hw.model.Dish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

@Import(MyDataConfiguration.class)
@Configuration
public class JDBCTemplateConfiguration {
    @Bean
    RowMapper<Dish> rowMapper(){
        return new DishMapper();
    }
    @Bean
    public Dao<Dish> dishDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return new DishDaoImpl(jdbcTemplate, dataSource);
    }
    @Bean
    public Dao<String> ingredientsDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return new IngredientsDaoImpl(jdbcTemplate, dataSource);
    }
}
