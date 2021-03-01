package com.mercedes.fpm.reponse;

import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private Double productPrice;
    private String productCurrency;
    private Double priceChange;
    private String priceChangeSign;
}
