package com.b07.store;

import java.util.List;

public interface SalesLog {

  /**
   * Gets the salesLog for ledger in a list
   * 
   * @return
   */
  public List<Sale> getSaleLog();

}
