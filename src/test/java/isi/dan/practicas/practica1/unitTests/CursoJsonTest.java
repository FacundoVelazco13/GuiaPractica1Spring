package isi.dan.practicas.practica1.unitTests;

import isi.dan.practicas.practica1.models.Alumno;
import isi.dan.practicas.practica1.models.Curso;
import isi.dan.practicas.practica1.models.Docente;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

import java.util.ArrayList;

@JsonTest
public class CursoJsonTest {
    @Autowired
    private JacksonTester<Curso> json;

    @Autowired
    private JacksonTester<Curso[]> jsonList;

    private Curso[] cursos;

    @BeforeEach
    void setUp() {
        cursos = Arrays.array(
                new Curso(90,"Curso90",10,10,null,null),
                new Curso(91,"Curso91",12,12,null,null),
                new Curso(92,"Curso92",13,13,null,null));
    }
    @Test
    void shouldDeserializeCurso() throws Exception {
        String expected = """
                {
                   "id": 1,
                   "nombre": "Jua12n",
                   "creditos": 9999,
                   "cupo": 9999,
                   "docenteAsignado":{"id": 1,"nombre": "Jua12n","salario": 9999.0,"cursosDictados": []},
                   "alumnosInscriptos": []
                 }
                """;
        ObjectContent<Curso> parsed = json.parse(expected);
        System.out.println(parsed);
        System.out.println(parsed.getObject());
        Docente docente = parsed.getObject().getDocenteAsignado();
        System.out.println(docente);

        Curso newCursoDocente = new Curso(null, "newCurso", 99, 99, docente,new ArrayList<>());

        JsonContent<Curso> cursoJson = json.write(newCursoDocente);
        System.out.println(cursoJson);

        Curso newCurso = new Curso(null, "newCurso", 99, 99, new Docente(null,null,null,new ArrayList<>()), new ArrayList<>());
        System.out.println(json.write(newCurso));

        AssertionsForClassTypes.assertThat(json.parseObject(expected).getId()).isEqualTo(1);
    }
}
