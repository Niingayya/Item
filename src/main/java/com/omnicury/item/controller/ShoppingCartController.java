package com.omnicury.item.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicury.item.model.Item;
import com.omnicury.item.model.ShoppingCart;
import com.omnicury.item.service.ItemService;
import com.omnicury.item.service.ShoppingCartService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
public class ShoppingCartController {

	private static final Logger LOGGER = LogManager.getLogger(ShoppingCartController.class);

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ItemService itemService;

	@ApiOperation(httpMethod = "POST", value = "Creates a new shopping cart.")
	@RequestMapping(value = "/shoppingcart", method = RequestMethod.POST)
	public ResponseEntity<Object> createShoppingCart(@RequestBody ShoppingCart shoppingCart)
			throws JsonProcessingException {
		LOGGER.info(":: in createShoppingCart() :: ");
		
		ShoppingCart shoppingCart1 = shoppingCartService.createShoppingCat(shoppingCart);
		
		return new ResponseEntity<Object>(shoppingCart1,HttpStatus.CREATED);
	}

	@ApiOperation(httpMethod = "GET", value = "To get shopping cart of particular ShoppingCartId")
	@RequestMapping(value = "/shoppingcart/{shoppingCartId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getShoppingCart(@PathVariable("shoppingCartId") int shoppingCartId) {

		ShoppingCart shoppingCart = null;
		LOGGER.info(":: in getShoppingCart() :: ");
		try {
			shoppingCart = shoppingCartService.getShoppingCart(shoppingCartId);
		} catch (Exception e) {
			if (e instanceof NoSuchElementException) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<Object>(shoppingCart, HttpStatus.OK);

	}

	@ApiOperation(httpMethod = "PUT", value = "Update Contents in shoppingCart by ShoppingCartId with valid Item RequestBody.")
	@PutMapping(value = "/shoppingCart/{shoppingCartId}", produces = "application/json")
	public ResponseEntity<Object> updateShoppingCart(@PathVariable("shoppingCartId") int shoppingCartId,
			@RequestBody @Valid Item item) {

		LOGGER.info(":: in updateShoppingCart() of item ::");
		ShoppingCart shoppingCartResponse = shoppingCartService.updateShoppingCart(shoppingCartId, item);

		if (shoppingCartResponse != null) {
			return new ResponseEntity<Object>(shoppingCartResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(httpMethod = "DELETE", value = "Delete Item By itemId and shoppingCartId as PathVariable.")
	@RequestMapping(value = "/shoppingcart/{shoppingCartId}/item/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteShoppingCart(@PathVariable("shoppingCartId") int shoppingCartId,
			@PathVariable("itemId") int itemId) {

		LOGGER.info(":: in deleteShoppingCart() of item::");
		ShoppingCart shoppingCart = shoppingCartService.deleteShoppingCart(shoppingCartId, itemId);

		return new ResponseEntity<Object>(shoppingCart, HttpStatus.OK);
	}

	@ApiOperation(httpMethod = "DELETE", value = "Delete shoppingCart By ShoppingCartId.")
	@RequestMapping(value = "/shoppingcart/{shoppingCartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteShoppingCart(@PathVariable("shoppingCartId") int shoppingCartId) {

		LOGGER.info(":: in deleteShoppingCart() by shoppingCartId::");
		boolean status = shoppingCartService.deleteShoppingCart(shoppingCartId);
		if (status = true) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(httpMethod = "PUT", value = "Update Item in shoppingCart by ShoppingCartId,ItemId with Item RequestBody.")
	@PutMapping(value = "/shoppingCart/{shoppingCartId}/item/{itemId}", produces = "application/json")
	public ResponseEntity<Object> updateShoppingCart(@RequestBody Item item,@PathVariable("shoppingCartId") int shoppingCartId,@PathVariable("itemId") int itemId
			) {
		Item itemResponse=itemService.updateItem(item, itemId, shoppingCartId);
		
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(shoppingCartId);
		
		if (itemResponse != null) {
			return new ResponseEntity<Object>(shoppingCart, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}
}
