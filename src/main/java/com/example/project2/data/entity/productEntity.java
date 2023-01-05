package com.example.project2.data.entity;


import com.example.project2.data.dto.productDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class productEntity {

    @Id
    String productID;
    String productName;
    Integer productPrice;
    Integer productStock;

    /*
    @Column
    String sellerID;

    @Column
    String sellerPhoneNumber;
     */

    public productDTO toDto(){
        return productDTO.builder()
                .productID(productID)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }

}
