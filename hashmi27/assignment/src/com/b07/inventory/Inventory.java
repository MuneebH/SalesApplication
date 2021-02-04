package com.b07.inventory;

import java.util.HashMap;

public interface Inventory {
	
    /**
     * return the itemMap of the inventory
     * @return the item map (item: quantity)
     */
	public HashMap<Item, Integer> getItemMap();
	
	/**
	 * set the item MAp
	 * @param itemMap
	 */
	public void setItemMap(HashMap<Item, Integer> itemMap);
	
	/**
	 * update the map with item, value
	 * @param item
	 * @param value
	 */
	public void updateMap(Item item, int value);
	
	/**
	 * get the total items in the inventory
	 * @return totalitems in inventory
	 */
	public int getTotalItems();
	
	/**
	 * set the totalitems in the inventory
	 * @param total
	 */
	public void setTotalItems(int total);
	
}
