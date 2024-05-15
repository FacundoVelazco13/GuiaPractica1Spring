package isi.dan.practicas.practica1.controllers;

import isi.dan.practicas.practica1.exceptions.DocenteExcedidoException;
import isi.dan.practicas.practica1.models.Curso;
import isi.dan.practicas.practica1.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        Optional<Curso> cursoOptional = cursoService.findCursoById(id);
        if (cursoOptional.isPresent()) return ResponseEntity.ok(cursoOptional.get());
        else return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        return ResponseEntity.ok(cursoService.findAllCursos());
    }
    @PostMapping
    public ResponseEntity<Void> saveCurso(@RequestBody Curso curso, UriComponentsBuilder ucb) {
        if (curso.getId() != null) return ResponseEntity.badRequest().build();
        try {
            cursoService.saveCurso(curso);
        } catch (DocenteExcedidoException e) {
            return ResponseEntity.badRequest().build();
        }
        URI locationOfNewCurso = ucb.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(locationOfNewCurso).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCurso(@PathVariable Integer id, @RequestBody Curso curso) {
        if (cursoService.findCursoById(id).isEmpty()) return ResponseEntity.notFound().build();
        curso.setId(id);
        try {
            cursoService.saveCurso(curso);
        } catch (DocenteExcedidoException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        cursoService.deleteCursoById(id);
        return ResponseEntity.noContent().build();
    }
}
