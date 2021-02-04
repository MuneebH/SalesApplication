package com.b07.inventory;

import java.sql.SQLException;
import java.util.HashMap;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.NegativeInventoryQuantityException;

public class InventoryImpl implements Inventory {

  private HashMap<Item, Integer> inventory = new HashMap<>();
  private int total;
  DatabaseInsertHelper databaseInsert = new DatabaseInsertHelper();

  // Overload if empty InventoryImpl is given
  public InventoryImpl() {}

  /**
   * Sets the inventory of the database
   * 
   * @param itemMap inventory
   * @throws NegativeInventoryQuantityException if there is a negative inventory
   * @throws DatabaseInsertException insertion exist already
   * @throws SQLException Not in the database
   */
  public InventoryImpl(HashMap<Item, Integer> itemMap)
      throws NegativeInventoryQuantityException, DatabaseInsertException, SQLException {
    // itterate through the itemMap maping te key
    for (Item key : itemMap.keySet()) {
      // if the value is less than 0 meaning negative quantity. Throws error
      if (itemMap.get(key) < 0) {
        throw new NegativeInventoryQuantityException();
      }
      // inserts it to the database
      databaseInsert.insertInventory(key.getId(), itemMap.get(key));
    }


    this.inventory = itemMap;
  }

  /**
   * Takes in a boolean to see if if you want database calls or not
   * 
   * @param itemMap
   * @param hi if it exist
   * @throws NegativeInventoryQuantityException if negative inventory
   */
  public InventoryImpl(HashMap<Item, Integer> itemMap, boolean hi)
      throws NegativeInventoryQuantityException {
    for (Item key : itemMap.keySet()) {
      if (itemMap.get(key) < 0) {
        throw new NegativeInventoryQuantityException();
      }
    }
    this.inventory = itemMap;
  }



  @Override
  public HashMap<Item, Integer> getItemMap() {
    // Returns the inventory
    return this.inventory;
  }

  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap)
      throws NegativeInventoryQuantityException, DatabaseInsertException, SQLException {
    // itterates through the itemMap mapping to the key
    for (Item key : itemMap.keySet()) {
      // if its negative inventory throws error
      if (itemMap.get(key) < 0) {
        throw new NegativeInventoryQuantityException();
      }
    }
    // gets the itemMap
    this.inventory = itemMap;

  }

  @Override
  public void updateMap(Item item, Integer value)
      throws NegativeInventoryQuantityException, SQLException {
    // Calculates total and updates the Map
    // given the Item throws error if negative
    for (Item key : inventory.keySet()) {
      if (key.getName().equals(item.getName())) {
        if ((inventory.get(key) + value) < 0) {
          throw new NegativeInventoryQuantityException();
        }
        // computes the added inventory to the database and to the inventory
        inventory.compute(key, (k, v) -> v + value);
        DatabaseUpdateHelper.updateInventoryQuantity(this.inventory.get(key), key.getId());

      }
    }


  }

  @Override
  public void setItem(Item item, int value) throws NegativeInventoryQuantityException {
    if (value < 0) {
      throw new NegativeInventoryQuantityException();
    }
    inventory.put(item, value);
  }


}
