package com.bokchitable.dto;

import com.bokchitable.domain.Restaurant;

public record RestaurantDetailDto(
        Long id,
        String name,
        String cuisine,
        String priceRange,
        Double rating,
        String thumbnailUrl,
        Boolean isBookable
        // TODO: 주소/영업시간/메뉴/리뷰 등 확장 필드
) {
    public static RestaurantDetailDto from(Restaurant r) {
        return new RestaurantDetailDto(
                r.getId(), r.getName(), r.getCuisine(), r.getPriceRange(),
                r.getRating(), r.getThumbnailUrl(), r.getBookable()
        );
    }
}