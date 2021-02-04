package com.b07.store;

import java.math.BigDecimal;
import java.sql.SQLException;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.NegativeInventoryQuantityException;
import com.b07.inventory.Inventory;
import com.b07.inventory.InventoryImpl;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {

  // Sets all the values required
  private HashMap<Item, Integer> items = new HashMap<>();
  private Customer customer;
  private BigDecimal total;
  private final BigDecimal TAXRATE = new BigDecimal("1.13");

  /**
   * Sets the costumer requierd for the ShoppingCart
   * 
   * @param customer
   */
  public ShoppingCart(Customer customer) {
    this.customer = customer;
  }

  public ShoppingCart() {
    // overload constructor
  }

  /**
   * Sets the item and quantity to the cart
   * 
   * @param the item
   * @param the quantity
   * @throws DatabaseInsertException if already exist
   * @throws SQLException if in database
   */
  public void addItem(Item item, int quantity) throws DatabaseInsertException, SQLException {
    
    boolean found = false;
    for(Item items : this.items.keySet()) {
      if(item.getName().equals(items.getName())) {
        found = true;
        this.items.compute(items, (k, v) -> v + quantity);
      }
    }
    if(found == false) {
      items.put(item, quantity);
    }
  }
  
  /**
   * Sets a customer
   * 
   * @param customer
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * removes the item and quantity to the cart
   * 
   * @param item
   * @param quantity
   * @throws SQLException if already in the database
   * @throws DatabaseInsertException if database insertion is existant
   * @throws NegativeInventoryQuantityException if negative inventory quantity
   */
  public void removeItem(Item item, int quantity)
      throws SQLException, DatabaseInsertException, NegativeInventoryQuantityException {
    // add exception if v - quantity = negative
    Item found = null;
    for (Item key : items.keySet()) {
      if (item.getName().equals(key.getName())) {
        found = key;
      }
    }

    // if the items hit 0 removes it
    if (this.items.get(found) - quantity == 0) {
      this.items.remove(found);
    }
    // not possible to throws error
    else if (this.items.get(found) - quantity < 0) {
      throw new NegativeInventoryQuantityException();

    } else {
      // edits it
      items.compute(found, (k, v) -> v - quantity);
    }

  }

  /**
   * Gets a list of all the items in the cart
   * 
   * @return
   */
  public List<Item> getItems() {
    // gets the list of items in the array list
    List<Item> listOfItems = new ArrayList<>(this.items.keySet());
    // returns that list
    return listOfItems;
  }

  /**
   * Gets the entire cart .
   * 
   * @return
   */
  public HashMap<Item, Integer> getCart() {
    return this.items;
  }

  /**
   * Gets the customer
   * 
   * @return
   */
  public Customer getCustomer() {
    return this.customer;
  }

  /**
   * Gets the tax-rate
   * 
   * @return bigdecimal taxrate
   */
  public BigDecimal getTaxRate() {
    return TAXRATE;
  }

  /**
   * Clears the cart
   * 
   * @throws SQLException
   */
  public void clearCart() throws SQLException {
    this.items.clear();
  }

  /**
   * gets the total amount before purchase
   * 
   * @return the total
   */
  public BigDecimal getTotal() {
    BigDecimal itemPrice = new BigDecimal("0");
    for (Item key : items.keySet()) {
      BigDecimal quantity = new BigDecimal(items.get(key));
      itemPrice = itemPrice.add(key.getPrice().multiply(quantity));
    }
    itemPrice = itemPrice.multiply(TAXRATE).setScale(2, 2);
    return itemPrice;

  }

  /**
   * Return true iff if its all checkedout
   * 
   * @return boolean if checkouted
   * @throws DatabaseInsertException
   * @throws SQLException
   */
  public int checkOut() throws DatabaseInsertException, SQLException {
    BigDecimal totalCheck = this.getTotal();
    DatabaseInsertHelper databaseInsert = new DatabaseInsertHelper();
    int saleId = databaseInsert.insertSale(customer.getId(), totalCheck);
    return saleId;
  }

}
