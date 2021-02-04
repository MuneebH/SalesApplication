package com.b07.database.helper;

import com.b07.database.DatabaseUpdater;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.exceptions.AddressTooLongException;
import com.b07.exceptions.AgeInvalidException;
import com.b07.exceptions.InvalidQuantityException;
import com.b07.exceptions.ItemIdInvalidException;
import com.b07.exceptions.NameInvalidException;
import com.b07.exceptions.PriceInvalidException;
import com.b07.exceptions.RoleIdInvalidException;
import com.b07.exceptions.RoleNameInvalidException;
import com.b07.users.Role;
import com.b07.users.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUpdateHelper extends DatabaseUpdater {
  
  public static boolean updateRoleName(String name, int id) throws RoleNameInvalidException, SQLException {
    //TODO Implement this method as stated on the assignment sheet (Strawberry)
    //hint: You should be using these three lines in your final code
    for (Role role : Role.values()) {
      if (role.name() != name) {
        throw new RoleNameInvalidException();
      }
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateRoleName(name, id, connection);
    connection.close();
    return complete;
  } 
  
  public static boolean updateUserName(String name, int userId) throws UserIdInvalidException, SQLException {
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      boolean complete = DatabaseUpdater.updateUserName(name, userId, connection);
      connection.close();
      return complete;
    }
  }
  
  public static boolean updateUserAge(int age, int userId) throws UserIdInvalidException, SQLException, AgeInvalidException {
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else if (age < 0) {
      throw new AgeInvalidException(); 
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      boolean complete = DatabaseUpdater.updateUserAge(age, userId, connection);
      connection.close();
      return complete;
    }
  }
  
  public static boolean updateUserAddress(String address, int userId) throws UserIdInvalidException, AddressTooLongException, SQLException {
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else if (address.length() > 100) {
      throw new AddressTooLongException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      boolean complete = DatabaseUpdater.updateUserAddress(address, userId, connection);
      connection.close();
      return complete;
    }
    
  }
  
  public static boolean updateUserRole(int roleId, int userId) throws SQLException, UserIdInvalidException, RoleIdInvalidException {
    List<Integer> roles = DatabaseSelectHelper.getRoleIds();
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else if (!roles.contains(roleId)) {
      throw new RoleIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      boolean complete = DatabaseUpdater.updateUserRole(roleId, userId, connection);
      connection.close();
      return complete;
    }

  }
  
  public static boolean updateItemName(String name, int itemId) throws SQLException, NameInvalidException {
    try {
      DatabaseInsertHelper.checkItemId(itemId);
    } catch (ItemIdInvalidException e) {
      e.printStackTrace();
    }  
    try {
      DatabaseUpdateHelper.checkName(name);
    } catch (NameInvalidException e) {
      e.printStackTrace();
    }
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      boolean complete = DatabaseUpdater.updateItemName(name, itemId, connection);
      connection.close();
      return complete;
  }   
  
  
  public static void checkName(String name) throws NameInvalidException {
    if (name.length() > 64 || name == null) {
      throw new NameInvalidException();
    }
  }
  
  public static boolean updateItemPrice(BigDecimal price, int itemId) throws PriceInvalidException, SQLException {
    BigDecimal zero = new BigDecimal(0); 
    if(Math.max(0, price.stripTrailingZeros().scale()) != 2 || price.compareTo(zero) == -1) {
      throw new PriceInvalidException();
    }
    try {
      DatabaseInsertHelper.checkItemId(itemId);
    } catch (ItemIdInvalidException e) {
      e.printStackTrace();
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateItemPrice(price, itemId, connection);
    connection.close();
    return complete;
  }
  
  public static boolean updateInventoryQuantity(int quantity, int itemId) throws SQLException, InvalidQuantityException {
    try {
      DatabaseInsertHelper.checkItemId(itemId);
    } catch (ItemIdInvalidException e) {
      e.printStackTrace();
    }
    if (quantity < 0) {
      throw new InvalidQuantityException();
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateInventoryQuantity(quantity, itemId, connection);
    connection.close();
    return complete;
  }
}
