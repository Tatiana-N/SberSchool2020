package org.nta.lessons.lesson18.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

public class TableNotExistsException extends SQLErrorCodeSQLExceptionTranslator {
    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        if (sqlEx.getErrorCode() == 42102) {
            return new DataAccessException("Запрос в неизвестную таблицу. Вот запрос:" + sql) {};
        }
        return super.customTranslate(task, sql, sqlEx);
    }

}
