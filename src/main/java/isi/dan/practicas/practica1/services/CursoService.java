package isi.dan.practicas.practica1.services;

import isi.dan.practicas.practica1.models.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    public Curso saveCurso(Curso curso);
    public Optional<Curso> findCursoById(Integer id);
    public List<Curso> findAllCursos();
    public void deleteCursoById(Integer id);
}
