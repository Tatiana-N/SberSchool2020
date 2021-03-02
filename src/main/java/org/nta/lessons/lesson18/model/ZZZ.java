package org.nta.lessons.lesson18.model;

import java.util.Objects;

public class ZZZ implements Tabled {
    private Integer id;
    private String name;
    private String type;
    private String table;

    public static ZZZ create(String name, String type) {
        ZZZ zzz = new ZZZ();
        zzz.setName(name);
        zzz.setType(type);
        zzz.setTable("ZZZ");

        return zzz;
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "ZZZ{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
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
        ZZZ zzz = (ZZZ) o;
        return id.equals(zzz.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
