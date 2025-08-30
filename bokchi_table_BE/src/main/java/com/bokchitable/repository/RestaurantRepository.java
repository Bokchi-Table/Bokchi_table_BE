package com.bokchitable.repository;

import com.bokchitable.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 간단 검색/필터 조합 (이름 LIKE, cuisine/priceRange/평점 조건)
    Page<Restaurant> findByNameContainingIgnoreCaseAndCuisineContainingIgnoreCaseAndPriceRangeContainingIgnoreCaseAndRatingGreaterThanEqual(
            String name, String cuisine, String priceRange, Double minRating, Pageable pageable
    );
}