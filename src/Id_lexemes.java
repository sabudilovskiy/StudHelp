public enum Id_lexemes {
    //пре-унарные операторы, высший приоритет
    ABS(1), SIN(2), COS(3), TG(4), CTG(5), ARCSIN(6), ARCCOS(7), ARCTG(8), ARCCTG(9), EXP(10), LN(11),
    //псевдо пре-унарный оператор
    LOG(12),
    //пост-унарные операторы, приоритет: 2
    FACTORIAL(13),
    //бинарные операторы, приоритет: 3
    POW(14),
    //бинарные операторы, приоритет: 4
    MULT(15), DIV(16),
    //бинарные операторы, низший приоритет
    PLUS(17), MINUS(18),
    //требуется для определения числа операторов
    NUMBER_OPERATORS(19),
    //технические вещи
    ARGUMENT(20), X(21), LEFT_BR(22), RIGHT_BR(23), COMMA(24), END(25);
    private int base;
    Id_lexemes(int base)
    {
        this.base = base;
    }
    public int get() {
        return base;
    }
}
