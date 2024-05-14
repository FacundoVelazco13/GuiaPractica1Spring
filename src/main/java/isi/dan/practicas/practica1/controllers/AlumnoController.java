package isi.dan.practicas.practica1.controllers;

import isi.dan.practicas.practica1.models.Alumno;
import isi.dan.practicas.practica1.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Integer id) {
        Optional<Alumno> alumno = alumnoService.findAlumnoById(id);
        if(alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        } else return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        return ResponseEntity.ok(alumnoService.findAllAlumnos());
    }
    @PostMapping
    public ResponseEntity<Void> saveAlumno(@RequestBody Alumno alumno, UriComponentsBuilder ucb) {
        if (alumno.getId() != null) return ResponseEntity.badRequest().build();
        Alumno alumnoCreated = alumnoService.saveAlumno(alumno);
        URI locationOfNewAlumno = ucb.path("/alumno/{id}").buildAndExpand(alumnoCreated.getId()).toUri();
        return ResponseEntity.created(locationOfNewAlumno).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateAlumnoById(@PathVariable Integer id, @RequestBody Alumno alumno) {
        Optional<Alumno> alumnoOptional = alumnoService.findAlumnoById(id);
        if(alumnoOptional.isPresent()) {
            alumno.setId(id);
            alumnoService.saveAlumno(alumno);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAlumnoById(@PathVariable Integer id) {
        alumnoService.deleteAlumnoById(id);
        return ResponseEntity.noContent().build();
    }
}