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
    Long id;
    String name;
    Integer price;
    Integer stock;

    /*
    @Column
    String sellerID;

    @Column
    String sellerPhoneNumber;
     */

    public productDTO toDto(){
        return productDTO.builder()
                .id(id)
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }

}
