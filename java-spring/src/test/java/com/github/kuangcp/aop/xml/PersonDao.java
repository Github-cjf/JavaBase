package com.github.kuangcp.aop.xml;

import java.util.List;

/**
 * 目标接口
 *
 * @author Administrator
 */
public interface PersonDao {

  void savePerson();

  void updatePerson();

  void deletePerson();

  List<Person> getPerson();
}
