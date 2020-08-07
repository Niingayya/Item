package com.omnicury.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnicury.item.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>
{

      public ShoppingCart findByShoppingCartId(int shoppingCartId);
}
