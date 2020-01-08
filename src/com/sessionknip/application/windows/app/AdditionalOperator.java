package com.sessionknip.application.windows.app;

public class AdditionalOperator {

    private final String name;
    private final String expression;

    public AdditionalOperator(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public String getExpression() {
        return expression;
    }

}
