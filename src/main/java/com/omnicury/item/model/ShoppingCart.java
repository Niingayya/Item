package com.omnicury.item.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="shopping_cart")
@JsonIgnoreProperties(ignoreUnknown=true)
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="shopping_cart_id")
	@ApiModelProperty(hidden=true)
	private int shoppingCartId;
	
	@Column(name="account_key")
	private String accountKey;
	
	@Transient
	private float totalAmount;

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Item> contents;

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	
	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	 @JsonManagedReference
	public Set<Item> getContents() {
		return contents;
	}
	
	public void setContents(Set<Item> contents) {
		this.contents=contents;
		for (Item item : contents) {
            // initializing the shoppingCartProduct instance in Children class (Owner side)
            // so that it is not a null and PK can be created
            item.setShoppingCart(this);
        }
	}
}
