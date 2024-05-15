package isi.dan.practicas.practica1.services;

import isi.dan.practicas.practica1.exceptions.DocenteExcedidoException;
import isi.dan.practicas.practica1.models.Curso;
import isi.dan.practicas.practica1.repositories.CursoJpaRepository;
import isi.dan.practicas.practica1.repositories.DocenteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CursoServiceImplJPA implements CursoService {
    @Autowired
    CursoJpaRepository cursoJpaRepository;
    @Autowired
    DocenteJpaRepository docenteJpaRepository;
    @Override
    public Curso saveCurso(Curso curso) throws DocenteExcedidoException {
        if(curso.getDocenteAsignado()!=null ){
            if(docenteJpaRepository.findById(curso.getDocenteAsignado().getId()).get().getCursosDictados().size() >= 3) throw new DocenteExcedidoException();
        }
        Curso realCurso;
        if(curso.getId() != null){
            realCurso = cursoJpaRepository.findById(curso.getId()).get();
            if(curso.getNombre()!=null) realCurso.setNombre(curso.getNombre());
            if(curso.getCreditos()!=null) realCurso.setCreditos(curso.getCreditos());
            if(curso.getCupo()!=null) realCurso.setCupo(curso.getCupo());
            //Programar parte de docente y alumnos.
        }else realCurso = curso;
        return cursoJpaRepository.save(realCurso);
    }

    @Override
    public Optional<Curso> findCursoById(Integer id) {
        return cursoJpaRepository.findById(id);
    }

    @Override
    public List<Curso> findAllCursos() {
        return cursoJpaRepository.findAll();
    }

    @Override
    public void deleteCursoById(Integer id) {
        cursoJpaRepository.deleteById(id);
    }
}
