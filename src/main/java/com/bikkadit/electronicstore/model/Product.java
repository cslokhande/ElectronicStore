package com.bikkadit.electronicstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_data")
public class Product extends BaseEntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String title;
    @Column(length = 1000)
    private String description;
    private Double price;
    private Double discountedPrice;
    private Integer quantity;
    @CreationTimestamp
    private Date addedDate;
    private boolean live;
    private boolean stock;

}

