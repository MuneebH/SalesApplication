package com.b07.users;

import com.b07.store.ShoppingCart;

public class Account {

  private int accountId;
  private ShoppingCart shoppingCart;

  /**
   * Initializes an Account for a customer, initialize the shopping cart as null
   * 
   * @param accountId
   */
  public Account(int accountId) {
    this.accountId = accountId;
    this.shoppingCart = null;
  }

  /**
   * Get the id of the account
   * 
   * @return the acctId
   */
  public int getId() {
    return this.accountId;
  }

  /**
   * Set the id of the account
   * 
   * @param accountId, the id that's supposed to be set
   */
  public void setId(int accountId) {
    this.accountId = accountId;
  }

  /**
   * Set the shopping cart for the account
   * 
   * @param shoppingCart, the shopping cart to be set
   */

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  /**
   * Get the shopping cart
   * 
   * @return the shopping cart of the account
   */
  public ShoppingCart getShoppingCart() {
    return this.shoppingCart;
  }

}
