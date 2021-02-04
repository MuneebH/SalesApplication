package com.b07.users;

import java.sql.SQLException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidAgeException;
import com.b07.exceptions.NameTooLongException;
import com.b07.exceptions.TooLongAddressException;

public class Customer extends User {


  // Initiliazes the variables
  private int id;
  private int age;
  private String name;
  private int roleId;
  private String address;


  /**
   * Initializes the id, name, age, address of the custumer
   * 
   * @param id
   * @param name
   * @param age
   * @param address
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws TooLongAddressException
   * @throws InvalidAgeException
   * @throws NameTooLongException
   */
  public Customer(int id, String name, int age, String address) throws DatabaseInsertException,
      SQLException, TooLongAddressException, InvalidAgeException, NameTooLongException {

    if (age < 0) { // if the age is negative, throw an exception
      throw new InvalidAgeException();
    }
    if (address.length() > 100) { // address of the person can't be greater than 100 char
      throw new TooLongAddressException();
    }
    if (name.length() > 64) { // name of the person can't be greater than 64 characters
      throw new NameTooLongException();
    }
    // call user's constructor to initialize id, name, age, address
    super.setId(id);
    super.setName(name);
    super.setAge(age);
    super.setAddress(address);

  }



}
