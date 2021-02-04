package com.b07.store;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import com.b07.exceptions.UserIdInvalidException;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidQuantityException;
import com.b07.exceptions.TotalPriceInvalidException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.users.Customer;

public class ShoppingCart {

  HashMap<Item, Integer> items;
  Customer customer;
  BigDecimal total;
  final BigDecimal TAXRATE = new BigDecimal(0.13);

  /**
   * the constrcutor
   * 
   * @param customer
   */
  public ShoppingCart(Customer customer) {
    this.customer = customer;
  }

  /**
   * add an item to the cart
   * 
   * @param item
   * @param quantity
   * @throws SQLException
   * @throws InvalidQuantityException
   */

  public void addItem(Item item, int quantity) throws SQLException, InvalidQuantityException {
    items.put(item, quantity);
    DatabaseUpdateHelper.updateInventoryQuantity(quantity, item.getId());
  }

  /**
   * remove item from cart
   * 
   * @param item
   * @param quantity
   * @throws SQLException
   * @throws InvalidQuantityException
   */
  public void removeItem(Item item, int quantity) throws SQLException, InvalidQuantityException {
    int inventoryQuantity = DatabaseSelectHelper.getInventoryQuantity(item.getId());
    int newQuantity = inventoryQuantity - quantity;
    if (newQuantity <= 0) {
      items.remove(item);
    } else {
      DatabaseUpdateHelper.updateInventoryQuantity(newQuantity, item.getId());
    }
  }

  /**
   * get all the items
   * 
   * @return the items in a list
   * @throws SQLException
   */
  public List<Item> getItems() throws SQLException {
    return DatabaseSelectHelper.getAllItems();
  }

  /**
   * get the customer
   * 
   * @return the customer
   */

  public Customer getCustomer() {
    return this.customer;
  }

  /**
   * 
   * @return the total
   * @throws SQLException
   */
  public BigDecimal getTotal() throws SQLException {
    BigDecimal total = new BigDecimal(0);
    for (Item item : items.keySet()) {
      BigDecimal quantity = new BigDecimal(items.get(item));
      total = total.add(item.getPrice().multiply(quantity));
    }
    return total;
  }

  /**
   * 
   * @return return the taxrate (0.13)
   */
  public BigDecimal getTaxRate() {
    return TAXRATE;
  }

  /**
   * check out the cart
   * 
   * @param shoppingCart
   * @return true if it successfu
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws UserIdInvalidException
   * @throws TotalPriceInvalidException
   * @throws InvalidQuantityException
   */
  public boolean checkOut(ShoppingCart shoppingCart) throws DatabaseInsertException, SQLException,
      UserIdInvalidException, TotalPriceInvalidException, InvalidQuantityException {

    boolean check = true;
    if (shoppingCart.getCustomer() != null) {
      BigDecimal total = this.getTotal();
      total = total.multiply(TAXRATE);
      DatabaseInsertHelper db = new DatabaseInsertHelper();
      db.insertSale(customer.getId(), total);
    }
    Inventory inventory = DatabaseSelectHelper.getInventory();
    HashMap<Item, Integer> itemMap = inventory.getItemMap();
    for (Item item : itemMap.keySet()) {
      for (Item checkOutItem : this.items.keySet()) {
        int quantity = itemMap.get(item);
        int checkOutQuantity = items.get(checkOutItem);
        if (checkOutQuantity > quantity) {
          check = false;
        } else {
          int lastQuantity = quantity - checkOutQuantity;
          DatabaseUpdateHelper.updateInventoryQuantity(lastQuantity, item.getId());
        }
      }
    }
    shoppingCart.clearCart();
    return check;
  }

  public void clearCart() {
    items.clear();
  }



}
