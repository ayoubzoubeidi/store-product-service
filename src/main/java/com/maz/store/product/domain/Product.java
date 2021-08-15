package com.maz.store.product.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Document(collection = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    private UUID id;

    private String upc;

    private String label;

    private Category category;

    private BigDecimal cost;

    @CreatedDate
    private Date createDate;

    @LastModifiedDate
    private Date lastModifiedDate;


}
