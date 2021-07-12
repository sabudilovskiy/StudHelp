package MRV;

import java.util.ArrayList;

public class Sentence {
    private ArrayList <Lexeme> _array = new ArrayList<>();
    public Sentence()
    {
    };
    public Sentence(String input)
    {
        int pos = 0;
        while (pos < input.length())
        {
            while (pos < input.length() && input.charAt(pos) == ' ')pos++;
            if (pos < input.length())
            {
                int begin = pos;
                if (Support.is_numeral(input.charAt(pos)))
                {
                    String buffer = "";
                    boolean use_point = false;
                    while (pos < input.length() && (input.charAt(pos) == '.' || Support.is_numeral(input.charAt(pos))))
                    {
                        if (input.charAt(pos) == '.')
                        {
                            if (use_point == false)
                            {
                                use_point = true;
                                buffer+=(input.charAt(pos++));
                            }
                            else
                            {
                                ErrorHandler.setError( Id_errors.ERROR_SIGNS, pos, pos );
                                return;
                            }
                        }
                        else
                        {
                            buffer+=input.charAt(pos++);
                        }
                    }
                    ArrayList <Double> temp_array = new ArrayList<>();
                    temp_array.add(Double.parseDouble(buffer));
                    Lexeme temp = new Lexeme(Id_lexemes.ARGUMENT, temp_array);
                    temp.setBegin (begin);
                    temp.setEnd (pos - 1);
                    this.add_lexeme(temp);
                }
                else
                {
                    String buffer = new String();
                    ArrayList <Integer> a = new ArrayList<>();
                    a.add(-1);
                    int max_id = -1;
                    int pos_max = pos;
                    String max_buffer = "";
                    while (a.size() > 0 && pos < input.length())
                    {
                        buffer+=input.charAt(pos++);
                        a = Archieve.decode(buffer, null);
                        for (int i = 0; i < a.size(); i++)
                        {
                            if (a.get(i) > 0)
                            {
                                max_buffer = buffer;
                                max_id = a.get(i);
                                pos_max = pos;
                                break;
                            }
                        }
                    }
                    if (max_id == -1)
                    {
                            int end = pos - 1;
                            ErrorHandler.setError( Id_errors.UNKNOWN_FUNCTION, begin, end);
                            return;
                    }
                    else
                    {
                        Id_lexemes id = Id_lexemes.get_by_id(max_id);
                        Lexeme temp;
                        pos = pos_max;
                        if (id != Id_lexemes.VARIABLE){
                            temp = new Lexeme(id);
                        }
                        else {
                            temp = new Lexeme (max_buffer);
                        }
                        temp.setBegin (begin);
                        temp.setEnd (pos_max - 1);
                        this.add_lexeme(temp);
                    }
                }
            }
        }
        int left_brs = 0, right_brs = 0;
        for (int i = 0; i < _array.size (); i++)
        {
            if (_array.get (i).get_id () == Id_lexemes.LEFT_BR) left_brs++;
            else if(_array.get (i).get_id () == Id_lexemes.RIGHT_BR) right_brs++;
            if (right_brs > left_brs){
                ErrorHandler.setError (Id_errors.MORE_RIGHT_BRACKETS, _array.get (i));
                return;
            }
        }
        if (left_brs == right_brs)
        {
            this.add_lexeme(new Lexeme(Id_lexemes.END));
            return;
        }
        else
        {
            int a = find_left_br ();
            ErrorHandler.setError(Id_errors.HAVE_OPEN_BRACKETS,a, a );
            return;
        }
    }
    Sentence(ArrayList <Lexeme> array)
    {
        for (Lexeme lexeme : array) add_lexeme(lexeme);
    }
    int find_left_br()
    {
        for (int i = 0; _array.get(i).get_id() != Id_lexemes.END; i++)
        {
            if (_array.get(i).get_id() == Id_lexemes.LEFT_BR) return i;
        }
        return -1;
    }
    //получить номер лексемы-конца
    int get_end()
    {
        return _array.size() - 1;
    }
    //добавить лексему
    void add_lexeme(Lexeme a)
    {
        _array.add(a);
    }
    //удалить лексему с таким номером
    void delete_lexeme(int i)
    {
        _array.remove(i);
    }
    //заменить лексему x на значение
    public void substitute(ArrayList<String> keys, ArrayList<Double> values)
    {
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            double x = values.get(i);
            for (int j = 0; j < _array.size(); j++)
            {
                if (_array.get(j).get_id() == Id_lexemes.VARIABLE && _array.get( j ).getKey().equals( key ))
                {
                    ArrayList <Double> x0 = new ArrayList<>();
                    x0.add(x);
                    _array.set(j,new Lexeme(Id_lexemes.ARGUMENT, x0));
                }
            }
        }

    }
    //создать новое предложение из части старого
    Sentence create_lexeme_vector(int a, int b)
    {
        ArrayList <Lexeme> buffer = new ArrayList<>();
        for (int i = a; i <= b; i++)
        {
            buffer.add(_array.get(i));
        }
        buffer.add(new Lexeme(Id_lexemes.END, new ArrayList<Double>()));
        return new Sentence(buffer);
    }
    //заменить диапазон на одну лексему
    void replace_sector( int a,  int b, Lexeme paste)
    {
        for (int i = a; i < b; i++)
        {
            this.delete_lexeme(a);
        }
        _array.set(a,paste);
    }
    ArrayList <Integer> find_commas(int begin)
    {
        ArrayList <Integer> answer = new ArrayList<>();
        for (int i = 0; _array.get(i).get_id() != Id_lexemes.END; i++)
        {
            if (_array.get(i).get_id() == Id_lexemes.COMMA)
            {
                answer.add(i + begin + 1);
            }
            else if (_array.get(i).get_id() == Id_lexemes.LEFT_BR)
            {
                i = find_right_bracket(i);
            }
        }
        return answer;
    }
    //найти закрывающую скобку для открывающей
    int find_right_bracket(int a)
    {
        int count_open_brackets = 1;
        for (int i = a + 1; ; i++)
        {
            if (_array.get(i).get_id() == Id_lexemes.LEFT_BR) count_open_brackets++;
            else if (_array.get(i).get_id() == Id_lexemes.RIGHT_BR) count_open_brackets--;
            if (count_open_brackets == 0)
            {
                return i;
            }
        }
    }
    //найти оператор с наивысшим приоритетом
    int find_highest_priority()
    {
        int max_priority = 0;
        for (int i = 0; _array.get(i).get_id() != Id_lexemes.END; i++)
        {
            if (_array.get(i).get_id() != Id_lexemes.ARGUMENT)
            {
                int cur_priority = Archieve.get_priority(_array.get(i).get_id());
                if (cur_priority > max_priority)
                {
                    max_priority = cur_priority;
                }
            }
        }
        return max_priority;
    }
    int find_countable_operator(int priority)
    {
        for (int i = 0; _array.get(i).get_id() != Id_lexemes.END; i++)
        {
            if (_array.get(i).get_id() != Id_lexemes.ARGUMENT && Archieve.get_priority(_array.get(i).get_id())==priority)
            {
                int left = Archieve.get_left_argue(_array.get(i).get_id());
                int right = Archieve.get_right_argue(_array.get(i).get_id());
                if ((left == 0 || _array.get(i - 1).get_id() == Id_lexemes.ARGUMENT) && (right == 0 || _array.get(i + 1).get_id() == Id_lexemes.ARGUMENT)) return i;
            }
        }
        return -1;
    }
    boolean have_errors()
    {
        int n = _array.size() - 1;
        if (Archieve.get_left_argue(_array.get(0).get_id()) == 0 && Archieve.get_right_argue(_array.get(n).get_id()) == 0)
        {
            for (int i = 1; i < n; i++)
            {
                Id_lexemes cur_id = _array.get(i).get_id();
                int left = Archieve.get_left_argue(cur_id);
                int right = Archieve.get_right_argue(cur_id);
                if (left > 0 && right > 0)
                {
                    if (Archieve.get_right_argue(_array.get(i - 1).get_id()) == 0 && Archieve.get_left_argue(_array.get(i + 1).get_id()) == 0)
                    {

                    }
					else
                    {
                        ErrorHandler.setError( Id_errors.MISS_ARGUMENT_BINARY_OPERATOR, _array.get (i)  );
                        return true;
                    }
                }
				else if (left > 0)
            {
                if (Archieve.get_right_argue(_array.get(i - 1).get_id()) == 0 && Archieve.get_left_argue(_array.get(i+1).get_id()) != 0)
                {

                }
					else
                {
                    ErrorHandler.setError( Id_errors.MISS_ARGUMENT_POST_OPERATOR, _array.get (i) );
                    return true;
                }
            }
            else if (right > 0)
            {
                if (Archieve.get_left_argue(_array.get(i + 1).get_id()) == 0 && Archieve.get_right_argue(_array.get(i - 1).get_id()) != 0)
                {

                }
					else
                {
                    ErrorHandler.setError( Id_errors.MISS_ARGUMENT_PRE_OPERATOR, _array.get (i) );
                    return true;
                }
            }
            }
        }
        return false;
    }
    //посчитать значение предложения
    public Lexeme count() {
        int a = find_left_br();
        while (a != -1) //избавляемся от скобок
        {
            int b = find_right_bracket( a );
            Sentence current = this.create_lexeme_vector( a + 1, b - 1 );
            ArrayList<Integer> commas = current.find_commas( a );
            //в скобках выражение без запятых
            if (commas.size() == 0) {
                Lexeme replace = current.count(  );
                if (ErrorHandler.getError() == Id_errors.NON_ERROR) {
                    this.replace_sector( a, b, replace );
                } else {
                    return new Lexeme( Id_lexemes.END, new ArrayList<Double>() );
                }
            }
            //есть запятые
            else {
                ArrayList<Sentence> A = new ArrayList<>();
                ArrayList<Double> values = new ArrayList<>();
                for (int i = 0; i < commas.size() + 1; i++) {
                    A.add( new Sentence() );
                    values.add( 0.0 );
                }
                A.set( 0, this.create_lexeme_vector( a + 1, commas.get( 0 ) - 1 ) );
                for (int i = 1; i < commas.size(); i++) {
                    A.set( 1, this.create_lexeme_vector( commas.get( i - 1 ) + 1, commas.get( i ) - 1 ) );
                }
                A.set( A.size() - 1, this.create_lexeme_vector( commas.get( commas.size() - 1 ) + 1, b - 1 ) );
                for (int i = 0; i < values.size(); i++) {
                    values.set( i, A.get( i ).count().get_value() );
                    if (ErrorHandler.getError() != Id_errors.NON_ERROR) {
                        return new Lexeme( Id_lexemes.END, new ArrayList<Double>() );
                    }
                }
                Lexeme replace = new Lexeme( Id_lexemes.ARGUMENT, values );
                this.replace_sector( a, b, replace );
            }
            a = find_left_br();
        }
        if (have_errors ()) {
            return new Lexeme (Id_lexemes.END);
        }
        a = find_highest_priority();
        while (a != 0) {
            int b = find_countable_operator(a);
            while (b != -1) {
                int left = Archieve.get_left_argue( _array.get( b ).get_id() );
                int right = Archieve.get_right_argue( _array.get( b ).get_id() );
                int l = left != 0 ? 1 : 0;
                int r = right != 0 ? 1 : 0;
                ArrayList<Double> left_argue = new ArrayList<>(), right_argue = new ArrayList<>();
                if (l != 0) {
                    left_argue = _array.get( b - 1 ).get_values();
                    if (left != left_argue.size()) {
                        ErrorHandler.setError( Id_errors.BAD_ARGUMENTS, _array.get (b) );
                        return new Lexeme( Id_lexemes.END, new ArrayList<Double>() );
                    }
                }
                if (r != 0) {
                    right_argue = _array.get( b + 1 ).get_values();
                    if (right != right_argue.size()) {
                        ErrorHandler.setError( Id_errors.BAD_ARGUMENTS, _array.get (b) );
                        return new Lexeme( Id_lexemes.END, new ArrayList<Double>() );
                    }
                }
                ArrayList<Double> argue = Support.union( left_argue, right_argue );
                if (Archieve.check_countable( _array.get( b ).get_id(), argue )) {
                    ArrayList<Double> x0 = new ArrayList<Double>();
                    x0.add( Archieve.count( _array.get( b ).get_id(), argue ) );
                    Lexeme replace = new Lexeme( Id_lexemes.ARGUMENT, x0 );
                    this.replace_sector( b - 1 * l, b + 1 * r, replace );
                } else {
                    ErrorHandler.setError( Id_errors.IMPOSSIBLE_COUNT, _array.get (b) );
                    return new Lexeme( Id_lexemes.END, new ArrayList<>() );
                }
                b = find_countable_operator(a) ;
            }
            a = find_highest_priority();
        }
        if (_array.size() == 2 && _array.get( 0 ).get_id() == Id_lexemes.ARGUMENT && _array.get( 1 ).get_id() == Id_lexemes.END) {
            return _array.get( 0 );
        }
        else{
            ErrorHandler.setError( Id_errors.UNKNOWN_ERROR, -1, -1 );
            return new Lexeme( Id_lexemes.END, new ArrayList<Double>() );
        }
    }
    String code(){
        String A = "";
        for (int i = 0; _array.get( i ).get_id() != Id_lexemes.END; i++) {
            Lexeme cur_lexeme = _array.get( i );
            A+= cur_lexeme.getKey();
        }
        return A;
    }
}
