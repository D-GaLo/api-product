package com.product.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;
import com.product.commons.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Tag(name = "ProductImage", description = "Catálogo de Imágenes de Productos")
public class CtrlProductImage {
	
    @Autowired
    SvcProductImage svc;

    @PostMapping("/{id}/image")
    @Operation(summary = "Registrar imagen de producto", description = "Registra una nueva imagen para un producto")
    public ResponseEntity<ApiResponse> createProductImage(
    		@PathVariable("id") Integer productId, 
    		@Valid @RequestBody DtoProductImageIn in) {
        return ResponseEntity.ok(svc.upload(productId, in));
    }
    
    @GetMapping("/{id}/image")
    @Operation(summary = "Consultar imágenes de producto", description = "Consulta todas las imágenes de un producto registrado")
    public ResponseEntity<List<ProductImage>> getProductImages(
    		@PathVariable("id") Integer productId) {
    	return ResponseEntity.ok(svc.getImages(productId));
    }
    
    @DeleteMapping("/{id}/image/{product-image-id}")
    @Operation(summary = "Eliminar imagen de producto", description = "Elimina una imagen de un producto registrado")
    public ResponseEntity<ApiResponse> deleteProductImage(
    		@PathVariable("id") Integer productId, 
    		@PathVariable("product-image-id") Integer productImageId) {
    	return ResponseEntity.ok(svc.deleteImage(productId, productImageId));
    }

}