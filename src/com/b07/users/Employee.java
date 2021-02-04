package com.b07.users;

import java.sql.SQLException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidAgeException;
import com.b07.exceptions.NameTooLongException;
import com.b07.exceptions.TooLongAddressException;

public class Employee extends User {

  private int roleId;
  private String address;
  private int id;
  private int age;
  private String name;

  // Overrides Employee to let Empty implementations
  public Employee() {

  }

  /**
   * Initialize the id, name, age, address of the Employee
   * 
   * @param The id
   * @param The name
   * @param The age
   * @param The address
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws TooLongAddressException
   * @throws InvalidAgeException
   * @throws NameTooLongException
   */
  public Employee(int id, String name, int age, String address) throws DatabaseInsertException,
      SQLException, TooLongAddressException, InvalidAgeException, NameTooLongException {
    // Initiliazes all the values
    if (age < 0) { // if the age is negative, its
      throw new InvalidAgeException();
    }
    if (address.length() > 100) { // address can't be greater than 100 characters
      throw new TooLongAddressException();
    }
    if (name.length() > 64) { // the name can't be greater than 64 characters
      throw new NameTooLongException();
    }
    // call user constructor to initialize id, name, age, address
    super.setId(id);
    super.setName(name);
    super.setAge(age);
    super.setAddress(address);

  }



}
