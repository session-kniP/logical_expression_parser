package com.sessionknip.parser.operations.values;

import com.sessionknip.parser.TokenType;
import com.sessionknip.parser.expressions.values.OperandExpression;



public abstract class BitwiseOperations {

    private static final String OP_CHARS = "-*+>~()";
    private static final TokenType[] OP_TOKENS =
            {
                    TokenType.NOT,
                    TokenType.AND,
                    TokenType.OR, TokenType.IMPL,
                    TokenType.EQUIV,
                    TokenType.O_BRACKET,
                    TokenType.C_BRACKET };


    public static boolean conjunction(OperandExpression expr1, OperandExpression expr2) {
        return expr1.getValue() & expr2.getValue();
    }

    public static boolean disjunction(OperandExpression expr1, OperandExpression expr2) {
        return expr1.getValue() | expr2.getValue();
    }

    public static boolean implication(OperandExpression expr1, OperandExpression expr2) {
        return !expr1.getValue() | expr2.getValue();
    }

    public static boolean equivalent(OperandExpression expr1, OperandExpression expr2) {
        return (expr1.getValue() | !expr2.getValue()) & (!expr1.getValue() | expr2.getValue());
    }

    public static TokenType getOperationTokenType(char expression) {
        int pos = OP_CHARS.indexOf(expression);
        return OP_TOKENS[pos];
    }

    public static String getOpChars() {
        return OP_CHARS;
    }

}





