package com.example.crudwarehouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 90)
    private String name;

    @Column(name = "vendor_code", nullable = false, unique = true, updatable = false)
    private String vendorCode;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "price", nullable = false, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @CreationTimestamp
    @Column(name = "quantity_update_date", nullable = false)
    private LocalDateTime quantityUpdateDate;

    @CreationTimestamp
    @Column(name = "create_ts", nullable = false)
    @JsonProperty("create_ts")
    private LocalDateTime createTs;
}
