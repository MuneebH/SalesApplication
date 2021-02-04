package com.b07.inventory;

import java.sql.SQLException;
import java.util.HashMap;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidQuantityException;
import com.b07.exceptions.ItemIdInvalidException;

public class InventoryImpl implements Inventory {
	
	int totalItems; 
	HashMap<Item, Integer> itemMap; 

	public InventoryImpl(HashMap<Item, Integer> itemMap, int totalItems) throws DatabaseInsertException, SQLException, InvalidQuantityException, ItemIdInvalidException {
		this.itemMap = itemMap;
		this.totalItems = totalItems; 
		DatabaseInsertHelper db = new DatabaseInsertHelper();
		for (Item key : this.itemMap.keySet()) {
			db.insertInventory(key.getId(), this.itemMap.get(key));
		}
	}
	
	@Override
	public HashMap<Item, Integer> getItemMap() {
		return this.itemMap;
	}

	@Override
	public void setItemMap(HashMap<Item, Integer> itemMap) {
		this.itemMap = itemMap;
	}

	@Override
	public void updateMap(Item item, int value) {
		this.itemMap.put(item, value);
	}

	@Override
	public int getTotalItems() {
		return totalItems; 
	}

	@Override
	public void setTotalItems(int total) {
		this.totalItems = total; 
	}

}
