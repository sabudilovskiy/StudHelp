import java.util.ArrayList;

public class MRV {
    static double count_lexemes( String input, ArrayList<String> keys, ArrayList<Double> values ) throws MRV_ARGUMENT_LIST_MISMATCH, MRV_UNKNOWN_FUNCTION, MRV_ERROR_SIGNS, MRV_IMPOSSIBLE_COUNT, MRV_MISS_ARGUMENT_BINARY_OPERATOR, MRV_MISS_ARGUMENT_PRE_OPERATOR, MRV_MISS_ARGUMENT_POST_OPERATOR, MRV_HAVE_OPEN_BRACKETS, MRV_MORE_RIGHT_BRACKETS, MRV_BAD_ARGUMENTS, MRV_UNKNOWN_ERROR {
        if (keys.size() != values.size()) {
            throw new MRV_ARGUMENT_LIST_MISMATCH();
        }
        new Archieve (keys);
        Lexeme answer;
        Sentence sentence = new Sentence(input);
        if ( ErrorHandler.getError () == Id_errors.ERROR_SIGNS ) throw new MRV_ERROR_SIGNS ();
        else if ( ErrorHandler.getError () == Id_errors.UNKNOWN_FUNCTION ) throw new MRV_UNKNOWN_FUNCTION ();
        sentence.substitute(keys, values);
        answer = sentence.count();
        if (ErrorHandler.getError() == Id_errors.NON_ERROR)
        {
            return answer.get_value();
        }
        else if (ErrorHandler.getError() == Id_errors.UNKNOWN_FUNCTION)
        {
            throw new MRV_UNKNOWN_FUNCTION();
        }
        else if (ErrorHandler.getError() == Id_errors.ERROR_SIGNS)
        {
            throw new MRV_ERROR_SIGNS();
        }
        else if (ErrorHandler.getError() == Id_errors.IMPOSSIBLE_COUNT)
        {
            throw new MRV_IMPOSSIBLE_COUNT();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_BINARY_OPERATOR)
        {
            throw new MRV_MISS_ARGUMENT_BINARY_OPERATOR();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_PRE_OPERATOR)
        {
            throw new MRV_MISS_ARGUMENT_PRE_OPERATOR();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_POST_OPERATOR)
        {
            throw new MRV_MISS_ARGUMENT_POST_OPERATOR();
        }
        else if (ErrorHandler.getError() == Id_errors.HAVE_OPEN_BRACKETS)
        {
            throw new MRV_HAVE_OPEN_BRACKETS();
        }
        else if (ErrorHandler.getError() == Id_errors.MORE_RIGHT_BRACKETS)
        {
            throw new MRV_MORE_RIGHT_BRACKETS();
        }
        else if (ErrorHandler.getError() == Id_errors.BAD_ARGUMENTS)
        {
            throw new MRV_BAD_ARGUMENTS();
        }
        else
        {
            throw new MRV_UNKNOWN_ERROR();
        }

    }
}
class Func_Input_Exception extends Exception{
    private int error_begin = -1;
    private int error_end = -1;
    Func_Input_Exception(){

    }
    Func_Input_Exception(int begin, int end){
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
class MRV_ARGUMENT_LIST_MISMATCH extends Func_Input_Exception{
    public MRV_ARGUMENT_LIST_MISMATCH(){
    }
}
class MRV_UNKNOWN_FUNCTION extends Func_Input_Exception{
    public MRV_UNKNOWN_FUNCTION(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_ERROR_SIGNS extends Func_Input_Exception{
    public MRV_ERROR_SIGNS(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_IMPOSSIBLE_COUNT extends Func_Input_Exception{
    public MRV_IMPOSSIBLE_COUNT(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_BAD_ARGUMENTS extends Func_Input_Exception{
    public MRV_BAD_ARGUMENTS(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_HAVE_OPEN_BRACKETS extends Func_Input_Exception{
    MRV_HAVE_OPEN_BRACKETS(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }}
class MRV_MORE_RIGHT_BRACKETS extends Func_Input_Exception{
    public MRV_MORE_RIGHT_BRACKETS(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_MISS_ARGUMENT_BINARY_OPERATOR extends Func_Input_Exception{
    public MRV_MISS_ARGUMENT_BINARY_OPERATOR(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_MISS_ARGUMENT_POST_OPERATOR extends Func_Input_Exception{
    public MRV_MISS_ARGUMENT_POST_OPERATOR(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_MISS_ARGUMENT_PRE_OPERATOR extends Func_Input_Exception{
    public MRV_MISS_ARGUMENT_PRE_OPERATOR(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}
class MRV_UNKNOWN_ERROR extends Func_Input_Exception{
    public MRV_UNKNOWN_ERROR(){
        super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
    }
}


