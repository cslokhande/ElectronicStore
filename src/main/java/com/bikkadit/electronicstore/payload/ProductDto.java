package com.bikkadit.electronicstore.payload;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto extends BaseEntityDto {

    private Integer productId;
    private String title;
    private String descrition;
    private Integer price;
    private Integer discountedPrice;
    private Integer quantity;
    private Date addedDate;
    private boolean live;

}
