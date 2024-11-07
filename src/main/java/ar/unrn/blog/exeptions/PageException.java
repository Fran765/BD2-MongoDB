package ar.unrn.blog.exeptions;

public class PageException extends RuntimeException{

    public PageException(String message) {
        super(message);
    }

    public PageException(String msj, Throwable cause) {
        super(msj, cause);
    }
}
