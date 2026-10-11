package com.rica.ricaapi.publicaciones;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "publicaciones")
public class Publicacion {

    @Id
    private String id;

    private String investigadorCorreo;
    private String titulo;
    private String tipo;
    private Integer anio;
    private Map<String, String> detalles;

    public Publicacion() {
    }

    public Publicacion(String investigadorCorreo, String titulo, String tipo,
                       Integer anio, Map<String, String> detalles) {
        this.investigadorCorreo = investigadorCorreo;
        this.titulo = titulo;
        this.tipo = tipo;
        this.anio = anio;
        this.detalles = detalles;
    }

    // Getter y Setter de id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter y Setter de investigadorCorreo
    public String getInvestigadorCorreo() {
        return investigadorCorreo;
    }

    public void setInvestigadorCorreo(String investigadorCorreo) {
        this.investigadorCorreo = investigadorCorreo;
    }

    // Getter y Setter de titulo
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter y Setter de tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter y Setter de anio
    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    // Getter y Setter de detalles
    public Map<String, String> getDetalles() {
        return detalles;
    }

    public void setDetalles(Map<String, String> detalles) {
        this.detalles = detalles;
    }
}