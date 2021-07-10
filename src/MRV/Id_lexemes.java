package MRV;

public enum Id_lexemes {
    //пре-унарные операторы, высший приоритет
    ARGUMENT, VARIABLE, LEFT_BR, RIGHT_BR, COMMA, ABS, SIN, COS, TG, CTG, ARCSIN, ARCCOS, ARCTG, ARCCTG, EXP, LN,
    //псевдо пре-унарный оператор
    LOG,
    //пост-унарные операторы, приоритет: 2
    FACTORIAL,
    //бинарные операторы, приоритет: 3
    POW,
    //бинарные операторы, приоритет: 4
    MULT, DIV,
    //бинарные операторы, низший приоритет
    PLUS, MINUS,
    //требуется для определения числа операторов
    NUMBER_OPERATORS,
    //технические вещи
    END;

    public static int getId(Id_lexemes lexeme) {
        for (int i = 0; i < Id_lexemes.values().length; i++) {
            if (lexeme == Id_lexemes.values()[i])
                return i;
        }
        throw new NumberFormatException("No such element in enum");
    }


    static public Id_lexemes get_by_id(int id) {
        return Id_lexemes.values()[id];
    }
}
