package isi.dan.practicas.practica1.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private Double salario;
    @JsonManagedReference(value = "docente-curso")
    @OneToMany(mappedBy = "docenteAsignado")
    private List<Curso> cursosDictados;
    public Docente() {
    }
    public Docente(Integer id, String nombre, Double salario, List<Curso> cursosDictados) {
        this.id = id;
        this.nombre = nombre;
        this.salario = salario;
        this.cursosDictados = cursosDictados;
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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public List<Curso> getCursosDictados() {
        return cursosDictados;
    }

    public void setCursosDictados(List<Curso> cursosDictados) {
        this.cursosDictados = cursosDictados;
    }
    public void addCursosDictados(Curso curso) {
        this.cursosDictados.add(curso);
    }
}
