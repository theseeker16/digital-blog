package com.digitalblog.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Permiso entity.
 */
public class PermisoDTO implements Serializable {

    private Long id;

    private String descripcion;

    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PermisoDTO permisoDTO = (PermisoDTO) o;
        if(permisoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), permisoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PermisoDTO{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
