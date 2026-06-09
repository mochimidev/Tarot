package com.example.evaluacion4charlottegabriel.Dao;

import java.io.Serializable;

/**
 * Clase modelo que representa una carta del tarot.
 * Implementa Serializable para poder pasarla entre activities.
 * 
 * Contiene:
 * - Título: Nombre de la carta (ej: "El Loco", "El Mago")
 * - Descripción: Significado de la carta en su forma derecha
 * - Descripción Invertida: Significado cuando sale al revés
 * - Descripción Amorosa: Interpretación en contextos de amor/parejas
 */
public class Carta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String titulo;
    private String descripcion;
    private String descripcionInvertida;
    private String descripcionAmorosa;

    /**
     * Constructor vacío requerido por Firebase
     */
    public Carta() {
    }

    /**
     * Constructor con título y descripción básica
     */
    public Carta(String titulo, String descripcion) {
        this.descripcion = descripcion;
        this.titulo = titulo;
    }

    /**
     * Constructor completo con todas las descripciones
     */
    public Carta(String titulo, String descripcion, 
                 String descripcionInvertida, String descripcionAmorosa) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.descripcionInvertida = descripcionInvertida;
        this.descripcionAmorosa = descripcionAmorosa;
    }

    // Getters y Setters
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion != null ? descripcion : "";
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionInvertida() {
        return descripcionInvertida != null ? descripcionInvertida : "";
    }

    public void setDescripcionInvertida(String descripcionInvertida) {
        this.descripcionInvertida = descripcionInvertida;
    }

    public String getDescripcionAmorosa() {
        return descripcionAmorosa != null ? descripcionAmorosa : "";
    }

    public void setDescripcionAmorosa(String descripcionAmorosa) {
        this.descripcionAmorosa = descripcionAmorosa;
    }

    /**
     * Método toString para debugging
     */
    @Override
    public String toString() {
        return "Carta{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", descripcionInvertida='" + descripcionInvertida + '\'' +
                ", descripcionAmorosa='" + descripcionAmorosa + '\'' +
                '}';
    }
}
