package isi.dan.practicas.practica1.unitTests;

import isi.dan.practicas.practica1.models.Alumno;
import isi.dan.practicas.practica1.models.Docente;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class DocenteJsonTest {
    @Autowired
    private JacksonTester<Docente> json;

    @Test
    void shouldDeserializeDocente() throws Exception {
        String expectedWithoutCurso = """
                {
                   "id": 1,
                   "nombre": "Jua12n",
                   "salario": 9999.0,
                   "cursosDictados": []
                 }
                """;
        String expectedWithAnyCurso = """
                {
                   "id": 1,
                   "nombre": "Jua12n",
                   "salario": 9999.0,
                   "cursosDictados": [{"id": 1,"nombre": "Jua12n","creditos": 9999,"cupo": 9999,"alumnosInscriptos": []}]
                 }
                """;

        System.out.println(json.parse(expectedWithoutCurso));
        System.out.println(json.parse(expectedWithAnyCurso));

        AssertionsForClassTypes.assertThat(json.parseObject(expectedWithoutCurso).getId()).isEqualTo(1);
    }
}
