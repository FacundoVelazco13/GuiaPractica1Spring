package isi.dan.practicas.practica1.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private String legajo;
    @ManyToMany(mappedBy = "alumnosInscriptos")
    private List<Curso> cursosInscriptos;

    public Alumno() {
    }
    public Alumno(Integer id, String nombre, String legajo, List<Curso> cursosInscriptos) {
        this.id = id;
        this.nombre = nombre;
        this.legajo = legajo;
        this.cursosInscriptos = cursosInscriptos;
    }
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

    @Override
    public String toString() {
        return "{" +
                "\"id\"=" + id +
                ", \"nombre\"='" + nombre + '\'' +
                ", \"legajo\"='" + legajo + '\'' +
                ", \"cursosInscriptos\"=" + cursosInscriptos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(id, alumno.id) && Objects.equals(nombre, alumno.nombre) && Objects.equals(legajo, alumno.legajo) && Objects.equals(cursosInscriptos, alumno.cursosInscriptos);
    }
}