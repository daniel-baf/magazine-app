package DB;

public enum GeneralPaths {

    // VARIABLES
    EMAIL_IN_USE(-1, "EMAIL_IN_USE"),
    UNAUTHORIZED(-2, "UNAUTHORIZED"),
    ERROR_ON_INSERT(0, "ERROR_INSERT"),
    NO_ERROR(1, "NO_ERROR"),
    FILES_PDF_PATH(-3, "FilesMagPage/Posts/"),
    FILES_IMG_PATH_AD(-3, "FilesMagPage/img/ad/"),
    FILES_IMG_PATH_PROF_EDITOR(-3, "FilesMagPage/img/profile/editor/"),
    FILES_IMG_PATH_PROF_READER(-3, "FilesMagPage/img/profile/reader/"),
    JASPER_EDITOR_MAIN_PATH_RELATIVE(-3, "com/jefemayoneso/MagazineApi/reports/editorReports/"),
    JASPER_EDITOR_SUB_PATH_RELATIVE(-3, "com/jefemayoneso/MagazineApi/reports/editorReports/subReport/"),
    JASPER_ADMIN_MAIN_PATH_RELATIVE(-3, "com/jefemayoneso/MagazineApi/reports/adminReports/"),
    JASPER_ADMIN_SUB_PATH_RELATIVE(-3, "com/jefemayoneso/MagazineApi/reports/adminReports/subReport/");
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
