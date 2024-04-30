package isi.dan.practicas.practica1.exceptions;

public class CupoExcedidoException extends Exception {
    public CupoExcedidoException() {
        super("El curso alcanzo el numero maximo de inscriptos");
    }
}
