package com.b07.store;

import com.b07.inventory.Item;
import com.b07.users.User;
import java.math.BigDecimal;
import java.util.HashMap;

public interface Sale {

  /**
   * Get the saleId
   * 
   * @return Integer ID for sale.
   */
  public int getId();

  /**
   * Set the saleId
   * 
   * @param Takes in a int id
   */
  public void setId(int id);

  /**
   * Get the User in the sale
   * 
   * @return the User
   */
  public User getUser();

  /**
   * Set the User associated with the Sale
   * 
   * @param user
   */
  public void setUser(User user);


  /**
   * Set the total Price
   * 
   * @return BigDecimal total Price
   */
  public BigDecimal getTotalPrice();


  /**
   * Set total price
   * 
   * @param BigDecimal price to set
   */
  public void setTotalPrice(BigDecimal price);


  /**
   * Return the map associated
   * 
   * @return the Inventory of the sale
   */
  public HashMap<Item, Integer> getItemMap();


  /**
   * Set the itemMap associated with the sale
   * 
   * @param itemMap
   */
  public void setItemMap(HashMap<Item, Integer> itemMap);


}
