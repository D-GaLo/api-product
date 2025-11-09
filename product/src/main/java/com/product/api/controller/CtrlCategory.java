package com.product.api.controller;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.commons.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

/**
 * Controlador REST para manejar las operaciones CRUD de categorías.
 */
@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Módulo de categorías")
public class CtrlCategory {
	
	@Autowired
	SvcCategory svc;

	@GetMapping
	@Operation(summary = "Consultar categorías", description = "Consulta las categorías registradas")
	public ResponseEntity<List<Category>> findAll(){
		return ResponseEntity.ok(svc.findAll());
    }

    @GetMapping("/active")
	@Operation(summary = "Consultar categorías activas", description = "Consulta las categorías activas registradas")
	public ResponseEntity<List<Category>> findActive(){
		return ResponseEntity.ok(svc.findActive());
	}

    @PostMapping
	@Operation(summary = "Registrar categoría", description = "Registro de una categoría")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody DtoCategoryIn in) {
		return ResponseEntity.ok(svc.create(in));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar categoría", description = "Actualización de una categoría")
	public ResponseEntity<ApiResponse> update(@Valid @RequestBody DtoCategoryIn in, @PathVariable("id") Integer id){
		return ResponseEntity.ok(svc.update(in, id));
	}

	@PatchMapping("/{id}/enable")
	@Operation(summary = "Activar categoría", description = "Activación de una categoría")
	public ResponseEntity<ApiResponse> enable(@PathVariable Integer id) {
		return ResponseEntity.ok(svc.enable(id));
	}

	@PatchMapping("/{id}/disable")
	@Operation(summary = "Desactivar categoría", description = "Desactivación de una categoría")
	public ResponseEntity<ApiResponse> disable(@PathVariable Integer id) {
		return ResponseEntity.ok(svc.disable(id));
	}
} 