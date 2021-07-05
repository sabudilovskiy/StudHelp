import java.util.ArrayList;

class Lexeme {
private Id_lexemes id;
private ArrayList<Double> values = new ArrayList<>(1);
public Lexeme(){
}
    public Lexeme(Id_lexemes id, ArrayList<Double> values){
        this.values = values;
        this.id = id;
    }
    public Id_lexemes get_id() {
        return id;
    }
    public double get_value(int i){
        return values.get(i);
    }
    public double get_value(){
        return values.get(0);
    }
    public ArrayList<Double> get_values()
    {
        return values;
    }
}
