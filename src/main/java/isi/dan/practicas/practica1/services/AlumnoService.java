package isi.dan.practicas.practica1.services;

import isi.dan.practicas.practica1.models.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AlumnoService {
    public Alumno saveAlumno(Alumno alumno);
    public Optional<Alumno> findAlumnoById(Integer id);
    public List<Alumno> findAllAlumnos();
    public void deleteAlumnoById(Integer id);
}
