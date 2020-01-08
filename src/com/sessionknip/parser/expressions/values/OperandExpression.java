package com.sessionknip.parser.expressions.values;

public class OperandExpression implements ValuesExpression {

    private final boolean value;

    public OperandExpression(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

}
