package org.nta.lessons.lesson18.config;


import org.nta.lessons.lesson18.dao.AnimalDao;
import org.nta.lessons.lesson18.dao.AnimalDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

@Configuration
@Import(DataConfiguration.class)
public class JdbcTemplateConfig {

    @Bean
    public AnimalDao animalDao(DataSource dataSource, LobHandler lobHandler, JdbcTemplate jdbcTemplate) {
        return new AnimalDaoImpl(dataSource, lobHandler, jdbcTemplate);
    }
}
