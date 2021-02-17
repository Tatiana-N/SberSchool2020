package org.nta.lessons.lesson15.solid;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryResults implements ResultsOfQuery {
  DBconnecting dBconnecting;

  public SalaryResults(DBconnecting dBconnecting) {
    this.dBconnecting = dBconnecting;
  }

  public ResultSet getResults(String departmentId, LocalDate dateFrom, LocalDate dateTo) {
    PreparedStatement ps = null;
    try {
      ps = dBconnecting.getConnection().prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
        "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
        " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
      // inject parameters to sql
      ps.setString(0, departmentId);
      ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
      ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));

    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      return ps.executeQuery();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return null;
  }
}