package org.nta.lessons.lesson15.solid;

import java.sql.ResultSet;
import java.time.LocalDate;

public interface ResultsOfQuery {
  ResultSet getResults(String departmentId, LocalDate dateFrom, LocalDate dateTo);
}
