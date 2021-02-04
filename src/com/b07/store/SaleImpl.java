package com.b07.store;

import com.b07.inventory.Item;
import com.b07.users.User;
import java.math.BigDecimal;
import java.util.HashMap;

public class SaleImpl implements Sale {

  private int id;

  private User user;

  private BigDecimal totalPrice;

  private HashMap<Item, Integer> itemMap;

  // Overload the method to be used as a single
  public SaleImpl() {

  }

  /**
   * Initiliazes the id, user, totalPrice and the Map
   * 
   * @param id Integer
   * @param User USER object
   * @param totalPrice BigDecimal total price
   * @param itemMap hashmap for inventory
   */
  public SaleImpl(int id, User user, BigDecimal totalPrice) {
    // sets the id, user, totalPrice, and itemMap
    this.id = id;
    this.user = user;
    this.totalPrice = totalPrice;
    this.itemMap = new HashMap<Item, Integer>();
    

  }

  @Override
  public int getId() {
    // Get the ID
    return id;
  }

  @Override
  public void setId(int id) {
    // Set the ID
    this.id = id;
  }

  @Override
  public User getUser() {
    // Get the user
    return this.user;
  }

  @Override
  public void setUser(User user) {
    // Set the user
    this.user = user;
  }

  @Override
  public BigDecimal getTotalPrice() {
    // Get the total price
    return this.totalPrice;
  }

  @Override
  public void setTotalPrice(BigDecimal price) {
    // Set the total price
    this.totalPrice = price;
  }

  @Override
  public HashMap<Item, Integer> getItemMap() {
    // get the Item map
    return this.itemMap;
  }

  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    // Set the item map
    this.itemMap = itemMap;
  }

}