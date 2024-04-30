package isi.dan.practicas.practica1.repositories;

import isi.dan.practicas.practica1.models.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteJpaRepository extends JpaRepository<Docente, Integer> {
}
