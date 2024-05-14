package isi.dan.practicas.practica1.unitTests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import isi.dan.practicas.practica1.models.Curso;
import isi.dan.practicas.practica1.models.Docente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CursoApplicationTest {
    @Autowired
    TestRestTemplate restTemplate;
    @Test
    void shouldReturnExistingCursoById() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/curso/100", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(100);
    }
    @Test
    void shouldNotReturnNotExistingCursoById() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/curso/999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    void shouldReturnAllCursos() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/curso", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        List<JsonPath> cursos = documentContext.read("$..id");
        assertThat(cursos).asList().isNotEmpty();
    }
    @Test
    void ShouldCreateCurso() {
        Curso newCurso = new Curso(null, "newCurso", 99, 99, null, null);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("/curso", newCurso, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewCurso = response.getHeaders().getLocation();
        ResponseEntity<String> responseGet = restTemplate
                .getForEntity(locationOfNewCurso, String.class);
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(responseGet.getBody());
        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("newCurso");
        Integer creditos = documentContext.read("$.creditos");
        assertThat(creditos).isEqualTo(99);
        Integer cupo = documentContext.read("$.cupo");
        assertThat(cupo).isEqualTo(99);
    }
    @Test
    void shouldNotCreateCursoWithId() {
        Curso newCurso = new Curso(1, "newCurso", 99, 99, null, null);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("/curso", newCurso, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    @DirtiesContext
    void shouldUpdateCurso() {
        Curso newCurso = new Curso(null, "CursoActualizado", 100, 100,new Docente(), new ArrayList<>());
        HttpEntity<Curso> request = new HttpEntity<>(newCurso);
        ResponseEntity<Void> response = restTemplate
                .exchange("/curso/101", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> responseGet = restTemplate
                .getForEntity("/curso/101", String.class);
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(responseGet.getBody());

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("CursoActualizado");
        Integer creditos = documentContext.read("$.creditos");
        assertThat(creditos).isEqualTo(100);
        Integer cupo = documentContext.read("$.cupo");
        assertThat(cupo).isEqualTo(100);
    }
    @Test
    void shouldNotUpdateNotExistingCurso() {
        Curso newCurso = new Curso(null, "CursoActualizado", 100, 100, new Docente(), new ArrayList<>());
        HttpEntity<Curso> request = new HttpEntity<>(newCurso);
        ResponseEntity<Void> response = restTemplate
                .exchange("/curso/999", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    @DirtiesContext
    void shouldDeleteCurso() {
        ResponseEntity<Void> response = restTemplate
                .exchange("/curso/101", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> responseGet = restTemplate
                .getForEntity("/curso/101", String.class);
        assertThat(responseGet.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
