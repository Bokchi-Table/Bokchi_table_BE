package com.bokchitable.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurants")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Restaurant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String name;

    @Column(length=50)
    private String cuisine;

    @Column(name="price_range", length=8)
    private String priceRange;

    private Double rating;

    @Column(name="thumbnail_url", length=255)
    private String thumbnailUrl;

    @Column(name="bookable")
    private Boolean bookable;
}   
