package isi.dan.practicas.practica1.controllers;

import isi.dan.practicas.practica1.repositories.AlumnoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    AlumnoJpaRepository alumnoJpaRepository;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> getAlumnoById(@PathVariable Integer id) {
        return ResponseEntity.ok(alumnoJpaRepository.findById(id));
    }

}
