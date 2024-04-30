package isi.dan.practicas.practica1.repositories;

import isi.dan.practicas.practica1.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoJpaRepository extends JpaRepository<Curso, Integer> {
}
