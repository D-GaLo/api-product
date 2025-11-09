package com.product.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
       @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "product_image_id")
   private Integer productImageId;


   @Column(name = "product_id")
   private Integer productId;


   @Column(name = "image")
   private String image;


   @Column(name = "status")
   private Integer status;


   
   public Integer getProductImageId() {
    return productImageId;
}

public Integer getProductId() {
    return productId;
}

public String getImage() {
    return image;
}

public Integer getStatus() {
    return status;
}


public void setProductImageId(Integer productImageId) {
    this.productImageId = productImageId;
}

public void setProductId(Integer productId) {
    this.productId = productId;
}

public void setImage(String image) {
    this.image = image;
}

public void setStatus(Integer status) {
    this.status = status;
}

}
