package isi.dan.practicas.practica1.services;

import isi.dan.practicas.practica1.exceptions.DocenteExcedidoException;
import isi.dan.practicas.practica1.models.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    public Curso saveCurso(Curso curso) throws DocenteExcedidoException;
    public Optional<Curso> findCursoById(Integer id);
    public List<Curso> findAllCursos();
    public void deleteCursoById(Integer id);
}
