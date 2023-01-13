package com.example.project2.data.dto;

import com.example.project2.data.entity.productEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class productDTO {

//    @Size(min=0,max=10)
    @NotNull
    private Long ProductID;
    @NotNull
    private String ProductName;
    @NotNull
    @Min(value = 500)
    @Max(value = 3000000)
    private int ProductPrice;
    @NotNull
    @Min(value = 0)
    @Max(value = 10000)
    private int ProductStock;

    public productEntity toEntity(){
        return productEntity.builder()
                .ProductID(ProductID)
                .ProductName(ProductName)
                .ProductPrice(ProductPrice)
                .ProductStock(ProductStock)
                .build();
    }

}
