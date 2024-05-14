package isi.dan.practicas.practica1.controllers;

import isi.dan.practicas.practica1.models.Docente;
import isi.dan.practicas.practica1.services.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/docente")
public class DocenteController {
    @Autowired
    DocenteService docenteService;
    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Integer id) {
        Optional<Docente> docenteOptional = docenteService.findDocenteById(id);
        if (docenteOptional.isPresent()){
            return ResponseEntity.ok(docenteOptional.get());
        }else return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<Docente>> getAllDocentes() {
        return ResponseEntity.ok(docenteService.findAllDocentes());
    }
    @PostMapping
    public ResponseEntity<Void> saveDocente(@RequestBody Docente docente, UriComponentsBuilder ucb) {
        if (docente.getId() != null) return ResponseEntity.badRequest().build();
        Docente docenteCreated = docenteService.saveDocente(docente);
        URI locationOfNewDocente = ucb.path("/docente/{id}")
                .buildAndExpand(docenteCreated.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewDocente).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDocente(@PathVariable Integer id, @RequestBody Docente docente) {
        if (docenteService.findDocenteById(id).isEmpty()) return ResponseEntity.notFound().build();
        docente.setId(id);
        docenteService.saveDocente(docente);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Integer id) {
        if (docenteService.findDocenteById(id).isEmpty()) return ResponseEntity.notFound().build();
        docenteService.deleteDocenteById(id);
        return ResponseEntity.noContent().build();
    }
}
