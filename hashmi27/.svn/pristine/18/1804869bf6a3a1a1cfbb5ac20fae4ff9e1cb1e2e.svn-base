package com.b07.database.helper;

import com.b07.database.DatabaseSelector;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.exceptions.ItemIdInvalidException;
import com.b07.exceptions.RoleIdInvalidException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.store.Sale;
import com.b07.store.SalesLog;
import com.b07.users.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * TODO: Complete the below methods to be able to get information out of the database.
 * TODO: The given code is there to aide you in building your methods.  You don't have
 * TODO: to keep the exact code that is given (for example, DELETE the System.out.println())
 */
public class DatabaseSelectHelper extends DatabaseSelector {
  public static List<Integer> getRoleIds() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRoles(connection);
    List<Integer> ids = new ArrayList<>();
    while (results.next()) {
      System.out.println(results.getInt("ID"));
    }
    results.close();
    connection.close();
    return ids;    
  }
  
  public static String getRoleName(int roleId) throws SQLException, RoleIdInvalidException {
    List<Integer> roles = DatabaseSelectHelper.getRoleIds();
    if (!roles.contains(roleId)) {
      throw new RoleIdInvalidException();
    } 
    else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      String role = DatabaseSelector.getRole(roleId, connection);
      connection.close();
      return role;
    }
  }
  
  public static int getUserRoleId(int userId) throws SQLException, UserIdInvalidException {
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int roleId = DatabaseSelector.getUserRole(userId, connection);
      connection.close();
      return roleId;
    }
  }
  
  public static List<Integer> getUsersByRole(int roleId) throws RoleIdInvalidException, SQLException {
    List<Integer> roles = DatabaseSelectHelper.getRoleIds();
    if (!roles.contains(roleId)) {
      throw new RoleIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUsersByRole(roleId, connection);
      List<Integer> userIds = new ArrayList<>();
      while (results.next()) {
        System.out.println(results.getInt("USERID"));
      }
      results.close();
      connection.close();
      return userIds;
    }
  }
  
  public static List<User> getUsersDetails() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUsersDetails(connection);
    List<User> users = new ArrayList<>();
    while (results.next()) {
      System.out.println(results.getInt("ID"));
      System.out.println(results.getString("NAME"));
      System.out.println(results.getInt("AGE"));
      System.out.println(results.getString("ADDRESS"));
    }
    results.close();
    connection.close();
    return users;
  }
  
  public static User getUserDetails(int userId) throws SQLException, UserIdInvalidException {
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
      while (results.next()) {
        System.out.println(results.getInt("ID"));
        System.out.println(results.getString("NAME"));
        System.out.println(results.getInt("AGE"));
        System.out.println(results.getString("ADDRESS"));
      }
      results.close();
      connection.close();
      return null;
    }
  }
  
  public static String getPassword(int userId) throws SQLException, UserIdInvalidException {
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      String password = DatabaseSelector.getPassword(userId, connection);
      connection.close();
      return password;
    }
  }
  
  public static List<Item> getAllItems() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAllItems(connection);
    List<Item> items = new ArrayList<>();
    while(results.next()) {
      System.out.println(results.getInt("ID"));
      System.out.println(results.getString("NAME"));
      System.out.println(new BigDecimal(results.getString("PRICE")));
    }
    results.close();
    connection.close();
    return items;
  }
  
  public static Item getItem(int itemId) throws SQLException {
    try {
      DatabaseInsertHelper.checkItemId(itemId);
    } catch (ItemIdInvalidException e) {
      e.printStackTrace();
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItem(itemId, connection);
    while(results.next()) {
      System.out.println(results.getInt("ID"));
      System.out.println(results.getString("NAME"));
      System.out.println(new BigDecimal(results.getString("PRICE")));
    }
    results.close();
    connection.close();
    return null;
  }
  
  public static Inventory getInventory() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getInventory(connection);
   
    while(results.next()) {
      System.out.println(results.getInt("ITEMID"));
      System.out.println(results.getString("QUANTITY"));
    }
    results.close();
    connection.close();
    return null;
  }
  
  public static int getInventoryQuantity(int itemId) throws SQLException {
    try {
      DatabaseInsertHelper.checkItemId(itemId);
    } catch (ItemIdInvalidException e) {
      e.printStackTrace();
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int quantity = DatabaseSelector.getInventoryQuantity(itemId, connection);
    connection.close();
    return quantity;
  }
  
  public static SalesLog getSales() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSales(connection);
    
    while(results.next()) {
      System.out.println(results.getInt("ID"));
      System.out.println(results.getInt("USERID"));
      System.out.println(new BigDecimal(results.getString("TOTALPRICE")));
    }
    results.close();
    connection.close();
    return null;
  }
  
  public static Sale getSaleById(int saleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSaleById(saleId, connection);
    while(results.next()) {
      System.out.println(results.getInt("ID"));
      System.out.println(results.getInt("USERID"));
      System.out.println(new BigDecimal(results.getString("TOTALPRICE")));
    }
    results.close();
    connection.close();
    return null;
  }
  
  public List<Sale> getSalesToUser(int userId) throws SQLException, UserIdInvalidException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else {
      ResultSet results = DatabaseSelectHelper.getSalesToUser(userId, connection);
      List<Sale> sales = new ArrayList<>();
      while (results.next()) {
        System.out.println(results.getInt("ID"));
        System.out.println(results.getInt("USERID"));
        System.out.println(new BigDecimal(results.getString("TOTALPRICE")));
      }
      results.close();
      connection.close();
      return sales;
    }
  }
  public static void getItemizedSaleById(int saleId, Sale sale) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSaleById(saleId, connection);
    boolean flag = false;
    while(!flag) {
      int checkId = results.getInt("SALEID");
      if (saleId == checkId) {
        flag = true;
      }
    }
    while (results.next()) {
      System.out.println(results.getInt("SALEID"));
      System.out.println(results.getInt("ITEMID"));
      System.out.println(results.getInt("QUANTITY"));
    }
    results.close();
    connection.close();
  }
  
  public static void getItemizedSales(SalesLog salesLog) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSales(connection);
    while (results.next()) {
      System.out.println(results.getInt("SALEID"));
      System.out.println(results.getInt("ITEMID"));
      System.out.println(results.getInt("QUANTITY"));
    }
    results.close();
    connection.close();
  }
}
