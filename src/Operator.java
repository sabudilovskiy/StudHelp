import java.util.ArrayList;

public class Operator {
    private Id_lexemes id;
    private int left_argue = 0;
    private int right_argue = 0;
    private int priority;
    private ArrayList<String> decode_base;
    public int get_id(){
        return id.get();
    }
    public int get_left_argue()
    {
        return left_argue;
    }
    public int get_right_argue()
    {
        return right_argue;
    }
    public int is_it(String input)
    {
        int max_code = 0;
        for (int i = 0; i < decode_base.size(); i++)
        {
            if (decode_base.get(i).length() == input.length() && decode_base.get(i) == input) return 2;
			else if (decode_base.get(i).length() >= input.length())
        {
            int k = 1;
            for (int j = 0; j < input.length(); j++) if (decode_base.get(i).charAt(j) != input.charAt(j)) k = 0;
            max_code = k;
        }
        }
        return max_code;
    }
    int get_priority()
    {
        return priority;
    }
    //id, количество аргументов слева, количество аргументов справа, приоритет, количество кодовых слов, кодовые слова
    Operator(Id_lexemes id, int left_argue, int right_argue, int priority, ArrayList<String> list_of_words)
    {
        this.id = id;
        this.left_argue = left_argue;
        this.right_argue = right_argue;
        this.priority = priority;
        for (int i = 0; i < list_of_words.size(); i++)
        {
            decode_base.add(list_of_words.get(i));
        }
    }
    Operator()
    {
        priority = 0;
    }
}
