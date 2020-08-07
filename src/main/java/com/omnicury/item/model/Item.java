package com.omnicury.item.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="tbl_omnicure_cart")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="item_id")
	@ApiModelProperty(hidden = true)
	private int itemId;
	
	@Column(name="product_id")
	private int productId;

	@Column(name="n0_item")
	private int noItem;
	
	@Column(name="price_per_product")
	private float pricePerProduct;
	
	@Transient
	private float  totalProductPrice;

	public float getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(float totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	@ManyToOne
	@JoinColumn(name="Shopping_cart_id")
	@ApiModelProperty(hidden = true)
    private ShoppingCart shoppingCart; 

	@JsonBackReference
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
		//shoppingCart.getContents().add(this);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

//
	public int getNoItem() {
		return noItem;
	}

	public void setNoItem(int noItem) {
		this.noItem = noItem;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public float getPricePerProduct() {
		return pricePerProduct;
	}

	public void setPricePerProduct(float pricePerProduct) {
		this.pricePerProduct = pricePerProduct;
	}

}
