package com.b07.users;

import java.sql.SQLException;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.security.PasswordHelpers;

public abstract class User {

  private int id;
  private String name;
  private int age;
  private int roleId;
  private boolean authenticated;
  private String address;
  private Role role;

  /**
   * get the id
   * 
   * @return
   */
  public int getId() {
    return this.id;
  }

  /**
   * set the id
   * 
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * set the role id
   * 
   * @param id
   */
  public void setRoleId(int id) {
    this.roleId = id;
  }

  /**
   * 
   * @return the name of the user
   */
  public String getName() {
    return this.name;
  }

  /**
   * set the name of the user
   * 
   * @param nom
   */
  public void setName(String nom) {
    this.name = nom;
  }

  /**
   * 
   * @return the age of hte user
   */
  public int getAge() {
    return this.age;
  }

  /**
   * set the age of the user
   * 
   * @param old
   */
  public void setAge(int old) {
    this.age = old;
  }

  /**
   * get the role id
   * 
   * @return the rolde id
   */
  public int getRoleId() {
    return this.roleId;
  }

  /**
   * authenticate if the passwoerd correct
   * 
   * @param password
   * @return true if correct, false if not
   * @throws SQLException
   * @throws UserIdInvalidException
   */

  public final boolean authenticate(String password) throws SQLException, UserIdInvalidException {
    String hashedP = DatabaseSelectHelper.getPassword(this.id);
    authenticated = PasswordHelpers.comparePassword(hashedP, password);
    return authenticated;
  }

  /**
   * set the role of the user
   * 
   * @param role
   */

  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * get the role
   * 
   * @return
   */

  public Role getRole() {
    return this.role;
  }

  /**
   * get the address
   * 
   * @return address of user
   */
  public String getAddress() {
    return address;
  }

  /**
   * set the address
   * 
   * @param address
   */
  public void setAddress(String address) {
    this.address = address;
  }
}
