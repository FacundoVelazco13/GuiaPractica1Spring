package isi.dan.practicas.practica1.unitTests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
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
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocenteApplicationTest {
    @Autowired
    TestRestTemplate restTemplate;
    @Test
    void shouldReturnExistingDocenteById() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/docente/100", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(100);
    }
    @Test
    void shouldNotReturnNotExistingDocenteById() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/docente/999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    void shouldReturnAllDocentes() {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/docente", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        List<JsonPath> docentes = documentContext.read("$..id");
        assertThat(docentes).asList().isNotEmpty();
    }
    @Test
    void shouldCreateDocente(){
        Docente newDocente = new Docente(null, "newDocente", 9999.9, null);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("/docente",newDocente, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewDocente = response.getHeaders().getLocation();

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(locationOfNewDocente, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        Double salario = documentContext.read("$.salario");
        assertThat(id).isNotNull();
        assertThat(nombre).isEqualTo("newDocente");
        assertThat(salario).isEqualTo(9999.9);
    }
    @Test
    void shouldNotCreateDocenteWithId(){
        Docente newDocente = new Docente(999, "newDocente", 9999.9, null);
        ResponseEntity<Void> response = restTemplate
                .postForEntity("/docente",newDocente, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    @DirtiesContext
    void shouldUpdateDocente(){
        Docente updateDocente = new Docente(null, "DocenteActualizado", 10000.0, null);
        HttpEntity<Docente> request = new HttpEntity<>(updateDocente);
        ResponseEntity<Void> response = restTemplate
                .exchange("/docente/101", HttpMethod.PUT,request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity("/docente/101", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        String nombre = documentContext.read("$.nombre");
        Double salario = documentContext.read("$.salario");

        assertThat(nombre).isEqualTo("DocenteActualizado");
        assertThat(salario).isEqualTo(10000.0);
    }
    @Test
    @DirtiesContext
    void shouldDeleteDocente(){
        ResponseEntity<Void> response = restTemplate
                .exchange("/docente/101", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity("/docente/101", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
