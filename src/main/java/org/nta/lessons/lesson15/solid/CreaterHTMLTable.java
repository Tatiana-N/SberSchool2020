package org.nta.lessons.lesson15.solid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreaterHTMLTable implements CreaterDataForSending {
  private static final String BEGIN_HTML_STRING = "<html><body><table><tr><td>Employee</td><td>Salary</td></tr>";
  private static final String ROW_START_TAG = "<tr>";
  private static final String ROW_END_TAG = "</tr>";
  private static final String CELL_START_TAG = "<td>";
  private static final String CELL_END_TAG = "</td>";
  private static final String END_HTML_STRING = "</table></body></html>";
  ResultSet results;
  List<DataListener> listenerList = new ArrayList<>();
  String result;
  @Override
  public void addListener(DataListener dataListener) {
    listenerList.add(dataListener);
  }

  @Override
  public void deleteListener(DataListener dataListener) {
    listenerList.remove(dataListener);
  }

  @Override
  public String createStringForSending() {
    result = getHTML().toString();
    notifyListeners();
    return result;
  }

  @Override
  public void notifyListeners() {
    for (DataListener dataListener : listenerList) {
      dataListener.onEvent(result);
    }

  }

  public CreaterHTMLTable(ResultSet results) {
    this.results = results;
  }

  public StringBuilder getHTML() {
    StringBuilder resultingHtml = new StringBuilder();
    resultingHtml.append(BEGIN_HTML_STRING);
    double totals = 0;
    try {
      while (results.next()) {
        // process each row of query results
        resultingHtml.append(ROW_START_TAG); // add row start tag
        resultingHtml.append(CELL_START_TAG).append(results.getString("emp_name")).append(CELL_END_TAG); // appending employee name
        resultingHtml.append(CELL_START_TAG).append(results.getDouble("salary")).append(CELL_END_TAG); // appending employee salary for period
        resultingHtml.append(ROW_END_TAG); // add row end tag
        totals += results.getDouble("salary"); // add salary to totals
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
    resultingHtml.append(END_HTML_STRING);
    return resultingHtml;
  }
}
