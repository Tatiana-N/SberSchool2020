package org.nta.lessons.lesson18.dao;

import org.nta.lessons.lesson18.exception.TableNotExistsException;
import org.nta.lessons.lesson18.model.Animal;
import org.nta.lessons.lesson18.model.Person;
import org.nta.lessons.lesson18.model.Tabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AnimalDaoImpl implements AnimalDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcOperations parameterJdbcOperations;
    private SimpleJdbcInsertOperations insertOperations;
    private RowMapper<Animal> animalRowMapper;
    private LobHandler lobHandler;
@Autowired
    public AnimalDaoImpl(DataSource dataSource, LobHandler lobHandler, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.parameterJdbcOperations = new NamedParameterJdbcTemplate(dataSource);
        this.insertOperations = new SimpleJdbcInsert(dataSource)
                .withTableName("Animal")
                .usingGeneratedKeyColumns("id");

        this.lobHandler = lobHandler;
        this.animalRowMapper = (resultSet, i) -> {
            Animal animal = new Animal();
            animal.setId(resultSet.getInt(1));
            animal.setName(resultSet.getString(2));
            animal.setType(resultSet.getString(3));
            animal.setPhoto(lobHandler.getBlobAsBytes(resultSet, 4));
            int personId = resultSet.getInt(5);

            if (personId != 0) {
                Person person = new Person();
                person.setId(personId);
                animal.setPerson(person);
            }

            return animal;
        };

        jdbcTemplate.setExceptionTranslator(new TableNotExistsException());
    }

    @Override
    public void createAnimalSimple(Animal animal) {
        parameterJdbcOperations.update("insert into Animal (name, type) values (:name, :type)",
                new BeanPropertySqlParameterSource(animal));
    }

    @Override
    public void createAnimalWithPhoto(Animal animal) {
        jdbcTemplate.execute("insert into animal (name, type, photo) values (?, ?, ?)",
                new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                    @Override
                    protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, animal.getName());
                        preparedStatement.setString(2, animal.getType());
                        lobCreator.setBlobAsBytes(preparedStatement, 3, animal.getPhoto());
                    }
                });
    }

    @Override
    public void showAnimals() {
        jdbcTemplate.queryForList("select * from animal order by type, name").forEach(System.out::println);
    }

    @Override
    public void showZZZ() {
        jdbcTemplate.queryForList("select * from zzz order by type, name").forEach(System.out::println);
    }

    @Override
    public void createAnimals(Animal... animals) {
        jdbcTemplate.batchUpdate("insert into Animal (name, type) values (?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, animals[i].getName());
                preparedStatement.setString(2, animals[i].getType());
            }

            @Override
            public int getBatchSize() {
                return animals.length;
            }
        });
    }

    @Override
    public <T extends Tabled> void createNamedAnimals(List<T> tList) {
        parameterJdbcOperations.batchUpdate("insert into $table (name, type) values (:name, :type)"
                        .replace("$table", tList.get(0).getTable()),
                SqlParameterSourceUtils.createBatch(tList.toArray())); // я привела к массиву
    }

    @Override
    public List<Animal> getAllAnimals() {
        return parameterJdbcOperations.query("select * from animal", this.animalRowMapper);
    }

    @Override
    public void errorSelect() {
        jdbcTemplate.query("select * from animal_table", animalRowMapper);
    }

    @Override
    public Animal createAnimalBySimpleInsert(Animal animal) {
        Number returnKey = insertOperations.executeAndReturnKey(new BeanPropertySqlParameterSource(animal));

        animal.setId((Integer) returnKey);

        return animal;
    }


}
