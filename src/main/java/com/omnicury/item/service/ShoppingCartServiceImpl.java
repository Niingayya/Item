package com.omnicury.item.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnicury.item.model.Item;
import com.omnicury.item.model.ShoppingCart;
import com.omnicury.item.repository.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Override
	public ShoppingCart createShoppingCat(ShoppingCart shoppingCart) {
		float totalAmount = 0;
		float totalProductPrice = 0;
		for (Item items : shoppingCart.getContents()) {
			totalProductPrice = totalProductPrice + (items.getPricePerProduct() * items.getNoItem());
			totalAmount = totalAmount + totalProductPrice;
			items.setTotalProductPrice(totalProductPrice);
			totalProductPrice = 0;
		}
		shoppingCart.setTotalAmount(totalAmount);

		return shoppingCartRepository.save(shoppingCart);

	}

	@Override
	public ShoppingCart updateShoppingCart(int shoppingCartId, @Valid Item item) {
		boolean dataFoundStatus = false;
		float totalAmount = 0;
		float totalProductPrice = 0;
		ShoppingCart shoppingCart = shoppingCartRepository.findByShoppingCartId(shoppingCartId);
		if (shoppingCart != null) {   
			
			for (Item items : shoppingCart.getContents()) {
				totalProductPrice = 0;
				
				if (items.getProductId() == item.getProductId()) {
					items.setNoItem(item.getNoItem());
					items.setPricePerProduct(item.getPricePerProduct());
					totalProductPrice = totalProductPrice + (item.getPricePerProduct() * item.getNoItem());
					totalAmount = totalAmount + totalProductPrice;
					items.setTotalProductPrice(totalProductPrice);
					shoppingCart = shoppingCartRepository.save(shoppingCart);
					dataFoundStatus = true;
				     } 
				else {
					totalProductPrice = totalProductPrice + (items.getPricePerProduct() * items.getNoItem());
					items.setTotalProductPrice(totalProductPrice);
					totalAmount += totalProductPrice;
				     }
				}
			         
			if (!dataFoundStatus) {
				item.setShoppingCart(shoppingCart);
				shoppingCart.getContents().add(item);
				shoppingCart = shoppingCartRepository.save(shoppingCart);
				int productId=0;
				totalAmount=0;
				for (Item items : shoppingCart.getContents()) {
					totalProductPrice = 0;
					totalProductPrice = totalProductPrice + (items.getPricePerProduct() * items.getNoItem());
					totalAmount = totalAmount + totalProductPrice;
					items.setTotalProductPrice(totalProductPrice);
					productId=items.getItemId();
					
				}
			}
			shoppingCart.setTotalAmount(totalAmount);
		}
		return shoppingCart;

	}

	@Override
	public ShoppingCart getShoppingCart(int shoppingCartId) {
		float totalAmount = 0;
		float totalProductPrice = 0;
		ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId).get();
		System.out.println("in serviceImpl" + shoppingCart);
		if (shoppingCart != null) {
			for (Item items : shoppingCart.getContents()) {
				totalProductPrice = 0;
				totalProductPrice = totalProductPrice + (items.getPricePerProduct() * items.getNoItem());
				totalAmount = totalAmount + totalProductPrice;
				items.setTotalProductPrice(totalProductPrice);

				shoppingCart.setTotalAmount(totalAmount);

			}
		}
		return shoppingCart;
	}

	@Override
	public ShoppingCart deleteShoppingCart(int shoppingCartId, int itemId) {
		float totalAmount = 0;
		float totalProductPrice = 0;
		ShoppingCart shoppingCart = shoppingCartRepository.findByShoppingCartId(shoppingCartId);
		if (shoppingCart != null) {
			for (Item items : shoppingCart.getContents()) {
				
				if (items.getItemId() == itemId) {
					items.setShoppingCart(shoppingCart);
					shoppingCart.getContents().remove(items);
					shoppingCart = shoppingCartRepository.save(shoppingCart);
					for (Item itemss : shoppingCart.getContents()) {
						totalProductPrice = 0;
					totalProductPrice = totalProductPrice + (itemss.getPricePerProduct() * itemss.getNoItem());
						totalAmount = totalAmount + totalProductPrice;
						itemss.setTotalProductPrice(totalProductPrice);
					}
						shoppingCart.setTotalAmount(totalAmount);
					
				}
			}
		}
		return shoppingCart;
	}

	@Override
	public boolean deleteShoppingCart(int shoppingCartId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByShoppingCartId(shoppingCartId);
		if (shoppingCart != null) {
			shoppingCartRepository.delete(shoppingCart);
		}
		return true;
	}

}
