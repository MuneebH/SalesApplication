package com.b07.database.helper;

import com.b07.database.DatabaseSelector;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidAgeException;
import com.b07.exceptions.InvalidNameException;
import com.b07.exceptions.InvalidPriceException;
import com.b07.exceptions.NameTooLongException;
import com.b07.exceptions.NegativeInventoryQuantityException;
import com.b07.exceptions.TooLongAddressException;
import com.b07.inventory.Inventory;
import com.b07.inventory.InventoryImpl;
import com.b07.inventory.Item;
import com.b07.inventory.ItemImpl;
import com.b07.store.Sale;
import com.b07.store.SaleImpl;
import com.b07.store.SalesLog;
import com.b07.store.SalesLogSimpleImpl;
import com.b07.store.ShoppingCart;
import com.b07.users.Account;
import com.b07.users.Admin;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * TODO: Complete the below methods to be able to get information out of the database. TODO: The
 * given code is there to aide you in building your methods. You don't have TODO: to keep the exact
 * code that is given (for example, DELETE the System.out.println())
 */
public class DatabaseSelectHelper extends DatabaseSelector {
  public static List<Integer> getRoleIds() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRoles(connection);
    List<Integer> ids = new ArrayList<>();
    while (results.next()) {
      ids.add(results.getInt("ID"));
    }
    results.close();
    connection.close();
    return ids;
  }

  public static String getRoleName(int roleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String role = DatabaseSelector.getRole(roleId, connection);
    connection.close();
    return role;
  }

  public static int getUserRoleId(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int roleId = DatabaseSelector.getUserRole(userId, connection);
    connection.close();
    return roleId;
  }

  public static List<Integer> getUsersByRole(int roleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUsersByRole(roleId, connection);
    List<Integer> userIds = new ArrayList<>();
    while (results.next()) {
      userIds.add(results.getInt("USERID"));
    }
    results.close();
    connection.close();
    return userIds;

  }

  public static List<User> getUsersDetails()
      throws SQLException, DatabaseInsertException, TooLongAddressException, InvalidAgeException, NameTooLongException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUsersDetails(connection);
    List<User> users = new ArrayList<>();
    User user;
    user = null;
    while (results.next()) {
      user = DatabaseSelectHelper.getUserDetails(results.getInt("ID"));
      users.add(user);
    }
    results.close();
    connection.close();
    return users;
  }

  public static User getUserDetails(int userId)
      throws SQLException, DatabaseInsertException, TooLongAddressException, InvalidAgeException, NameTooLongException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
    User user;
    user = null;
    int roleId;
    String roleName;
    roleName = null;
    while (results.next()) {
      roleId = DatabaseSelectHelper.getUserRoleId(results.getInt("ID"));
      roleName = DatabaseSelectHelper.getRoleName(roleId);
      if (roleName.equalsIgnoreCase("admin")) {
        user = new Admin(results.getInt("ID"), results.getString("NAME"), results.getInt("AGE"),
            results.getString("ADDRESS"));
      } else if (roleName.equalsIgnoreCase("customer")) {
        user = new Customer(results.getInt("ID"), results.getString("NAME"), results.getInt("AGE"),
            results.getString("ADDRESS"));
      } else {
        user = new Employee(results.getInt("ID"), results.getString("NAME"), results.getInt("AGE"),
            results.getString("ADDRESS"));
      }
    }
    results.close();
    connection.close();
    return user;
  }

  public static String getPassword(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String password = DatabaseSelector.getPassword(userId, connection);
    connection.close();
    return password;
  }

  public static List<Item> getAllItems()
      throws SQLException, NameTooLongException, InvalidPriceException, InvalidNameException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAllItems(connection);
    List<Item> items = new ArrayList<>();
    Item item;
    item = null;
    while (results.next()) {
      item = DatabaseSelectHelper.getItem(results.getInt("ID"));
      items.add(item);

    }
    results.close();
    connection.close();
    return items;
  }

  public static Item getItem(int itemId)
      throws SQLException, InvalidPriceException, NameTooLongException, InvalidNameException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItem(itemId, connection);
    Item item = new ItemImpl();
    while (results.next()) {
      item.setId(results.getInt("ID"));
      item.setName(results.getString("NAME"));
      item.setPrice(new BigDecimal(results.getString("PRICE")));
    }
    results.close();
    connection.close();
    return item;
  }

  public static Inventory getInventory()
      throws SQLException, DatabaseInsertException, NegativeInventoryQuantityException,
      InvalidPriceException, NameTooLongException, InvalidNameException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getInventory(connection);
    Inventory inventory = new InventoryImpl();
    HashMap<Item, Integer> itemMap = new HashMap<>();

    while (results.next()) {
      itemMap.put(getItem(results.getInt("ITEMID")), results.getInt("QUANTITY"));

    }
    inventory.setItemMap(itemMap);

    results.close();
    connection.close();
    return inventory;
  }

  public static int getInventoryQuantity(int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int quantity = DatabaseSelector.getInventoryQuantity(itemId, connection);
    connection.close();
    return quantity;
  }

  public static SalesLog getSales() throws SQLException, DatabaseInsertException,
  TooLongAddressException, InvalidAgeException, NameTooLongException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSales(connection);
    ArrayList<Sale> sales;
    sales = new ArrayList<>();
    while (results.next()) {
      sales.add(DatabaseSelectHelper.getSaleById(results.getInt("ID")));
    }
    SalesLog salesLog = new SalesLogSimpleImpl(sales);
    results.close();
    connection.close();
    return salesLog;
  }

  public static Sale getSaleById(int saleId) throws SQLException, DatabaseInsertException,
  TooLongAddressException, InvalidAgeException, NameTooLongException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSaleById(saleId, connection);
    Sale sale;
    sale = null;
    while (results.next()) {
      sale = new SaleImpl(results.getInt("ID"), DatabaseSelectHelper.getUserDetails
          (results.getInt("USERID")), new BigDecimal(results.getString
              ("TOTALPRICE")));
    }
    results.close();
    connection.close();
    return sale;
  }

  public List<Sale> getSalesToUser(int userId) throws SQLException, DatabaseInsertException,
  TooLongAddressException, InvalidAgeException, NameTooLongException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelectHelper.getSalesToUser(userId, connection);
    List<Sale> sales = new ArrayList<>();
    while (results.next()) {
      sales.add(DatabaseSelectHelper.getSaleById(results.getInt("ID")));
    }
    results.close();
    connection.close();
    return sales;
  }

  public static HashMap<Item, Integer> getItemizedSaleById(int saleId) throws SQLException, InvalidPriceException,
  NameTooLongException, InvalidNameException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSaleById(saleId, connection);
    HashMap<Item, Integer> itemMap; 
    itemMap = new HashMap<>();
    while (results.next()) {
      itemMap.put(DatabaseSelectHelper.getItem(results.getInt("ITEMID")),
          (results.getInt("QUANTITY")));
    }
    results.close();
    connection.close();
    return itemMap;
  }

  public static void getItemizedSales(SalesLog salesLog) throws SQLException, InvalidPriceException,
  NameTooLongException, InvalidNameException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSales(connection);
    ArrayList<Sale> sales = (ArrayList<Sale>) salesLog.getSaleLog();
    while (results.next()) {
      DatabaseSelectHelper.getItemizedSaleById(results.getInt("SALEID"));
    }
    salesLog.setSaleLog(sales);
    results.close();
    connection.close();
  }
  
  public static ShoppingCart getAccountDetails(int accountId) throws SQLException, DatabaseInsertException, 
  TooLongAddressException, InvalidAgeException, NameTooLongException, InvalidPriceException, InvalidNameException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAccountDetails(accountId, connection);
    ShoppingCart shoppingCart = new ShoppingCart();
    while (results.next()) {
      shoppingCart.addItem(DatabaseSelectHelper.getItem(results.getInt("ITEMID")), results.getInt("QUANTITY"));
    }
    return shoppingCart;
  }
  
  public static List<Account> getUserAccounts (int userId) throws SQLException, DatabaseInsertException, 
  TooLongAddressException, InvalidAgeException, NameTooLongException, InvalidPriceException, InvalidNameException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserAccounts(userId, connection);
    ArrayList<Account> accounts = new ArrayList<>();
    while (results.next()) {
      accounts.add(new Account(results.getInt("ID")));
    }
    return accounts;
  }
  
  public static List<Account> getUserActiveAccounts(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserActiveAccounts(userId, connection);
    ArrayList<Account> accounts = new ArrayList<>();
    while (results.next()) {
      accounts.add(new Account(results.getInt("ID")));
    }
    return accounts;
  }
  
  public static List<Account> getUserInactiveAccounts(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserInactiveAccounts(userId, connection);
    ArrayList<Account> accounts = new ArrayList<>();
    while (results.next()) {
      accounts.add(new Account(results.getInt("ID")));
    }
    return accounts;
  }
}


