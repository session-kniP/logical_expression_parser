package com.sessionknip.parser.expressions.values;

import com.sessionknip.parser.operations.values.BitwiseOperations;

public class BooleanExpression implements ValuesExpression {

    private final OperandExpression expr1, expr2;
    private final  char operator;

    public BooleanExpression(char operator, OperandExpression expr1, OperandExpression expr2) {
        this.operator = operator;
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public OperandExpression getExpressionResult() {
        switch (operator) {

            case '+':
                return new OperandExpression(BitwiseOperations.disjunction(expr1, expr2));

            case '>':
                return new OperandExpression(BitwiseOperations.implication(expr1, expr2));

            case '~':
                return new OperandExpression(BitwiseOperations.equivalent(expr1, expr2));

            default:
                return new OperandExpression(BitwiseOperations.conjunction(expr1, expr2));  //conjunction and nothing else, but class method requires to return something in any case
        }
    }

    @Override
    public String toString(){
        return String.format("%b %c %b", expr1, operator, expr2);
    }

}
