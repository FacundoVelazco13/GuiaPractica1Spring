package isi.dan.practicas.practica1.exceptions;

public class RecursoNoEncontrado extends Exception{
    public RecursoNoEncontrado(Object entity, Integer id) {
        super("No existe el identificador "+id+" del modelo "+entity.getClass().getSimpleName());
    }
}
