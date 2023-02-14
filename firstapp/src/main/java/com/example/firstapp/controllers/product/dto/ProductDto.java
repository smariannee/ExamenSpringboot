package com.example.firstapp.controllers.product.dto;

import com.example.firstapp.models.product.Product;
import com.example.firstapp.models.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String title;
    private String category;
    private double price;
    private boolean availability;
    private Transaction transaction;
    public Product castToProduct() {
        return new Product(
          getId(),
          getTitle(),
          getCategory(),
          getPrice(),
          null,
          null
        );
    }
}
