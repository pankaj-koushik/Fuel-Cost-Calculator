package com.mercedes.fpm.web.model;

import lombok.Data;

/**
 * Product> Petrol or Diesel
 */
@Data
public class Product {
        private String productName;
        private Double productPrice;
        private String productCurrency;
        private Double priceChange;
        private String priceChangeSign;
}
