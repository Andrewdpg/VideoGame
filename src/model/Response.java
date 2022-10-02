package model;

/**
 * Provides a better way to control responses from game to menu allowing to
 * send more than just a simple object.
 */
public class Response {

    // Some statical messages.
    public static final String NOT_VALID_VALUE = "El valor introducido es invalido.";
    public static final String NOT_VALID_OPTION = "La opcion introducida es invalida.";
    public static final String NOT_VALID_ENTITY = "La entidad es invalida.";
    public static final String MAX_CAPACITY = "Maxima capacidad alcanzada.";
    public static final String PLAYER_NOT_FOUND = "Jugador no encontrado.";
    public static final String ENTITY_NOT_FOUND = "Entidad no encontrada.";
    public static final String ALREADY_EXIST = "El nickname ya se encuentra registrado.";
    public static final String MUST_BE_POSITIVE = "El valor debe ser positivo.";

    private String message;
    private boolean isError;
    private Object data;

    public Response() {

    }

    public Response(boolean isError, String message, Object data) {
        this.message = message;
        this.isError = isError;
        this.data = data;
    }

    public void setResponse(boolean isError, String message, Object data) {
        this.isError = isError;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
