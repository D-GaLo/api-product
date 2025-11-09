package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.ProductImage;
import com.product.commons.dto.ApiResponse;

public interface SvcProductImage {
	public ApiResponse upload(Integer productId, DtoProductImageIn in);
	
	public List<ProductImage> getImages(Integer productId);
	
	public ApiResponse deleteImage(Integer productId, Integer productImageId);
}