package org.example.ingredientservice.dto.ProductDto;

import lombok.Data;

import java.util.List;

@Data
public class OpenFoodFactsResponse {
    private List<ProductDto> products; // Liste de produits
}
