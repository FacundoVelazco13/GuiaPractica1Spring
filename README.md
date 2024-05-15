# Guia Practica #1 Spring

### Problemas actuales
    Creo que tengo prolemas en la arquitectura, 
    las relaciones bidireccionales generan conflictos 
    a la hora de serializar y deserializar a Json.
###
    Me costo bastante encontrar las configuraciones de serializacion y deserializacion de Json para evitar
    los problemas de stackoverflow que se generan al serializar entidades con relaciones bidireccionales.
    Al final encontre que se puede usar la anotacion @JsonManagedReference y @JsonBackReference para evitar estos problemas.
    Tambien encontre que se puede usar @JsonIgnoreProperties para ignorar propiedades en la serializacion.
    Aun asi, no se creo que sea la mejor forma de hacerlo.
###
    Si permito inserciones de las 3 entidades y sus respectivas relacion
    (Ej. insetar un alumno con lista de cursos, insertar un curso con docente y alumnos, insertar un docente con lista de cursos)
    tengo bastante acoplamiento en las clases de servicio para validar todo eso y en las clases controladoras para validar existencia de los datos recibidos,
    por lo que no creo estar trabajando correctamente, quiero seguir avanzando o ver algun ejemplo del profesor.
### Avances
    
### Faltantes
    Agregar restricciones de cantidad de cursoso por docente al servicio de doncente.
###
    Restricciones de alumno en general.