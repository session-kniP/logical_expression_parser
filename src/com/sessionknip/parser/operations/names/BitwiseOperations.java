package com.sessionknip.parser.operations.names;

import com.sessionknip.parser.TokenType;
import com.sessionknip.parser.expressions.names.StringOperandExpression;

public abstract class BitwiseOperations {

    private static final String OP_CHARS = "-*+>~()";
    private static final TokenType[] OP_TOKENS = {TokenType.NOT, TokenType.AND, TokenType.OR, TokenType.IMPL, TokenType.EQUIV, TokenType.O_BRACKET, TokenType.C_BRACKET};
    private static final String[] OP_NAMES = {"Inversion", "Conjunction", "Disjunction", "Implication", "Equivalention"};   //Just for front-end


    public static String conjunction(StringOperandExpression expr1, StringOperandExpression expr2) {
        return expr1.getName() + " * " + expr2.getName();
    }

    public static String disjunction(StringOperandExpression expr1, StringOperandExpression expr2) {
        return expr1.getName() + " + " + expr2.getName();
    }

    public static String implication(StringOperandExpression expr1, StringOperandExpression expr2) {
        return expr1.getName() + " > " + expr2.getName();
    }

    public static String equivalent(StringOperandExpression expr1, StringOperandExpression expr2) {
        return expr1.getName() + " ~ " + expr2.getName();
    }


    public static String addOBracket(StringOperandExpression expr) {
        return "(" + expr.getName();
    }

    public static String addCBracket(StringOperandExpression expr) {
        return expr.getName() + ')';
    }



    public static TokenType getOperationTokenType(char expression) {
        int pos = OP_CHARS.indexOf(expression);
        return OP_TOKENS[pos];
    }

    public static String getOpChars() {
        return OP_CHARS;
    }

    public static int getOpCount() {
        return OP_NAMES.length;
    }

    public static String getOpName(int charPos) {
        return OP_NAMES[charPos];
    }

}
