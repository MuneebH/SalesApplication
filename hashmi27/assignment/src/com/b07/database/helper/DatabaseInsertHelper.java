package com.b07.database.helper;

import com.b07.database.DatabaseInserter;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.database.helper.DatabaseDriverHelper;
import com.b07.exceptions.AddressTooLongException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidQuantityException;
import com.b07.exceptions.ItemIdInvalidException;
import com.b07.exceptions.NameInvalidException;
import com.b07.exceptions.PriceInvalidException;
import com.b07.exceptions.RoleIdInvalidException;
import com.b07.exceptions.RoleNameInvalidException;
import com.b07.exceptions.SaleIdInvalidException;
import com.b07.exceptions.TotalPriceInvalidException;
import com.b07.inventory.Item;
import com.b07.store.Sale;
import com.b07.store.SalesLog;
import com.b07.users.Role;
import com.b07.users.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class DatabaseInsertHelper extends DatabaseInserter {

  public static int insertRole(String name)
      throws RoleNameInvalidException, DatabaseInsertException, SQLException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    for (Role role : Role.values()) {
      if (role.name() != name) {
        throw new RoleNameInvalidException();
      }
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int roleId = DatabaseInserter.insertRole(name, connection);
    connection.close();
    return roleId;
  }

  public static int insertNewUser(String name, int age, String address, String password)
      throws AddressTooLongException, DatabaseInsertException, SQLException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    if (address.length() > 100) {
      throw new AddressTooLongException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int userId = DatabaseInserter.insertNewUser(name, age, address, password, connection);
      connection.close();
      return userId;
    }
  }

  public static int insertUserRole(int userId, int roleId)
      throws DatabaseInsertException, SQLException, RoleIdInvalidException, UserIdInvalidException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    List<Integer> roles = DatabaseSelectHelper.getRoleIds();
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!roles.contains(roleId)) {
      throw new RoleIdInvalidException();
    } else if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int userRoleId = DatabaseInserter.insertUserRole(userId, roleId, connection);
      connection.close();
      return userRoleId;
    }
  }

  public int insertItem(String name, BigDecimal price)
      throws SQLException, DatabaseInsertException, NameInvalidException, PriceInvalidException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    BigDecimal zero = new BigDecimal(0);
    if (name.length() >= 64 || name == null) {
      throw new NameInvalidException();
    } else if (Math.max(0, price.stripTrailingZeros().scale()) != 2
        || price.compareTo(zero) == -1) {
      throw new PriceInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int itemId;
      itemId = DatabaseInserter.insertItem(name, price, connection);
      connection.close();
      return itemId;
    }
  }

  public int insertInventory(int itemId, int quantity) throws DatabaseInsertException, SQLException,
      InvalidQuantityException, ItemIdInvalidException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    try {
      DatabaseInsertHelper.checkItemId(itemId);
    } catch (ItemIdInvalidException e) {
      e.printStackTrace();
    }
    if (quantity < 0) {
      throw new InvalidQuantityException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int inventoryId = DatabaseInserter.insertInventory(itemId, quantity, connection);
      connection.close();
      return inventoryId;
    }
  }

  public static boolean checkItemId(int itemId) throws ItemIdInvalidException, SQLException {
    List<Item> items = DatabaseSelectHelper.getAllItems();
    Item item = DatabaseSelectHelper.getItem(itemId);
    boolean check = true;
    if (!items.contains(item)) {
      check = false;
    }
    return check;
  }

  public int insertSale(int userId, BigDecimal totalPrice) throws DatabaseInsertException,
      SQLException, UserIdInvalidException, TotalPriceInvalidException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    List<User> users = DatabaseSelectHelper.getUsersDetails();
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (!users.contains(user)) {
      throw new UserIdInvalidException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int saleId = DatabaseInserter.insertSale(userId, totalPrice, connection);
      Sale sale = DatabaseSelectHelper.getSaleById(saleId);
      HashMap<Item, Integer> itemMap = sale.getItemMap();
      BigDecimal itemPrice = new BigDecimal(0);
      for (Item item : itemMap.keySet()) {
        BigDecimal quantity = new BigDecimal(itemMap.get(item));
        itemPrice = itemPrice.add(item.getPrice().multiply(quantity));
      }
      if (itemPrice != totalPrice) {
        throw new TotalPriceInvalidException();
      }
      connection.close();
      return saleId;
    }
  }

  public int insertItemizedSale(int saleId, int itemId, int quantity) throws ItemIdInvalidException,
      DatabaseInsertException, SQLException, SaleIdInvalidException, InvalidQuantityException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    try {
      DatabaseInsertHelper.checkItemId(itemId);
    } catch (ItemIdInvalidException e) {
      e.printStackTrace();
    }
    SalesLog sales = DatabaseSelectHelper.getSales();
    Set<Integer> ids = sales.getSaleIds();
    if (!(ids.contains(saleId))) {
      throw new SaleIdInvalidException();
    } else if (quantity < 0) {
      throw new InvalidQuantityException();
    } else {
      Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
      int itemizedId = DatabaseInserter.insertItemizedSale(saleId, itemId, quantity, connection);
      connection.close();
      return itemizedId;
    }
  }


}
