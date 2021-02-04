/**
 * 
 */
package com.b07.store;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.TotalPriceInvalidException;
import com.b07.inventory.Item;
import com.b07.users.User;

/**
 * @author Muneeb
 *
 */
public class SaleImpl implements Sale {

  private int id;
  private User user;
  private BigDecimal price;
  HashMap<Item, Integer> itemMap;

  public SaleImpl(int id, User user, BigDecimal price, HashMap<Item, Integer> itemMap)
      throws DatabaseInsertException, SQLException, UserIdInvalidException, TotalPriceInvalidException {
    this.id = id;
    this.user = user;
    this.price = price;
    this.itemMap = itemMap;
    DatabaseInsertHelper db = new DatabaseInsertHelper();
    db.insertSale(id, price);
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public User getUser() {
    return this.user;
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public BigDecimal getTotalPrice() {
    return this.price;
  }

  @Override
  public void setTotalPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public HashMap<Item, Integer> getItemMap() {
    return this.itemMap;
  }

  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    this.itemMap = itemMap;
  }

}
