package com.bokchitable.service;

import com.bokchitable.domain.Restaurant;
import com.bokchitable.dto.*;
import com.bokchitable.dto.request.RestaurantUpsertRequest;
import com.bokchitable.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;

    public Page<RestaurantSummaryDto> list(String q, String cuisine, String priceRange, Double minRating,
                                           Pageable pageable) {
        String _q = q == null ? "" : q;
        String _c = cuisine == null ? "" : cuisine;
        String _p = priceRange == null ? "" : priceRange;
        double _min = minRating == null ? 0.0 : minRating;

        Page<Restaurant> page = repository
                .findByNameContainingIgnoreCaseAndCuisineContainingIgnoreCaseAndPriceRangeContainingIgnoreCaseAndRatingGreaterThanEqual(
                        _q, _c, _p, _min, pageable);

        return page.map(RestaurantSummaryDto::from);
    }

    public RestaurantDetailDto get(Long id) {
        Restaurant r = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        return RestaurantDetailDto.from(r);
    }

    public RestaurantDetailDto create(RestaurantUpsertRequest req) {
        Restaurant r = Restaurant.builder()
                .name(req.name())
                .cuisine(req.cuisine())
                .priceRange(req.priceRange())
                .rating(req.rating())
                .thumbnailUrl(req.thumbnailUrl())
                .bookable(req.isBookable())
                .build();
        repository.save(r);
        return RestaurantDetailDto.from(r);
    }

    public RestaurantDetailDto update(Long id, RestaurantUpsertRequest req) {
        Restaurant r = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
        r.setName(req.name());
        r.setCuisine(req.cuisine());
        r.setPriceRange(req.priceRange());
        r.setRating(req.rating());
        r.setThumbnailUrl(req.thumbnailUrl());
        r.setBookable(req.isBookable());
        repository.save(r);
        return RestaurantDetailDto.from(r);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Restaurant not found");
        }
        repository.deleteById(id);
    }
}