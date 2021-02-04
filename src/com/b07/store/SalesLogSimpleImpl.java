package com.b07.store;

import java.util.ArrayList;
import java.util.List;


public class SalesLogSimpleImpl implements SalesLog {
  // sets the SalesLog in an arrayList
  private ArrayList<Sale> salesLog;

  /**
   * Sets the sales log
   * 
   * @param salesLog ledger
   */
  public SalesLogSimpleImpl(ArrayList<Sale> salesLog) {
    // Sets the sales log
    this.salesLog = salesLog;
  }

  @Override
  public List<Sale> getSaleLog() {
    // gets the sales log
    return this.salesLog;
  }

}
