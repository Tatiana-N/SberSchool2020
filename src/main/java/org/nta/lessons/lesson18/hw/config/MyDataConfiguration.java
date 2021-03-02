package org.nta.lessons.lesson18.hw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyDataConfiguration {
    @Bean // коннект к базе данных
    DataSource dataSource(){return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
    }
    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @PostConstruct// после сформированного контекста запускает скрипт из файла
    public void makeScript() throws SQLException {
        ScriptUtils.executeSqlScript(dataSource().getConnection(), new ClassPathResource("/dish.sql"));
    }
}
