// src/main/java/com/bokchitable/dto/request/estaurantUpsertRequest.java
package com.bokchitable.dto.request;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonAlias;

public record RestaurantUpsertRequest(
        @NotBlank @Size(max = 120) String name,
        @Size(max = 50) String cuisine,
        @Size(max = 8) String priceRange,
        @DecimalMin("0.0") @DecimalMax("5.0") Double rating,
        @Size(max = 255) String thumbnailUrl,
        @JsonAlias({"bookable", "isBookable"})
        @NotNull Boolean isBookable
) {}