/**
 * 
 */
package com.b07.inventory;

import java.math.BigDecimal;
import java.sql.SQLException;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.NameInvalidException;
import com.b07.exceptions.PriceInvalidException;

/**
 * @author Muneeb
 *
 */
public class ItemImpl implements Item {

  private int id;

  private String name;

  private BigDecimal price;

  /**
   * item implementation constructor
   * @param id
   * @param name
   * @param price
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws NameInvalidException
   * @throws PriceInvalidException
   */
  
  public ItemImpl(int id, String name, BigDecimal price)
      throws DatabaseInsertException, SQLException, NameInvalidException, PriceInvalidException {
    this.id = id;
    this.name = name;
    this.price = price;
    DatabaseInsertHelper db = new DatabaseInsertHelper();
    db.insertItem(name, price);
  }

  /**
   * get the id 
   */
  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public BigDecimal getPrice() {
    return this.price;
  }

  @Override
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}