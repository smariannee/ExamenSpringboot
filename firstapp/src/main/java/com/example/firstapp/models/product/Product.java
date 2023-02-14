package com.example.firstapp.models.product;

import com.example.firstapp.models.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private Boolean availability;
    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false, referencedColumnName = "id")
    private Transaction transaction;
}
