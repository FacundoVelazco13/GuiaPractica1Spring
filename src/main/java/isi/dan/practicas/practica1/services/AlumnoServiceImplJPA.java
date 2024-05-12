package isi.dan.practicas.practica1.services;

import isi.dan.practicas.practica1.models.Alumno;
import isi.dan.practicas.practica1.repositories.AlumnoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImplJPA implements AlumnoService{
    @Autowired
    AlumnoJpaRepository alumnoJpaRepository;

    @Override
    public Alumno saveAlumno(Alumno alumno) {
        Alumno realAlumno;
        if(alumno.getId() != null) {
            realAlumno = alumnoJpaRepository.findById(alumno.getId()).get();
            if (alumno.getNombre() != null) realAlumno.setNombre(alumno.getNombre());
            if (alumno.getLegajo() != null) realAlumno.setLegajo(alumno.getLegajo());
            //añadir logina de lista de cursos aca.
        }else realAlumno = alumno;

        return alumnoJpaRepository.save(realAlumno);
    }

    @Override
    public Optional<Alumno> findAlumnoById(Integer id) {
        return alumnoJpaRepository.findById(id);
    }

    @Override
    public List<Alumno> findAllAlumnos() {
        return alumnoJpaRepository.findAll();
    }

    @Override
    public void deleteAlumnoById(Integer id) {
        alumnoJpaRepository.deleteById(id);
    }
}
