package ENUMS;

public enum DAOResults {

    // VARIABLES
    EMAIL_IN_USE(-1, "EMAIL_IN_USE"),
    UNAUTHORIZED(-2, "UNAUTHORIZED"),
    ERROR_ON_INSERT(0, "ERROR_INSERT"),
    NO_ERROR(1, "NO_ERROR"),
    FILES_PDF_PATH(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/Posts/"),
    FILES_IMG_PATH_AD(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/img/ad/"),
    FILES_IMG_PATH_PROF_EDITOR(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/img/profile/editor/"),
    FILES_IMG_PATH_PROF_READER(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/img/profile/reader/");

    // METHODS
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
