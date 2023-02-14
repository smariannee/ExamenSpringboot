package com.example.firstapp.models.transaction;

import com.example.firstapp.models.product.Product;
import com.example.firstapp.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;
    private Date date;
}
