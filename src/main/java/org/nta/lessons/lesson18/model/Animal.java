package org.nta.lessons.lesson18.model;

import java.util.Objects;

public class Animal implements Tabled{
    private Integer id;
    private String name;
    private String type;
    private String table;
    private Person person;
    private byte[] photo;

    public static Animal create(String name, String type) {
        Animal animal = new Animal();
        animal.setName(name);
        animal.setType(type);
        animal.setTable("Animal");

        return animal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                (person != null ? ", person={id:" + person.getId() + '}' : "") +
                (photo != null ? ", имеется фотография" : "") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return id.equals(animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
