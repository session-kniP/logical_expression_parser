package com.sessionknip.parser.expressions.names;

public class StringOperandExpression implements NamesExpression {

    private String name;

    public StringOperandExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
