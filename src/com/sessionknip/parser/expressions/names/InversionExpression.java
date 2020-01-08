package com.sessionknip.parser.expressions.names;

public class InversionExpression extends StringOperandExpression implements NamesExpression {

    private final String name;

    public InversionExpression(StringOperandExpression expression) {
        super(expression.getName());
        this.name = '-' + expression.getName();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
