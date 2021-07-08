public enum Priority_operators {
    END, PRIOR_BOOL_FUNC, PRIOR_PLUS_MINUS, PRIOR_MULT_DIV, PRIOR_POW, PRIOR_FUNC;

    public static int getId(Priority_operators priority) {
        for (int i = 0; i < Priority_operators.values().length; i++) {
            if (priority == Priority_operators.values()[i])
                return i;
        }
        throw new NumberFormatException("No such element in enum");
    }
    static public Priority_operators get_by_id(int id) {
        return Priority_operators.values()[id];
    }
}
