
set schema public;
drop table IF EXISTS INGREDIENT;
drop table IF EXISTS DISHES;
drop table IF EXISTS INGREDIENT_AND_DISHES;

create table DISH (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    receiptName VARCHAR(100) NOT NULL,
    numberOfIngredients INT NOT NULL
);

CREATE UNIQUE INDEX IF NOt EXISTS UNIQUE_DISH ON DISH(receiptName);

create table INGREDIENTS (
                        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                        name VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS UNIQUE_INGREDIENT ON INGREDIENT(name);
create table INGREDIENT_AND_DISHES (
                        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                        DISH_ID INT,
                        INGREDIENTS_ID INT
);

CREATE UNIQUE INDEX IF NOT EXISTS UNIQUE_INGREDIENT_AND_DISHES ON INGREDIENT_AND_DISHES(DISH_ID, INGREDIENTS_ID);

