package MRV;

import Lexemes.*;

import java.util.ArrayList;

public class MRV {
    public static double count_lexemes( String input, ArrayList<String> keys, ArrayList<Double> values ) throws MRV.ARGUMENT_LIST_MISMATCH, MRV.UNKNOWN_FUNCTION, MRV.ERROR_SIGNS, MRV.IMPOSSIBLE_COUNT, MRV.MISS_ARGUMENT_BINARY_OPERATOR, MRV.MISS_ARGUMENT_PRE_OPERATOR, MRV.MISS_ARGUMENT_POST_OPERATOR, MRV.HAVE_OPEN_BRACKETS, MRV.MORE_RIGHT_BRACKETS, MRV.BAD_ARGUMENTS, MRV.UNKNOWN_ERROR {
        if (keys.size() != values.size()) {
            throw new MRV.ARGUMENT_LIST_MISMATCH ();
        }
        new Archieve (keys);
        ErrorHandler.set_default ();
        Lexeme answer;
        Sentence sentence = new Sentence(input);
        if ( ErrorHandler.getError () == Id_errors.ERROR_SIGNS ) throw new MRV.ERROR_SIGNS ();
        else if ( ErrorHandler.getError () == Id_errors.UNKNOWN_FUNCTION ) throw new MRV.UNKNOWN_FUNCTION ();
        sentence.substitute(keys, values);
        answer = sentence.count();
        if (ErrorHandler.getError() == Id_errors.NON_ERROR)
        {
            return answer.get_value();
        }
        else if (ErrorHandler.getError() == Id_errors.UNKNOWN_FUNCTION)
        {
            throw new MRV.UNKNOWN_FUNCTION ();
        }
        else if (ErrorHandler.getError() == Id_errors.ERROR_SIGNS)
        {
            throw new MRV.ERROR_SIGNS ();
        }
        else if (ErrorHandler.getError() == Id_errors.IMPOSSIBLE_COUNT)
        {
            throw new MRV.IMPOSSIBLE_COUNT ();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_BINARY_OPERATOR)
        {
            throw new MRV.MISS_ARGUMENT_BINARY_OPERATOR ();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_PRE_OPERATOR)
        {
            throw new MRV.MISS_ARGUMENT_PRE_OPERATOR ();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_POST_OPERATOR)
        {
            throw new MRV.MISS_ARGUMENT_POST_OPERATOR ();
        }
        else if (ErrorHandler.getError() == Id_errors.HAVE_OPEN_BRACKETS)
        {
            throw new MRV.HAVE_OPEN_BRACKETS ();
        }
        else if (ErrorHandler.getError() == Id_errors.MORE_RIGHT_BRACKETS)
        {
            throw new MRV.MORE_RIGHT_BRACKETS ();
        }
        else if (ErrorHandler.getError() == Id_errors.BAD_ARGUMENTS)
        {
            throw new MRV.BAD_ARGUMENTS ();
        }
        else
        {
            throw new MRV.UNKNOWN_ERROR ();
        }

    }

    static class Lexemes_Exception extends Exception{
        private int error_begin = -1;
        private int error_end = -1;
        Lexemes_Exception(){
    
        }
            Lexemes_Exception( int begin, int end){
            error_begin = begin;
            error_end = end;
        }
        public int getError_begin() {
            return error_begin;
        }
    
        public int getError_end() {
            return error_end;
        }
    }

    public static class ARGUMENT_LIST_MISMATCH extends MRV.Lexemes_Exception {
        public ARGUMENT_LIST_MISMATCH(){
        }
    }

    public static class BAD_ARGUMENTS extends MRV.Lexemes_Exception {
        public BAD_ARGUMENTS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class ERROR_SIGNS extends MRV.Lexemes_Exception {
        public ERROR_SIGNS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class HAVE_OPEN_BRACKETS extends MRV.Lexemes_Exception {
        HAVE_OPEN_BRACKETS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }}

    public static class IMPOSSIBLE_COUNT extends MRV.Lexemes_Exception {
        public IMPOSSIBLE_COUNT(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MISS_ARGUMENT_BINARY_OPERATOR extends MRV.Lexemes_Exception {
        public MISS_ARGUMENT_BINARY_OPERATOR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MISS_ARGUMENT_POST_OPERATOR extends MRV.Lexemes_Exception {
        public MISS_ARGUMENT_POST_OPERATOR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MISS_ARGUMENT_PRE_OPERATOR extends MRV.Lexemes_Exception {
        public MISS_ARGUMENT_PRE_OPERATOR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MORE_RIGHT_BRACKETS extends MRV.Lexemes_Exception {
        public MORE_RIGHT_BRACKETS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class UNKNOWN_ERROR extends MRV.Lexemes_Exception {
        public UNKNOWN_ERROR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class UNKNOWN_FUNCTION extends MRV.Lexemes_Exception {
        public UNKNOWN_FUNCTION(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    static class MATRIX_ERROR extends Exception{
    }

    public static class NON_QUADRATIC_MATRIX extends MATRIX_ERROR{
    }
    public static class DEGENERATE_MATRIX extends MATRIX_ERROR{
    }
    public static class MATRIX_FAIL extends MATRIX_ERROR{
    }
    public static class MATRIX_DIMENSION_MISSMATCH extends MATRIX_ERROR{

    }
    public static class INVALID_NUMBER_STRING extends MATRIX_ERROR{
    }
    public static class HAVE_NOT_SOLUTIONS extends MATRIX_ERROR{}
    public static class NON_SINGLE extends MATRIX_ERROR{
    }
    public static class NOT_IMPLEMENT extends Exception{
    }
}



