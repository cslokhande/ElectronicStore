package com.bikkadit.electronicstore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Category_Data")
public class Category extends BaseEntityClass {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long categoryId;

     private String title;

     private String description;

     private String coverImage;
}
