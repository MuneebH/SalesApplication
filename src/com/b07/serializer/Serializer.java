package com.b07.serializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidAgeException;
import com.b07.exceptions.InvalidNameException;
import com.b07.exceptions.InvalidPriceException;
import com.b07.exceptions.NameTooLongException;
import com.b07.exceptions.NegativeInventoryQuantityException;
import com.b07.exceptions.TooLongAddressException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.store.SalesLog;
import com.b07.users.Account;
import com.b07.users.User;

public class Serializer {
  public static void serialize() throws SQLException, DatabaseInsertException,
      TooLongAddressException, InvalidAgeException, NameTooLongException, InvalidPriceException,
      InvalidNameException, NegativeInventoryQuantityException {
    // this is all the stuff I saw from Joe's file
    FileOutputStream fileOut = null;
    ObjectOutputStream out = null;
    try {
      fileOut = new FileOutputStream("./database_copy.ser");
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }
    try {
      out = new ObjectOutputStream(fileOut);
      // gets all the objects in ArrayLists
      ArrayList<String> roles = Serializer.getRoles();
      ArrayList<User> users = (ArrayList<User>) DatabaseSelectHelper.getUsersDetails();
      HashMap<User, String> userPasswords = Serializer.getUserAndPass();
      ArrayList<Item> items = (ArrayList<Item>) DatabaseSelectHelper.getAllItems();
      Inventory inventory = DatabaseSelectHelper.getInventory();
      SalesLog sales = DatabaseSelectHelper.getSales();
      DatabaseSelectHelper.getItemizedSales(sales);
      ArrayList<Account> activeAccounts = Serializer.getAccounts(true);
      ArrayList<Account> inactiveAccounts = Serializer.getAccounts(false);
      //writing objects
      out.writeObject(roles);
      out.writeObject(users);
      out.writeObject(userPasswords);
      out.writeObject(items);
      out.writeObject(inventory);
      out.writeObject(sales);
      out.writeObject(activeAccounts);
      out.writeObject(inactiveAccounts);
      out.close();
      fileOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  public static ArrayList<String> getRoles() {
    // get all roles
    ArrayList<Integer> x = new ArrayList<>();
    try {
      x = (ArrayList<Integer>) DatabaseSelectHelper.getRoleIds();
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    ArrayList<String> y = new ArrayList<>();
    for (int counter = 1; x.size() >= counter; counter++) {
      try {
        y.add(DatabaseSelectHelper.getRoleName(counter));
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return y;
  }

  public static HashMap<User, String> getUserAndPass() {
    // I need to do this because password isnt stored in the object
    ArrayList<User> users = new ArrayList<>();
    try {
      users = (ArrayList<User>) DatabaseSelectHelper.getUsersDetails();
    } catch (SQLException | DatabaseInsertException | TooLongAddressException | InvalidAgeException
        | NameTooLongException e) {
      // will never reach here
      e.printStackTrace();
    }
    HashMap<User, String> userPass = new HashMap<>();
    User user = null;
    for (int counter = 1; users.size() >= counter; counter++) {
      user = users.get(counter);
      try {
        userPass.put(user, DatabaseSelectHelper.getPassword(user.getId()));
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return userPass;
  }

  public static ArrayList<Account> getAccounts(boolean active) throws SQLException {
    ArrayList<Account> result = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    int userId = -1;
    for (int counter = 1; users.size() >= counter; counter++) {
      userId = users.get(counter).getId();
      if (active) {
        result.addAll(DatabaseSelectHelper.getUserActiveAccounts(userId));
      } else {
        result.addAll(DatabaseSelectHelper.getUserInactiveAccounts(userId));
      }
    }
    return result;
  }
}
