package MRV;

public class ErrorHandler {
    private static Id_errors error = Id_errors.NON_ERROR;
    private static int begin_error = -1;
    private static int end_error = -1;
    public static void setError( Id_errors error, Lexeme fail_lexeme) {
        ErrorHandler.error = error;
        ErrorHandler.begin_error = fail_lexeme.getBegin ();
        ErrorHandler.end_error = fail_lexeme.getEnd ();
    }
    public static void setError( Id_errors error, int begin, int end) {
        ErrorHandler.error = error;
        ErrorHandler.begin_error = begin;
        ErrorHandler.end_error = end;
    }
    public static Id_errors getError() {
        return error;
    }

    public static int get_begin_error() {
        return begin_error;
    }
    public static int get_end_error(){
        return end_error;
    }
}
