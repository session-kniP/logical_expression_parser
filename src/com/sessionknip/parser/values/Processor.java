package com.sessionknip.parser.values;

import com.sessionknip.parser.TokenType;
import com.sessionknip.parser.expressions.values.BooleanExpression;
import com.sessionknip.parser.expressions.values.InversionExpression;
import com.sessionknip.parser.expressions.values.OperandExpression;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    private final List<Token> tokens;
    private int position;
    private final int SIZE;


    public Processor(List<Token> tokens) {
        this.tokens = tokens;
        position = 0;
        SIZE = tokens.size();
    }

    public OperandExpression parse() {
        List<OperandExpression> result = new ArrayList<>();

//        while (!match(TokenType.END_OF_STRING)) {
//            result.add(expression());
//        }

        return expression();
    }

    private OperandExpression expression() {
        return equivalentExpression();
    }

    private OperandExpression equivalentExpression() {
        OperandExpression result = implicativeExpression();
        while (true) {
            if (match(TokenType.EQUIV)) {
                result = new BooleanExpression('~', result, implicativeExpression()).getExpressionResult();
                continue;
            }
            break;
        }
        return result;
    }

    private OperandExpression implicativeExpression() {
        OperandExpression result = disjunctiveExpression();

        while (true) {
            if (match(TokenType.IMPL)) {
                result = new BooleanExpression('>', result, disjunctiveExpression()).getExpressionResult();
                continue;
            }
            break;
        }
        return result;
    }

    private OperandExpression disjunctiveExpression() {
        OperandExpression result = conjunctiveExpression();

        while (true) {
            if (match(TokenType.OR)) {
                result = new BooleanExpression('+', result, conjunctiveExpression()).getExpressionResult();
                continue;
            }
            break;
        }
        return result;
    }

    private OperandExpression conjunctiveExpression() {
        OperandExpression result = inversionExpression();

        while (true) {
            if (match(TokenType.AND)) {
                result = new BooleanExpression('*', result, inversionExpression()).getExpressionResult();
                continue;
            }
            break;
        }
        return result;
    }

    private OperandExpression inversionExpression() {
        if (match(TokenType.NOT)) {
            return new InversionExpression(operandExpression());
        }
        return operandExpression();
    }

    private OperandExpression operandExpression() {

        Token current = getToken(false);
        if (match(TokenType.OPERAND)) {
            return new OperandExpression(current.getValue());
        }
        if (match(TokenType.O_BRACKET)) {
            OperandExpression result = expression();
            match(TokenType.C_BRACKET);
            return result;
        }
        throw new RuntimeException("Unknown ValuesExpression");
    }


    private Token getToken(boolean isWithOffsetShift) {
        if (position >= SIZE) {
            return new Token(TokenType.END_OF_STRING, false);
        }
        if (isWithOffsetShift) {
            return tokens.get(position++);
        }
        return tokens.get(position);
    }

    private boolean match(TokenType type) {
        if (getToken(false).getType() != type) {
            return false;
        }
        position++;
        return true;
    }
}
