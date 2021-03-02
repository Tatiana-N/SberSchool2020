package org.nta.lessons.lesson18.hw.dao;


import org.nta.lessons.lesson18.hw.model.Dish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishMapper implements RowMapper<Dish> {
    @Override
    public Dish mapRow(ResultSet resultSet, int i) throws SQLException {
        Dish dish = new Dish();
        dish.setReceiptName(resultSet.getString("receiptName"));
        dish.setNumberOfIngredients(resultSet.getInt("numberOfIngredients"));
        dish.setId(resultSet.getInt("id"));
        return dish;
    }
}
