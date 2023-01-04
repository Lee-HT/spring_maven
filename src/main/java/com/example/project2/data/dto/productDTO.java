package com.example.project2.data.dto;

import com.example.project2.data.entity.productEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class productDTO {

    private String productID;
    private String productName;
    private int productPrice;
    private int productStock;

    public productEntity toEntity(){
        return productEntity.builder()
                .productID(productID)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }

}
