public class ErrorHandler {
    private static Id_errors error = Id_errors.NON_ERROR;
    public static int test;

    public static void setError( Id_errors error ) {
        ErrorHandler.error = error;
    }
    public static Id_errors getError() {
        return error;
    }
}
