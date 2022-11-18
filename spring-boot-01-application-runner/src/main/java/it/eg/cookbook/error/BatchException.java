package it.eg.cookbook.error;

import lombok.Getter;


@Getter
public class BatchException extends RuntimeException {

    private final ResponseCode code;

    public BatchException(String message, ResponseCode code) {
        super(message);
        this.code = code;
    }

    public BatchException(Throwable throwable, ResponseCode code) {
        super(throwable);
        this.code = code;
    }

    public BatchException(Throwable cause) {
        this(cause, ResponseCode.SYSTEM_ERROR);
    }

    public BatchException(String cause) {
        this(cause, ResponseCode.BUSINESS_ERROR);
    }

    public BatchException(ResponseCode code) {
        this((String) null, code);
    }


}
