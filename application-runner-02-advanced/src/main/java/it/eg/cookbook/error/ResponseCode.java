package it.eg.cookbook.error;

public enum ResponseCode {

    PARAMETER_ERROR("Parametri non validi"),
    BUSINESS_ERROR("Errore generico"),
    SYSTEM_ERROR("Errore di sistema");

    private String message;

    public String getMessage() {
        return message;
    }

    ResponseCode(String message) {
        this.message = message;
    }

}
