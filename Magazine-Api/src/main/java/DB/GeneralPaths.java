package DB;

public enum GeneralPaths {

    // VARIABLES
    EMAIL_IN_USE(-1, "EMAIL_IN_USE"),
    UNAUTHORIZED(-2, "UNAUTHORIZED"),
    ERROR_ON_INSERT(0, "ERROR_INSERT"),
    NO_ERROR(1, "NO_ERROR"),
    FILES_PDF_PATH(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/Posts/"),
    FILES_IMG_PATH_AD(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/img/ad/"),
    FILES_IMG_PATH_PROF_EDITOR(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/img/profile/editor/"),
    FILES_IMG_PATH_PROF_READER(-3, "/home/jefemayoneso/Desktop/FIlesMagPage/img/profile/reader/"),
    JASPER_EDITOR_PATH(-3, "/home/jefemayoneso/Documents/Angular/projects/magazine-app/Magazine-Api/src/main/resources/JasperReports/editorReports/"),
    JASPER_EDITOR_SUB_PATH(-3,"/home/jefemayoneso/Documents/Angular/projects/magazine-app/Magazine-Api/src/main/resources/JasperReports/editorReports/subReport/");
    // METHODS
    private int code;
    private String message;

    private GeneralPaths(int code, String message) {
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
