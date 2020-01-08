package com.sessionknip.parser.expressions.names;

import com.sessionknip.parser.operations.names.BitwiseOperations;

public class BooleanExpression implements NamesExpression {

    private final StringOperandExpression expr1, expr2;
    private final char operator;

    public BooleanExpression(char operator, StringOperandExpression expr1, StringOperandExpression expr2) {
        this.operator = operator;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public StringOperandExpression getExpressionResult() {
        switch (operator) {

            case '+':
                return new StringOperandExpression(BitwiseOperations.disjunction(expr1, expr2));

            case '>':
                return new StringOperandExpression(BitwiseOperations.implication(expr1, expr2));

            case '~':
                return new StringOperandExpression(BitwiseOperations.equivalent(expr1, expr2));

            default:
                return new StringOperandExpression(BitwiseOperations.conjunction(expr1, expr2));  //conjunction and nothing else, but class method requires to return something in any case
        }
    }

    @Override
    public String toString(){
        return String.format("%b %c %b", expr1, operator, expr2);
    }

}
