import java.util.ArrayList;

public class MRV {
    static double count_lexemes( String input, ArrayList<String> keys, ArrayList<Double> values ) throws MRV_ARGUMENT_LIST_MISMATCH, MRV_UNKNOWN_FUNCTION, MRV_ERROR_SIGNS, MRV_IMPOSSIBLE_COUNT, MRV_MISS_ARGUMENT_BINARY_OPERATOR, MRV_MISS_ARGUMENT_PRE_OPERATOR, MRV_MISS_ARGUMENT_POST_OPERATOR, MRV_HAVE_OPEN_BRACKETS, MRV_MORE_RIGHT_BRACKETS, MRV_BAD_ARGUMENTS, MRV_UNKNOWN_ERROR {
        if (keys.size() != values.size()) {
            throw new MRV_ARGUMENT_LIST_MISMATCH();
        }
        new Archieve (keys);
        Lexeme answer;
        Sentence sentence = new Sentence(input);
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

class MRV_ARGUMENT_LIST_MISMATCH extends Exception{
    public MRV_ARGUMENT_LIST_MISMATCH(){
    }
}
class MRV_UNKNOWN_FUNCTION extends Exception{
    public MRV_UNKNOWN_FUNCTION(){
    }
}
class MRV_ERROR_SIGNS extends Exception{
    public MRV_ERROR_SIGNS(){
    }
}
class MRV_IMPOSSIBLE_COUNT extends Exception{
    public MRV_IMPOSSIBLE_COUNT(){
    }
}
class MRV_BAD_ARGUMENTS extends Exception{
    public MRV_BAD_ARGUMENTS(){
    }
}
class MRV_HAVE_OPEN_BRACKETS extends Exception{
    MRV_HAVE_OPEN_BRACKETS(){}}
class MRV_MORE_RIGHT_BRACKETS extends Exception{
    public MRV_MORE_RIGHT_BRACKETS(){
    }
}
class MRV_MISS_ARGUMENT_BINARY_OPERATOR extends Exception{
    public MRV_MISS_ARGUMENT_BINARY_OPERATOR(){}
}
class MRV_MISS_ARGUMENT_POST_OPERATOR extends Exception{
    public MRV_MISS_ARGUMENT_POST_OPERATOR(){}
}
class MRV_MISS_ARGUMENT_PRE_OPERATOR extends Exception{
    public MRV_MISS_ARGUMENT_PRE_OPERATOR(){}
}
class MRV_UNKNOWN_ERROR extends Exception{
    public MRV_UNKNOWN_ERROR(){}
}


