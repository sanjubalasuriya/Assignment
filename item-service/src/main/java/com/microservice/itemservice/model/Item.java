package com.microservice.itemservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id",length = 45)
    private long itemId;
    @Column(name = "name",length = 100)
    private String name;
    @Column(name = "price",length = 30)
    private double price;
    @Column(name = "qty",length = 10)
    private int qty;


}
