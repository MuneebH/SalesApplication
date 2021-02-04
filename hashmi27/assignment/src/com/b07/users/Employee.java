/**
 * 
 */
package com.b07.users;

import java.sql.SQLException;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.exceptions.AddressTooLongException;
import com.b07.exceptions.DatabaseInsertException;

/**
 * @author Muneeb
 *
 */
public class Employee extends User {

  /**
   * Employee constructor
   * @param id
   * @param name
   * @param age
   * @param address
   * @throws AddressTooLongException
   * @throws DatabaseInsertException
   * @throws SQLException
   */
  public Employee(int id, String name, int age, String address)
      throws AddressTooLongException, DatabaseInsertException, SQLException {
    // set the id, address, age, name
    this.setId(id); 
    this.setAddress(address);
    this.setName(name);
    this.setAge(age);
    this.setRole(Role.EMPLOYEE); // set the role to EMPLOYEE 
  }

  public Employee(int id, String name, int age, String address, boolean authenticated)
      throws AddressTooLongException, DatabaseInsertException, SQLException,
      UserIdInvalidException {
    this.setId(id);
    this.setAddress(address);
    this.setName(name);
    this.setAge(age);
    this.setRole(Role.EMPLOYEE);
  }

  public Employee() {
    this.setRole(Role.EMPLOYEE);
  }
}
