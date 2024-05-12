package isi.dan.practicas.practica1.services;

import isi.dan.practicas.practica1.models.Docente;

import java.util.List;
import java.util.Optional;

public interface DocenteService {
    public Docente saveDocente(Docente docente);
    public Optional<Docente> findDocenteById(Integer id);
    public List<Docente> findAllDocentes();
    public void deleteDocenteById(Integer id);
}
