package com.bikkadit.electronicstore.payload;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto extends BaseEntityDto {

    private Long productId;
    private String title;
    private String description;
    private Double price;
    private Double discountedPrice;
    private Integer quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
}
