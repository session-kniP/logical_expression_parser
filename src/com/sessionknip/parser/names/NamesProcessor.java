package com.sessionknip.parser.names;

import com.sessionknip.parser.TokenType;
import com.sessionknip.parser.expressions.names.BooleanExpression;
import com.sessionknip.parser.expressions.names.InversionExpression;
import com.sessionknip.parser.expressions.names.NamesExpression;
import com.sessionknip.parser.expressions.names.StringOperandExpression;
import com.sessionknip.parser.operations.names.BitwiseOperations;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class NamesProcessor {

    private final List<NamesToken> tokens;
    private List<NamesExpression> allExpressions;
    private List<String> operandExpressionNames;
    private int position;
    private final int SIZE;

    public NamesProcessor(List<NamesToken> tokens) {
        this.tokens = tokens;
        allExpressions = new ArrayList<>();
        operandExpressionNames = new ArrayList<>();
        position = 0;
        SIZE = tokens.size();
    }

    public List<StringOperandExpression> parse() {
        List<StringOperandExpression> result = new ArrayList<>();

        while (!match(TokenType.END_OF_STRING)) {
            StringOperandExpression expression = expression(false);
            result.add(expression);
        }

        return result;
    }

    private StringOperandExpression expression(boolean isInBrackets) {
        StringOperandExpression expression = equivalentExpression(isInBrackets);

        if (isInBrackets) {
            StringOperandExpression bracketsExpression = new StringOperandExpression(BitwiseOperations.addOBracket(expression));
            return new StringOperandExpression(BitwiseOperations.addCBracket(bracketsExpression));
        }

        allExpressions.add(expression);
        return expression;
    }

    private StringOperandExpression equivalentExpression(boolean isInBrackets) {
        StringOperandExpression result = implicativeExpression(isInBrackets);
        while (true) {
            if (match(TokenType.EQUIV)) {
                result = new BooleanExpression('~', result, implicativeExpression(isInBrackets)).getExpressionResult();
                allExpressions.add(result);
                continue;
            }
            break;
        }
        return result;
    }

    private StringOperandExpression implicativeExpression(boolean isInBrackets) {
        StringOperandExpression result = disjunctiveExpression(isInBrackets);

        while (true) {
            if (match(TokenType.IMPL)) {
                allExpressions.add(result);
                result = new BooleanExpression('>', result, disjunctiveExpression(isInBrackets)).getExpressionResult();
                continue;
            }
            break;
        }
        return result;
    }

    private StringOperandExpression disjunctiveExpression(boolean isInBrackets) {
        StringOperandExpression result = conjunctiveExpression(isInBrackets);

        while (true) {
            if (match(TokenType.OR)) {
                result = new BooleanExpression('+', result, conjunctiveExpression(isInBrackets)).getExpressionResult();
                allExpressions.add(result);
                continue;
            }
            break;
        }
        return result;
    }

    private StringOperandExpression conjunctiveExpression(boolean isInBrackets) {
        StringOperandExpression result = inversionExpression(isInBrackets);

        while (true) {
            if (match(TokenType.AND)) {
                result = new BooleanExpression('*', result, inversionExpression(isInBrackets)).getExpressionResult();
                allExpressions.add(result);
                continue;
            }
            break;
        }
        return result;
    }

    private StringOperandExpression inversionExpression(boolean isInBrackets) {
        if (match(TokenType.NOT)) {
            InversionExpression expression = new InversionExpression(operandExpression(isInBrackets));
            allExpressions.add(expression);
            return expression;
        }
        return operandExpression(isInBrackets);
    }

    private StringOperandExpression operandExpression(boolean isInBrackets) {

        NamesToken current = getToken();
        if (match(TokenType.OPERAND)) {
            StringOperandExpression expression = new StringOperandExpression(current.getName());

            if (!contains(expression.getName())) {
                allExpressions.add(expression);
                operandExpressionNames.add(expression.getName());
            }
            return expression;
        } else if (match(TokenType.O_BRACKET)) {
            StringOperandExpression expression = expression(true);
            match(TokenType.C_BRACKET);
            return expression;
        }
        JOptionPane.showMessageDialog(JFrame.getFrames()[0], "<html>Check your expression <br>and try again</br></html>", "Expression Error", JOptionPane.ERROR_MESSAGE);
        throw new RuntimeException("Unknown ValuesExpression");
    }

    private NamesToken getToken() {
        if (position >= SIZE) {
            return new NamesToken(TokenType.END_OF_STRING, "");
        }
        return tokens.get(position);
    }


    private boolean match(TokenType type) {
        if (getToken().getType() != type) {
            return false;
        }
        position++;
        return true;
    }

    private boolean contains(String name) {
        for (NamesExpression e : allExpressions) {
            if (((StringOperandExpression) e).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<NamesExpression> getAllExpressions() {
        return allExpressions;
    }

    public List<String> getOperandExpressionNames() {
        return operandExpressionNames;
    }

}
