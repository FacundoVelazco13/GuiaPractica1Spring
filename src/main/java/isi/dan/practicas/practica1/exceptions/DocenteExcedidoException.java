package isi.dan.practicas.practica1.exceptions;

public class DocenteExcedidoException extends Exception{
    public DocenteExcedidoException() {
        super("El docente alcanzo el numero maximo de cursos");
    }
}
