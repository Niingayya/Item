package com.omnicury.item.service;

import java.util.Optional;

import com.omnicury.item.model.Item;

public interface ItemService {
	
	public Item createItem(Item item);
	
	public Item updateItem(Item item, int itemId,int shoppingCartId);
	
	public Item getItem(int itemId);
	
}
