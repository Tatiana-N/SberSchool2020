package org.nta.lessons.lesson18.config;

import org.nta.lessons.lesson18.dao.PersonDao;
import org.nta.lessons.lesson18.dao.PersonDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@Import(DataConfiguration.class)
@EnableTransactionManagement
public class TransactionalConfig {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PersonDao personDao(DataSource dataSource, PlatformTransactionManager transactionManager,
                               TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        return new PersonDaoImpl(dataSource, transactionManager, transactionTemplate, jdbcTemplate);
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager) {
        return new TransactionTemplate(platformTransactionManager);
    }
}
