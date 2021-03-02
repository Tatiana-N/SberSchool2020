package org.nta.lessons.lesson18.hw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Dish {
    private int id;
    private String receiptName;
    private int numberOfIngredients;
    private List<Ingredient> list;

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", receiptName='" + receiptName + '\'' +
                ", numberOfIngredients=" + numberOfIngredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;
        Dish dish = (Dish) o;
        return getId() == dish.getId() && getNumberOfIngredients() == dish.getNumberOfIngredients() && getReceiptName().equals(dish.getReceiptName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getReceiptName(), getNumberOfIngredients());
    }
}
