package org.nta.lessons.lesson15.solid;


public interface CreaterDataForSending {
  void addListener(DataListener dataListener);
  void deleteListener(DataListener dataListener);
  String createStringForSending();
  void notifyListeners();
}
