package ar.unrn.blog.exeptions;

public class PostException extends RuntimeException{

    public PostException(String message) {
        super(message);
    }

    public PostException(String msj, Throwable cause) {
        super(msj, cause);
    }
}
