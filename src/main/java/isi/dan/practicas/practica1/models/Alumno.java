package isi.dan.practicas.practica1.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private String legajo;
    @ManyToMany(mappedBy = "alumnosInscriptos")
    private List<Curso> cursosInscriptos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public List<Curso> getCursosInscriptos() {
        return cursosInscriptos;
    }

    public void setCursosInscriptos(List<Curso> cursosInscriptos) {
        this.cursosInscriptos = cursosInscriptos;
    }
}