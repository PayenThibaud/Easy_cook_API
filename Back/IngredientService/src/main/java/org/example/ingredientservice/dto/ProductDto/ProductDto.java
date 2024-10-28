package org.example.ingredientservice.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {
    private String productName;
    private int calories;
    private String allergens;
    private String barcode;
}