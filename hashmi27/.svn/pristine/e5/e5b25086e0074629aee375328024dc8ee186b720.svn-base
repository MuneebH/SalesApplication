package com.b07.store;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class SalesLogImpl implements SalesLog {
  
  BigDecimal profit;
  int saleId;   
  HashMap<Integer, BigDecimal> map;

  public SalesLogImpl(int saleId, BigDecimal profit) {
    this.map = new HashMap<Integer, BigDecimal>();
    this.map.put(this.saleId, this.profit);
  }
  @Override
  public HashMap<Integer, BigDecimal> getSaleMap() {
    return this.map;
  }
  @Override
  public Set<Integer> getSaleIds() {
    return this.map.keySet();
  }
  @Override
  public Collection<BigDecimal> getProfits() {
    return this.map.values();
  }

}
