package com.microservice.itemservice.repository;

import com.microservice.itemservice.model.Item;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM item WHERE name LIKE %?1%")
    List<Item> findAllItems(String searchText, PageRequest of);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM item WHERE name LIKE %?1%")
    Long findAllItemCount(String searchText);
}
