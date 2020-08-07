package com.omnicury.item.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnicury.item.model.Item;
import com.omnicury.item.model.ShoppingCart;
import com.omnicury.item.repository.ItemRepository;



@Service

public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepository;
	

	@Autowired
	ShoppingCartService shoppingService;

	@Override
	public Item createItem(Item item) {
		
		return itemRepository.save(item);
		
		
		}

	public Item updateItem(Item item,int itemId,int shoppingCartId) {
		float totalAmount = 0;
		float totalProductPrice = 0;
       Item  item1=itemRepository.findByItemId(itemId);
       if(item1!=null){
    	   item1.setNoItem(item.getNoItem());
    	   item1.setPricePerProduct(item.getPricePerProduct());
    	   item1.setProductId(item.getProductId());
    	   totalProductPrice = totalProductPrice + (item.getPricePerProduct() * item.getNoItem());
			totalAmount = totalAmount + totalProductPrice;
			item1.setTotalProductPrice(totalProductPrice);
       }
		return itemRepository.save(item1);
		
	}

	@Override
	public Item getItem(int itemId) {
		Item item=itemRepository.findByItemId(itemId);
		return item;
	}

}
