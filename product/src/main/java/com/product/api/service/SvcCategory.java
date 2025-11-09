package com.product.api.service;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.commons.dto.ApiResponse;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio para manejar la lógica de negocio de las categorías.
 */
public interface SvcCategory {
    public List<Category> findAll();
    public List<Category> findActive();
    public ApiResponse create(DtoCategoryIn in);
    public ApiResponse update(DtoCategoryIn in, Integer id);
    public ApiResponse enable(Integer id);
    public ApiResponse disable(Integer id);
}