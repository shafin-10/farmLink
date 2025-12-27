package com.example.FarmLink.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name should be greater than 3")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "description must not be blank")
    @Size(min = 5, message = "description must be at least 5 characters long")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Price must not be null")
    private Double price;

    @Column(name = "stock")
    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @Column(name = "category")
    @NotBlank(message = "category must not be blank")
    private String category;

    @Column(name = "harvest_date")
    @NotNull(message = "Harvest date must not be null")
    private LocalDate harvestDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE, OUT_OF_STOCK, ARCHIVED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private Users farmer;
}
