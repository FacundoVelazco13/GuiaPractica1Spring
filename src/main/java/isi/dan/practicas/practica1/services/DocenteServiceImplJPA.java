package isi.dan.practicas.practica1.services;

import isi.dan.practicas.practica1.models.Docente;
import isi.dan.practicas.practica1.repositories.DocenteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DocenteServiceImplJPA implements DocenteService{
    @Autowired
    DocenteJpaRepository docenteJpaRepository;
    @Override
    public Docente saveDocente(Docente docente) {
        Docente realDocente;
        if(docente.getId()!=null){
            realDocente = docenteJpaRepository.findById(docente.getId()).get();
            if(docente.getNombre()!=null) realDocente.setNombre(docente.getNombre());
            if(docente.getSalario()!=null) realDocente.setSalario(docente.getSalario());
            //añadir logica de lista de cursos aca.
        }else realDocente = docente;
        return docenteJpaRepository.save(realDocente);
    }

    @Override
    public Optional<Docente> findDocenteById(Integer id) {
        return docenteJpaRepository.findById(id);
    }

    @Override
    public List<Docente> findAllDocentes() {
        return docenteJpaRepository.findAll();
    }

    @Override
    public void deleteDocenteById(Integer id) {
        docenteJpaRepository.deleteById(id);
    }
}
