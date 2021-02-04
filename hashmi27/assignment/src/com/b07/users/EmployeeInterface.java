package com.b07.users;

import java.sql.SQLException;
import java.util.List;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.AddressTooLongException;
import com.b07.exceptions.CustomerInvalidException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidQuantityException;
import com.b07.exceptions.RoleIdInvalidException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;

public class EmployeeInterface {

  private Employee employee; 
  private Inventory inventory;

  /**
   * Initializes employee, and inventory
   * @param employee
   * @param inventory
   */
  public EmployeeInterface(Employee employee, Inventory inventory) {
    this.employee = employee;
    this.setInventory(inventory);
  }
  
  /**
   * set the current employee
   * @param employee
   */

  public void setCurrentEmployee(Employee employee) {
    this.employee = employee;
  }
  
  /**
   * 
   * @return true if there is a current employee, false o/w
   */

  public boolean hasCurrentEmployee() {
    boolean check = false;
    if (this.employee != null) {
      check = true;
    } else {
      return check;
    }
    return check;
  }

  /**
   * 
   * @param item
   * @param quantity
   * @return true if the operation was successful
   * @throws SQLException
   * @throws InvalidQuantityException
   */
  public boolean restockInventory(Item item, int quantity)
      throws SQLException, InvalidQuantityException {
    boolean check = DatabaseUpdateHelper.updateInventoryQuantity(quantity, item.getId());
    return check;
  }

  /**
   * 
   * @param name
   * @param age
   * @param address
   * @param password
   * @return returns the user Id if the operation was successful
   * @throws AddressTooLongException
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws UserIdInvalidException
   * @throws CustomerInvalidException
   * @throws RoleIdInvalidException
   */
  public int createCustomer(String name, int age, String address, String password)
      throws AddressTooLongException, DatabaseInsertException, SQLException, UserIdInvalidException,
      CustomerInvalidException, RoleIdInvalidException {
    DatabaseInsertHelper.insertNewUser(name, age, address, password);
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    int result = 0;
    boolean check = false;
    for (User user : users) {
      if (user.getName() == name && user.getAddress() == address && user.getAge() == age
          && user.authenticate(password)) {
        result = user.getId();
        check = true;
      }
    }
    DatabaseInsertHelper.insertUserRole(result, 0);
    if (!check) {
      throw new CustomerInvalidException();
    }
    return result;
  }

  /**
   * 
   * @param name
   * @param age
   * @param address
   * @param password
   * @return return the userId if succesful
   * @throws AddressTooLongException
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws UserIdInvalidException
   * @throws CustomerInvalidException
   * @throws RoleIdInvalidException
   */
  public int createEmployee(String name, int age, String address, String password)
      throws AddressTooLongException, DatabaseInsertException, SQLException, UserIdInvalidException, CustomerInvalidException, RoleIdInvalidException {
    DatabaseInsertHelper.insertNewUser(name, age, address, password);    
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    int result = 0;
    boolean check = false;
    for (User user : users) {
      if (user.getName() == name && user.getAddress() == address && user.getAge() == age
          && user.authenticate(password)) {
        result = user.getId();
        check = true;
      }
    }
    DatabaseInsertHelper.insertUserRole(result, 1);
    if (!check) {
      throw new CustomerInvalidException();
    }
    return result;
  }

  /**
   * get the inventory
   * @return
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * set the inventory
   * @param inventory
   */
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

}
