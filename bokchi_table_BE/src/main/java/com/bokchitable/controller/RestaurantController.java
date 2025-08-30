package com.bokchitable.controller;

import com.bokchitable.dto.*;
import com.bokchitable.dto.request.RestaurantUpsertRequest;
import com.bokchitable.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    // READ - 목록
    @GetMapping
    public ResponseEntity<Page<RestaurantSummaryDto>> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) String priceRange,
            @RequestParam(required = false) Double minRating,
            @ParameterObject Pageable pageable
    ) {
        return ResponseEntity.ok(service.list(q, cuisine, priceRange, minRating, pageable));
    }

    // READ - 단건
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<RestaurantDetailDto> create(@Valid @RequestBody RestaurantUpsertRequest req) {
        var created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/restaurants/" + created.id()))
                .body(created);
    }

    // UPDATE (전체 교체)
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDetailDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody RestaurantUpsertRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}