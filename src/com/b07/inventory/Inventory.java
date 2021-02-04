package com.b07.inventory;

import java.sql.SQLException;
import java.util.HashMap;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.NegativeInventoryQuantityException;

public interface Inventory {

  /**
   * Retrives the inventory mapping systems
   * 
   * @return HashMap with Item attached with Integer Referencing the Item and Quantity of the
   *         inventory
   */
  public HashMap<Item, Integer> getItemMap();


  /**
   * Sets the itemMap for the inventory mapping system Which is a HashMap
   * 
   * @param itemMap
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws NegativeInventoryQuantityException
   */
  public void setItemMap(HashMap<Item, Integer> itemMap)
      throws DatabaseInsertException, SQLException, NegativeInventoryQuantityException;


  /**
   * Takes in an Item and value and sets it to the inventory
   * 
   * @param item object
   * @param value integer
   * @throws SQLException
   * @throws NegativeInventoryQuantityException
   */
  public void updateMap(Item item, Integer value)
      throws SQLException, NegativeInventoryQuantityException;


  /**
   * Takes in an Item and Integer and sets it to the inventory
   * 
   * @param item object
   * @param value integer
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws NegativeInventoryQuantityException
   */
  public void setItem(Item item, int value)
      throws DatabaseInsertException, SQLException, NegativeInventoryQuantityException;

}
