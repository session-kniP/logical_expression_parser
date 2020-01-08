package com.sessionknip.parser.expressions.values;

public class InversionExpression extends OperandExpression implements ValuesExpression {

    private final boolean value;


    public InversionExpression(OperandExpression expression) {
        super(expression.getValue());
        this.value = !expression.getValue();
    }

    public boolean getValue() {
        return value;
    }
}
