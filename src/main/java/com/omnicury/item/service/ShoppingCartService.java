package com.omnicury.item.service;

import java.util.Optional;

import javax.validation.Valid;

import com.omnicury.item.model.Item;
import com.omnicury.item.model.ShoppingCart;

public interface ShoppingCartService {
	
	public ShoppingCart createShoppingCat(ShoppingCart shoppingCart);
	
	public ShoppingCart updateShoppingCart(int shoppingCartId,@Valid Item item);
	
	public ShoppingCart getShoppingCart(int shoppingCartId);
	
	public ShoppingCart deleteShoppingCart(int shoppingCartId,int itemId);
	
	public boolean deleteShoppingCart(int shoppingCartId);



}
