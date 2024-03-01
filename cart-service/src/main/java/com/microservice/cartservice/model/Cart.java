package com.microservice.cartservice.model;

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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id",length = 45)
    private long cartId;
    @Column(name = "qty",length = 10)
    private int qty;
    @Column(name = "product_id",length = 45)
    private long productId;
    @Column(name = "user_id",length = 45)
    private long userId;
}
