package isi.dan.practicas.practica1.unitTests;

import isi.dan.practicas.practica1.models.Alumno;
import isi.dan.practicas.practica1.models.Curso;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.util.ArrayList;

@JsonTest
public class CursoJsonTest {
    @Autowired
    private JacksonTester<Curso> json;

    @Autowired
    private JacksonTester<Curso[]> jsonList;

    private Alumno[] alumnos;

    @BeforeEach
    void setUp() {
        alumnos = Arrays.array(
                new Alumno(90, "sarah1", "9999", new ArrayList<>()),
                new Alumno(91,"miguel", "8888", new ArrayList<>()),
                new Alumno(92,"Alguien", "7777", new ArrayList<>()));
    }
}
