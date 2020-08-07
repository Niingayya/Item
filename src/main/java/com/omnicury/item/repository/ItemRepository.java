package com.omnicury.item.repository;

import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnicury.item.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Item findByItemId(int itemId);

}
