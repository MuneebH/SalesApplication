package com.b07.users;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidAgeException;
import com.b07.exceptions.InvalidNameException;
import com.b07.exceptions.InvalidPriceException;
import com.b07.exceptions.NameTooLongException;
import com.b07.exceptions.TooLongAddressException;
import com.b07.inventory.Item;
import com.b07.store.SalesLog;

/**
 * @author shaahid
 *
 */
public class Admin extends Employee {


  private int id;
  private int age;
  private String name;
  private int roleId;
  private boolean authenticated;
  private String address;

  /**
   * Initiliazes name, id, age, address of Admin
   * 
   * @param the id
   * @param the name
   * @param the age
   * @param the address
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws InvalidAgeException
   * @throws TooLongAddressException
   * @throws NameTooLongException
   */
  public Admin(int id, String name, int age, String address) throws DatabaseInsertException,
      SQLException, InvalidAgeException, TooLongAddressException, NameTooLongException {
    // initiliazes all the values
    if (age < 0 || age > 120) { // the age can't be more than 120 or a negative number
      throw new InvalidAgeException();
    }
    if (address.length() > 100) { // the address can't be of length greater than 100 characters
      throw new TooLongAddressException();
    }
    if (name.length() > 64) { // name can't be greater than 64 characters
      throw new NameTooLongException();
    }
    // call employee's constructor to initialize the id, name, age, address
    super.setId(id);
    super.setName(name);
    super.setAge(age);
    super.setAddress(address);

  }



  /**
   * Returns true iff the Employee got promoted
   * 
   * @param employee
   * @return true iff the Employee but
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws TooLongAddressException
   * @throws InvalidAgeException
   * @throws NameTooLongException
   */
  public boolean promoteEmployee(Employee employee) throws DatabaseInsertException, SQLException,
      InvalidAgeException, TooLongAddressException, NameTooLongException {
    // Gets Changes employee's object to Admins
    employee =
        new Admin(employee.getId(), employee.getName(), employee.getAge(), employee.getAddress());
    return true;
  }

  /**
   * This is a option that would allow the admin to view the historic sales records
   * 
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws TooLongAddressException
   * @throws InvalidAgeException
   * @throws InvalidPriceException
   * @throws NameTooLongException
   * @throws InvalidNameException
   */
  public void ViewBooks() throws SQLException, DatabaseInsertException, TooLongAddressException,
      InvalidAgeException, InvalidPriceException, NameTooLongException, InvalidNameException {
    SalesLog sales = DatabaseSelectHelper.getSales(); // get the sales
    DatabaseSelectHelper.getItemizedSales(sales); // get the itemized sales
    HashMap<Item, Integer> itemsSold;
    itemsSold = new HashMap<>();
    for (int counter = 0; sales.getSaleLog().size() > counter; counter++) {
      System.out.println("Customer: " + sales.getSaleLog().get(counter).getUser().getName());
      System.out
          .println(("Purchase Number: " + String.valueOf(sales.getSaleLog().get(counter).getId())));
      ArrayList<Item> items =
          new ArrayList<Item>(sales.getSaleLog().get(counter).getItemMap().keySet());
      System.out.println("Itemized Breakdown: " + items.get(0).getName() + ": "
          + String.valueOf(sales.getSaleLog().get(counter).getItemMap().get(items.get(0))));
      itemsSold.put(items.get(0), sales.getSaleLog().get(counter).getItemMap().get(items.get(0)));
      for (int counter1 = 1; items.size() > counter1; counter++) {
        System.out.println("Itemized Breakdown: " + items.get(counter1).getName() + ": " + String
            .valueOf(sales.getSaleLog().get(counter).getItemMap().get(items.get(counter1))));
        if (itemsSold.containsKey(items.get(counter1))) {
          itemsSold.put(items.get(counter1), (itemsSold.get(items.get(counter1))
              + sales.getSaleLog().get(counter).getItemMap().get(items.get(counter1))));
        } else {
          itemsSold.put(items.get(counter1),
              sales.getSaleLog().get(counter).getItemMap().get(items.get(counter1)));
        }
      }
      System.out.println("-------------------------------------------------------------");
    }
    ArrayList<Item> items = new ArrayList<Item>(itemsSold.keySet());
    for (int counter = 0; items.size() > counter; counter++) {
      System.out.println("Number of " + items.get(counter).getName() + " sold: "
          + String.valueOf(itemsSold.get(items.get(counter))));
    }
    BigDecimal totalPrice = new BigDecimal("0");
    for (int counter = 0; sales.getSaleLog().size() > counter; counter++) {
      totalPrice = totalPrice.add(sales.getSaleLog().get(counter).getTotalPrice());
    }
    System.out.println(("TOTAL SALES: " + String.valueOf(totalPrice)));
  }
  
  public List<Integer> getActiveAccounts() throws SQLException, DatabaseInsertException, TooLongAddressException, InvalidAgeException, NameTooLongException {
    List<User> allUsers = new ArrayList<>();
    List<Integer> allIds = new ArrayList<>();
    allUsers = DatabaseSelectHelper.getUsersDetails();
    int count = 0;
    while(count < allUsers.size()) {
      int countTwo = 0;
      List<Account> thisUserId = DatabaseSelectHelper.getUserActiveAccounts(allUsers.get(count).getId());
      while(countTwo < thisUserId.size()) {
        allIds.add(thisUserId.get(countTwo).getId());
        countTwo += 1;
      }
      count += 1;
    }
    return allIds;
  }
  
  public List<Integer> getInActiveAccounts() throws SQLException, DatabaseInsertException, TooLongAddressException, InvalidAgeException, NameTooLongException {
    List<User> allUsers = new ArrayList<>();
    List<Integer> allIds = new ArrayList<>();
    allUsers = DatabaseSelectHelper.getUsersDetails();
    int count = 0;
    while(count < allUsers.size()) {
      int countTwo = 0;
      List<Account> thisUserId = DatabaseSelectHelper.getUserInactiveAccounts(allUsers.get(count).getId());
      while(countTwo < thisUserId.size()) {
        allIds.add(thisUserId.get(countTwo).getId());
        countTwo += 1;
      }
      count += 1;
    }
    return allIds;
  }
  
  
  
}
