package com.b07.serializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import com.b07.users.Account;
import com.b07.database.helper.DatabaseDriverHelper;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.store.DatabaseDriverExtender;
import com.b07.users.User;

public class Deserialzer {

  public static void deserialize()
      throws DatabaseInsertException, SQLException, ClassNotFoundException, IOException {
    FileInputStream fis = null;
    try {
      fis = new FileInputStream("database_copy.ser");
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
    ObjectInputStream ois = null;
    ois = new ObjectInputStream(fis);
    deserializeRoles(ois);
    deserializeUsers(ois); // includes passwords
    deserializeAccounts(ois);
    deserializeUserRoles(ois);
    ois.close();
    fis.close();
  }

  private static void deserializeUsers(ObjectInputStream ois)
      throws DatabaseInsertException, SQLException, ClassNotFoundException, IOException {
    List<User> users = (List<User>) ois.readObject();
    List<String> pws = (List<String>) ois.readObject();
    int i = 0;
    while (i < users.size()) {
      User user = users.get(i);
      String pw = pws.get(i);
      DatabaseInsertHelper.insertNewUser(user.getName(), user.getAge(), user.getAddress(), pw);
      DatabaseUpdateHelper.updateUserPassword(pw, user.getId());
      i++;
    }
  }

  private static void deserializeRoles(ObjectInputStream ois)
      throws DatabaseInsertException, SQLException, ClassNotFoundException, IOException {
    List<String> roleNames = (List<String>) ois.readObject();
    // iterate through the accs
    Iterator<String> i = roleNames.iterator();
    while (i.hasNext()) {
      String name = i.next();
      DatabaseInsertHelper.insertRole(name);
    }
  }

  private static void deserializeAccounts(ObjectInputStream ois)
      throws DatabaseInsertException, SQLException, ClassNotFoundException, IOException {
    List<Account> accs = (List<Account>) ois.readObject();
    // iterate through the accs
    Iterator<Account> i = accs.iterator();
    while (i.hasNext()) {
      Account account = i.next();
      DatabaseInsertHelper.insertAccount(account.getId(), true);
    }
  }

  private static void deserializeUserRoles(ObjectInputStream ois)
      throws DatabaseInsertException, SQLException, ClassNotFoundException, IOException {
    List<User> users = (List<User>) ois.readObject();
    Iterator<User> i = users.iterator();
    while (i.hasNext()) {
      User user = i.next();
      DatabaseInsertHelper.insertUserRole(user.getId(), user.getRoleId());
    }
  }
}

