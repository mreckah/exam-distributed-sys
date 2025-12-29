package com.oussama.inventoryservice.query.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
