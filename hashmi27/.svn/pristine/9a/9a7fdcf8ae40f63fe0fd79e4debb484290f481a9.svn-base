package com.b07.store;

import java.math.BigDecimal;
import java.util.HashMap;
import com.b07.inventory.Item;
import com.b07.users.User;

public interface Sale {

  /**
   * 
   * @return the id of the sale
   */
  public int getId();

  /**
   * set the id of the sale
   * 
   * @param id
   */
  public void setId(int id);

  /**
   * 
   * @return the user of the sale
   */
  public User getUser();

  /**
   * set the user
   * 
   * @param user
   */
  public void setUser(User user);

  /**
   * get the total price of the sale
   * 
   * @return
   */
  public BigDecimal getTotalPrice();

  /**
   * set the total price of the sale
   * 
   * @param price
   */
  public void setTotalPrice(BigDecimal price);

  /**
   * get the item map
   * 
   * @return the item map (item: how much there is)
   */
  public HashMap<Item, Integer> getItemMap();

  /**
   * set the item map
   * 
   * @param itemMap
   */
  public void setItemMap(HashMap<Item, Integer> itemMap);

}
