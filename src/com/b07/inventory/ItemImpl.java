package com.b07.inventory;

import java.math.BigDecimal;
import java.sql.SQLException;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidNameException;
import com.b07.exceptions.InvalidPriceException;
import com.b07.exceptions.NameTooLongException;

public class ItemImpl implements Item {

  // gets the inits
  private int itemId;
  private String name;
  private BigDecimal price;
  DatabaseInsertHelper databaseInsert = new DatabaseInsertHelper();


  // overrides so you can have empty Item
  public ItemImpl() {

  }

  /**
   * Sets the itemId, name, price to the Item
   * 
   * @param ItemId for item
   * @param name for item
   * @param price for item
   * @throws NameTooLongException name is too long
   * @throws InvalidPriceException price invalid
   * @throws InvalidNameException name invalid
   * @throws DatabaseInsertException already exist insertion
   * @throws SQLException in database already
   */
  public ItemImpl(int itemId, String name, BigDecimal price) throws NameTooLongException,
      InvalidPriceException, InvalidNameException, DatabaseInsertException, SQLException {

    this.itemId = itemId;
    // if name greater than 64 throws error
    if (name.length() > 64) {
      throw new NameTooLongException();
    }
    // if price is negative throws error
    else if (price.intValue() < 0) {
      throw new InvalidPriceException();
    }
    // if not in itemTypes then throws error
    int count = 0;
    boolean found = false;
    while (ItemTypes.values().length > count && found == false) {
      if (ItemTypes.values()[count].toString().equals(name)) {
        found = true;
      }
      count += 1;
    }
    if (found == false) {
      throw new InvalidNameException();
    }
    // sets variables for name, price
    // adds it to the database as well
    this.name = name;
    this.price = price;
    databaseInsert.insertItem(name, price);


  }

  @Override
  public int getId() {
    // gets the itemId
    return itemId;
  }

  @Override
  public void setId(int id) {
    // Sets the itemID
    this.itemId = id;

  }

  @Override
  public String getName() {
    // gets the name
    return this.name;
  }

  @Override
  public void setName(String name) throws NameTooLongException, InvalidNameException, SQLException {
    // sets the name
    if (name.length() > 64) {
      throw new NameTooLongException();
    }
    int count = 0;
    boolean found = false;
    // sees if its in the enum
    while (ItemTypes.values().length > count && found == false) {
      if (ItemTypes.values()[count].toString().equals(name)) {
        found = true;
      }
      count += 1;
    }
    if (found == false) {
      throw new InvalidNameException();
    }
    // sets name
    this.name = name;



  }

  @Override
  public BigDecimal getPrice() {
    // gets the price
    return this.price;
  }

  @Override
  public void setPrice(BigDecimal price) throws InvalidPriceException, SQLException {
    // sets the price
    if (price.intValue() < 0) {
      throw new InvalidPriceException();
    }
    this.price = price;

  }


}
