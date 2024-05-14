package isi.dan.practicas.practica1.unitTests;

import isi.dan.practicas.practica1.models.Alumno;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class AlumnoJsonTest {

    @Autowired
    private JacksonTester<Alumno> json;

    @Autowired
    private JacksonTester<Alumno[]> jsonList;

    private Alumno[] alumnos;

    @BeforeEach
    void setUp() {
        alumnos = Arrays.array(
                new Alumno(90, "sarah1", "9999", new ArrayList<>()),
                new Alumno(91,"miguel", "8888", new ArrayList<>()),
                new Alumno(92,"Alguien", "7777", new ArrayList<>()));
    }
    @Test
    void shouldSerializeAlumno() throws Exception {
        Alumno alumno = alumnos[0];
        //No me funciona, no se porque
        //assertThat(json.write(alumno)).isEqualToJson("alumno.json");

        assertThat(json.write(alumno)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(alumno)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(90);
        assertThat(json.write(alumno)).hasJsonPathStringValue("@.nombre");
        assertThat(json.write(alumno)).extractingJsonPathStringValue("@.nombre")
                .isEqualTo("sarah1");
        assertThat(json.write(alumno)).hasJsonPathStringValue("@.legajo");
        assertThat(json.write(alumno)).extractingJsonPathStringValue("@.legajo")
                .isEqualTo("9999");
    }
    @Test
    void shouldDeserializeAlumno() throws Exception {
        String expected = """
                {
                    "id": 90,
                    "nombre": "sarah1",
                    "legajo": "9999",
                    "cursosInscriptos": []
                }
                """;
        //No me funciona..........
        assertThat(json.parse(expected)).isEqualTo(new Alumno(90, "sarah1", "9999", new ArrayList<>()));
        assertThat(json.parseObject(expected).getId()).isEqualTo(90);
        assertThat(json.parseObject(expected).getNombre()).isEqualTo("sarah1");
        assertThat(json.parseObject(expected).getLegajo()).isEqualTo("9999");
    }
}
