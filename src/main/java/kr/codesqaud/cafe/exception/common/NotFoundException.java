package kr.codesqaud.cafe.exception.common;

public class NotFoundException extends RuntimeException {

    private String errorMessage;

    public NotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}