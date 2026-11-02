package com.rica.consultasservice.consultas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "fichas_investigador")
public class FichaInvestigador {

    public static final String ACTIVO = "ACTIVO";
    public static final String EN_ELIMINACION = "EN_ELIMINACION";

    @Id
    private String correo;
    private String nombreCompleto;
    private String estado;
    private List<ResumenPublicacion> publicaciones;
    private int totalPublicaciones;

    protected FichaInvestigador() {
    }

    public FichaInvestigador(String correo, String nombreCompleto, String estado,
                             List<ResumenPublicacion> publicaciones, int totalPublicaciones) {
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.estado = estado;
        this.publicaciones = publicaciones;
        this.totalPublicaciones = totalPublicaciones;
    }

    public static FichaInvestigador nueva(String correo) {
        return new FichaInvestigador(correo, null, ACTIVO, new ArrayList<>(), 0);
    }

    public void registrar(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void marcarEnEliminacion() {
        this.estado = EN_ELIMINACION;
    }

    public boolean agregarPublicacion(ResumenPublicacion nueva) {
        boolean yaExiste = publicaciones.stream().anyMatch(publicacion -> publicacion.id().equals(nueva.id()));
        if (yaExiste) {
            return false;
        }
        publicaciones.add(nueva);
        totalPublicaciones = publicaciones.size();
        return true;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEstado() {
        return estado;
    }

    public List<ResumenPublicacion> getPublicaciones() {
        return publicaciones;
    }

    public int getTotalPublicaciones() {
        return totalPublicaciones;
    }
}
