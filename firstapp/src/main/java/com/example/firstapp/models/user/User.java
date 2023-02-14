package com.example.firstapp.models.user;

import com.example.firstapp.models.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column()
    private String wishlist;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    private Transaction transaction;
}
