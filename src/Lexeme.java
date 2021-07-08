import java.util.ArrayList;

class Lexeme {
private Id_lexemes id;
private ArrayList<Double> values = new ArrayList<>();
public Lexeme(){

    values.add( 3.0 );
}
    public Lexeme(Id_lexemes id, ArrayList<Double> values){
        this.values = values;
        this.id = id;
    }
    public Lexeme(Id_lexemes id){
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
    public String code(Archieve archieve){
        String A = "";
        if (id==Id_lexemes.ARGUMENT) {
            if (values.size() > 1) {
                A+="(";
                A+=values.get( 0 );
                for (int i = 1; i < values.size(); i++) {
                    A += ",";
                    A += Double.toString( values.get( i ) );
            }
            }
            else{
                A+=Double.toString( values.get( 0 ) );
            }
        }
        else if(id==Id_lexemes.LEFT_BR) A+= "(";
        else if(id==Id_lexemes.RIGHT_BR) A+= ")";
        else if(id==Id_lexemes.X) A+= "x";
        else if(id==Id_lexemes.END);
        else {
            A = archieve.code(id);
        }
        return A;
    }
}
