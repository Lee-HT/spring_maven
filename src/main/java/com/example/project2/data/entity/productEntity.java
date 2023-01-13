package com.example.project2.data.entity;


import com.example.project2.data.dto.productDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "product")
public class productEntity extends BaseEntity{

    @Id
    Long ProductID;
    String ProductName;
    Integer ProductPrice;
    Integer ProductStock;

    /*
    @Column
    String sellerID;

    @Column
    String sellerPhoneNumber;
     */

    public productDTO toDto(){
        return productDTO.builder()
                .ProductID(ProductID)
                .ProductName(ProductName)
                .ProductPrice(ProductPrice)
                .ProductStock(ProductStock)
                .build();
    }

}
