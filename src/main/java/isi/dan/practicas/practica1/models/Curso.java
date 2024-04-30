package isi.dan.practicas.practica1.models;

import isi.dan.practicas.practica1.exceptions.CupoExcedidoException;
import isi.dan.practicas.practica1.exceptions.DocenteExcedidoException;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nombre;
    private Integer creditos;
    private Integer cupo;
    @ManyToOne
    private Docente docenteAsignado;
    @ManyToMany
    @JoinTable(
            name = "curso_alumno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    private List<Alumno> alumnosInscriptos;

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

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Docente getDocenteAsignado() {
        return docenteAsignado;
    }

    public void setDocenteAsignado(Docente docenteAsignado) throws DocenteExcedidoException {
        if (docenteAsignado.getCursosDictados().size() >= 3) {
            throw new DocenteExcedidoException();
        }else this.docenteAsignado = docenteAsignado;
    }

    public List<Alumno> getAlumnosInscriptos() {
        return alumnosInscriptos;
    }

    public void setAlumnosInscriptos(List<Alumno> alumnosInscriptos) {
        this.alumnosInscriptos = alumnosInscriptos;
    }
    public void addAlumnosInscriptos(Alumno alumno) throws CupoExcedidoException {
        if (this.alumnosInscriptos.size() >= this.cupo) {
            throw new CupoExcedidoException();
        }else this.alumnosInscriptos.add(alumno);
    }
}