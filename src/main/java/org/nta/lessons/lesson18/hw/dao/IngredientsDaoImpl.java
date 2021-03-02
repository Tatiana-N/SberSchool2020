package org.nta.lessons.lesson18.hw.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;

public class IngredientsDaoImpl implements Dao<String>{
    @Autowired
    JdbcTemplate jdbcTemplate;
    SimpleJdbcInsert simpleJdbcInsert;

    public IngredientsDaoImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("ingredients").usingGeneratedKeyColumns("id");
    }




    @Override
    public List getList() {
        return null;
    }

    @Override
    public String getById(int id) {
        return null;
    }

    @Override
    public String create(String obj) {
        simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(obj));
        return obj;
    }

    @Override
    public void showAll() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(String obj) {

    }
}
