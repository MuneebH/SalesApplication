package com.b07.inventory;

import java.math.BigDecimal;
import java.sql.SQLException;
import com.b07.exceptions.InvalidNameException;
import com.b07.exceptions.InvalidPriceException;
import com.b07.exceptions.NameTooLongException;

public interface Item {

  /**
   * Gets the id
   * 
   * @return the id
   */
  public int getId();

  /**
   * Sets the id
   * 
   * @param id
   */
  public void setId(int id);

  /**
   * gets the name
   * 
   * @return
   */
  public String getName();

  /**
   * Sets the name
   * 
   * @param name
   * @throws SQLException database error
   * @throws NameTooLongException name too long
   * @throws InvalidNameException not in certain criteria
   */
  public void setName(String name) throws SQLException, NameTooLongException, InvalidNameException;

  /**
   * Gets the price of item
   * 
   * @return
   */
  public BigDecimal getPrice();

  /**
   * sets the price of Item
   * 
   * @param price for item
   * @throws SQLException database nonexistant
   * @throws InvalidPriceException price in negatives
   */
  public void setPrice(BigDecimal price) throws SQLException, InvalidPriceException;

}
