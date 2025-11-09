package com.product.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

/**
 * La clase Category representa una categoría con identificador, nombre, tag y estatus.
 * Esta entidad se mapea con la tabla 'category' de la base de datos usando JPA.
 */
@Entity
@Table(name = "category")
public class Category {

    /**
     * Identificador único de la categoría.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("category_id")
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * Nombre de la categoría. Debe ser único.
     */
    @JsonProperty("category")
    @Column(name = "category")
    private String category;

    /**
     * Tag o abreviatura de la categoría. Debe ser único.
     */
    @JsonProperty("tag")
    @Column(name = "tag")
    private String tag;

    /**
     * Estatus de la categoría (1 = activa, 0 = eliminada).
     */
    @JsonProperty("status")
    @Column(name = "status")
    private Integer status;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Category() {
    }

    /**
     * Constructor para crear una nueva categoría.
     * 
     * @param categoryId Identificador único.
     * @param category   Nombre de la categoría.
     * @param tag        Tag.
     * @param status     Estatus (1 = activa, 0 = eliminada).
     */
    public Category(Integer categoryId, String category, String tag, Integer status) {
        this.categoryId = categoryId;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }


    // Getters y Setters
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", category='" + category + '\'' +
                ", tag='" + tag + '\'' +
                ", status=" + status +
                '}';
    }
}