package com.b07.users;

import java.sql.SQLException;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.exceptions.AddressTooLongException;
import com.b07.exceptions.DatabaseInsertException;

public class Customer extends User {

  /**
   * constructor
   * 
   * @param id
   * @param name
   * @param age
   * @param address
   * @throws AddressTooLongException
   * @throws DatabaseInsertException
   * @throws SQLException
   */
  public Customer(int id, String name, int age, String address)
      throws AddressTooLongException, DatabaseInsertException, SQLException {
    this.setId(id);
    this.setAge(age);
    this.setAddress(address);
    this.setName(name);
    this.setRole(Role.CUSTOMER);
  }

  /**
   * customer constructor with the parameters below
   * 
   * @param id
   * @param name
   * @param age
   * @param address
   * @param authenticated
   * @throws AddressTooLongException
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws UserIdInvalidException
   */
  public Customer(int id, String name, int age, String address, boolean authenticated)
      throws AddressTooLongException, DatabaseInsertException, SQLException,
      UserIdInvalidException {
    this.setId(id);
    this.setAge(age);
    this.setAddress(address);
    this.setName(name);
    this.setRole(Role.CUSTOMER);
  }

  /*
   * constructor
   */
  public Customer() {
    this.setRole(Role.CUSTOMER);
  }
}
