package com.bokchitable.dto;

import com.bokchitable.domain.Restaurant;

public record RestaurantSummaryDto(
        Long id,
        String name,
        String cuisine,
        String priceRange,
        Double rating,
        String thumbnailUrl,
        Boolean isBookable
) {
    public static RestaurantSummaryDto from(Restaurant r) {
        return new RestaurantSummaryDto(
                r.getId(), r.getName(), r.getCuisine(), r.getPriceRange(),
                r.getRating(), r.getThumbnailUrl(), r.getBookable()
        );
    }
}