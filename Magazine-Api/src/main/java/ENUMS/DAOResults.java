package ENUMS;

public enum DAOResults {

    EMAIL_IN_USE(-1, "EMAIL_IN_USE"),
    UNAUTHORIZED(-2, "UNAUTHORIZED"),
    ERROR_ON_INSERT(0, "ERROR_INSERT"),
    NO_ERROR(1, "NO_ERROR");

    private int code;
    private String message;

    private DAOResults(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
