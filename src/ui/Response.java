package ui;

public class Response {

    public static final String NOT_VALID_VALUE = "El valor introducido es invalido.";
    public static final String NOT_VALID_OPTION = "La opcion introducida es invalida.";
    public static final String MAX_CAPACITY = "Maxima capacidad alcanzada.";
    public static final String PLAYER_NOT_FOUND = "Jugador no encontrado.";
    public static final String ALREADY_EXIST = "El nickname ya se encuentra registrado.";
    public static final String MUST_BE_POSITIVE = "El valor debe ser positivo.";
    
    private String message;
    private boolean isError;
    private Object data;

    public Response(){
        
    }

    public void setResponse(boolean isError, String message, Object data){
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
