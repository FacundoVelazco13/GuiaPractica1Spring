package isi.dan.practicas.practica1.unitTests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import isi.dan.practicas.practica1.models.Alumno;
import isi.dan.practicas.practica1.models.Curso;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlumnoApplicationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldResturnExistingAlumnoById() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/alumno/100", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void shouldNotResturnNotExistingAlumnoById() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/alumno/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }
    @Test
    void shouldReturnAllAlumnos() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/alumno", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        List<JsonPath> alumnos = documentContext.read("$..id");
        AssertionsForClassTypes.assertThat(alumnos).asList().isNotEmpty();
    }
    @Test
    void shouldCreateAlumno() {
        Alumno newAlumno = new Alumno(null, "test", "9999/9", new ArrayList<>());
        ResponseEntity<Void> response = restTemplate
                .postForEntity("/alumno", newAlumno, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewAlumno = response.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(locationOfNewAlumno, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        String legajo = documentContext.read("$.legajo");

        assertThat(id).isNotNull();
        assertThat(nombre).isEqualTo("test");
        assertThat(legajo).isEqualTo("9999/9");
    }
    @Test
    void ShouldNotCreateAlumnoWithId() {
        Alumno newAlumno = new Alumno(9999, "test", "9999/9", null);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("/alumno", newAlumno, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    @DirtiesContext
    void shouldUpdateAlumno() {
        Alumno updateAlumno = new Alumno(null, "AlumnoActualizado", "1000/0", null);
        HttpEntity<Alumno> request = new HttpEntity<>(updateAlumno);
        ResponseEntity<Void> response = restTemplate
                .exchange("/alumno/101", HttpMethod.PUT,request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity("/alumno/101", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        String nombre = documentContext.read("$.nombre");
        String legajo = documentContext.read("$.legajo");
        List<Curso> cursos = documentContext.read("$.cursosInscriptos");
        assertThat(nombre).isEqualTo("AlumnoActualizado");
        assertThat(legajo).isEqualTo("1000/0");
        assertThat(cursos).isNotNull();
    }
    @Test
    @DirtiesContext
    void shouldDeleteAlumno() {
        ResponseEntity<Void> response = restTemplate
                .exchange("/alumno/101", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity("/alumno/101", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(getResponse.getBody()).isBlank();
    }
}
