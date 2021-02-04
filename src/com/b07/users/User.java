package com.b07.users;

import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.security.PasswordHelpers;
import java.sql.SQLException;

public abstract class User {

  // initiliazes the variables
  private int id;
  private String name;
  private int age;
  private String address;
  private int roleId;
  private boolean authenticated;

  /**
   * Gets the id of the User
   * 
   * @return the id
   */
  public int getId() {
    return this.id;
  }


  /**
   * Sets the id of the user
   * 
   * @param the id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the name of the user
   * 
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the user
   * 
   * @param the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the age of the User
   * 
   * @return the age
   */
  public int getAge() {
    return this.age;
  }

  /**
   * Sets the age of the user
   * 
   * @param age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets the roleId of the User
   * 
   * @return
   */
  public int getRoleId() {
    return this.roleId;
  }



  /**
   * Returns True iff authenticated with database
   * 
   * @param the password
   * @return boolean if authenticated
   * @throws SQLException
   */
  public boolean authenticate(final String password) throws SQLException {
    String passwordCheck = DatabaseSelectHelper.getPassword(id); // get the password
    if (PasswordHelpers.comparePassword(passwordCheck, password)) { // use comparePassword to see if
                                                                    // it authenticated
      authenticated = true;
    } else {
      authenticated = false;
    }

    return authenticated;
  }

  /**
   * Gets the address
   * 
   * @return the address
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * sets the address
   * 
   * @param the address
   */
  public void setAddress(String address) {
    this.address = address;
  }


}
