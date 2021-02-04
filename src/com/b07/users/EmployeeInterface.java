package com.b07.users;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidAgeException;
import com.b07.exceptions.NameTooLongException;
import com.b07.exceptions.NegativeInventoryQuantityException;
import com.b07.exceptions.TooLongAddressException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import java.sql.SQLException;


public class EmployeeInterface {


  // Initiliazes the variables
  private Employee currentEmployee = null;
  private Inventory inventory;
  private Customer customer;
  private Employee employee;

  /**
   * Initialize the employee and the inventory
   * 
   * @param The employee
   * @param The inventory
   */
  public EmployeeInterface(Employee employee, Inventory inventory) {
    this.currentEmployee = employee;
    this.inventory = inventory;
  }

  /**
   * Initialize the inventory only
   * 
   * @param The inventory
   */
  public EmployeeInterface(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Sets the employee
   * 
   * @param The employee
   */
  public void setCurrentEmployee(Employee employee) {
    this.currentEmployee = employee;
  }

  /**
   * Sees if there is a current employee
   * 
   * @return True if employee isnt null
   */
  public boolean hasCurrentEmployee() {
    return this.currentEmployee != null;

  }

  /**
   * Restocks the inventory
   * 
   * @param The item
   * @param The quantity
   * @return True if restock was sucessful
   * @throws NegativeInventoryQuantityException
   * @throws SQLException
   */
  public boolean restockInventory(Item item, Integer quantity)
      throws SQLException, NegativeInventoryQuantityException {
    inventory.updateMap(item, quantity);
    return true;
  }

  /**
   * Creates a new costumer and returns its id
   * 
   * @param the name
   * @param the age
   * @param the address
   * @param the password
   * @return the id of the costumer
   * @throws SQLException
   * @throws NameTooLongException
   * @throws InvalidAgeException
   * @throws TooLongAddressException
   * @throws DatabaseInsertException
   */
  public int createCustumer(String name, int age, String address, String password)
      throws SQLException, DatabaseInsertException, TooLongAddressException, InvalidAgeException,
      NameTooLongException {
    if (customer.authenticate(password)) { // if the customer is authenticated, then make the
                                           // customer
      customer = new Customer(customer.getId(), name, age, address);
    }
    return customer.getId(); // return the id of the customer

  }

  /**
   * Creates a new employee and returns its id
   * 
   * @param The name
   * @param The age
   * @param The address
   * @param The password
   * @return The id of the new Employee
   * @throws SQLException
   * @throws NameTooLongException
   * @throws InvalidAgeException
   * @throws TooLongAddressException
   * @throws DatabaseInsertException
   */
  public int createEmployee(String name, int age, String address, String password)
      throws SQLException, DatabaseInsertException, TooLongAddressException, InvalidAgeException,
      NameTooLongException {
    if (employee.authenticate(password)) { // if the employee is authenticated, make the employee
      employee = new Employee(employee.getId(), name, age, address);
    }
    return employee.getId(); // return the id of the employee

  }

}
