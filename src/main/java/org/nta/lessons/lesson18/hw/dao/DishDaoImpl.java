package org.nta.lessons.lesson18.hw.dao;

import org.nta.lessons.lesson18.hw.model.Dish;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.util.List;

public class DishDaoImpl implements Dao<Dish> {
    private final NamedParameterJdbcOperations parameterJdbcOperations;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;
    //private final RowMapper<Dish> dishMapper = new DishMapper();

    public DishDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("dish").usingGeneratedKeyColumns("id");
        this.parameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Dish> getList() {
        return parameterJdbcOperations.query("select * from dish", new BeanPropertyRowMapper<>(Dish.class));
    }

    @Nullable
    @Override
    public Dish getById(int id) {
        return jdbcTemplate.query("SELECT * FROM dish WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Dish.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public Dish create(Dish dish) {
        Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(dish));
        dish.setId((int) number);
        return dish;
    }

    @Override
    public void showAll() {
        jdbcTemplate.queryForList("select * from dish order by receiptName")
                .forEach(System.out::println);
    }

    @Override
    public void deleteById(int id) {
        if (getById(id) != null) {
            jdbcTemplate.update("delete from dish where id = ?", id);
        }

    }

    @Override
    public void update(Dish dish) {
        jdbcTemplate.update(
                "update  dish set receiptName = ?, numberOfIngredients = ?  where id = ?",
                dish.getReceiptName(), dish.getNumberOfIngredients(), dish.getId());
    }
}
