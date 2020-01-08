package com.sessionknip.parser.values;

import com.sessionknip.parser.TokenType;
import com.sessionknip.parser.operations.values.BitwiseOperations;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final String input;
    private List<Token> tokens;
    private int length;

    private int position = 0;

    public Parser(String input) {

        this.input = input;
        length = input.length();

        tokens = new ArrayList<>();
    }

    public List<Token> getTokens() {
        while (position < length) {
            char current = getNextChar();
            if (Character.isDigit(current)) {
                getOperandToken(current);
            } else if (BitwiseOperations.getOpChars().indexOf(current) != -1) {
                getOperationToken(current);
            }
        }

        return tokens;
    }

    private void getOperandToken(char operandChar) {
        boolean expression = Character.getNumericValue(operandChar) != 0;
        addToken(TokenType.OPERAND, expression);
    }

    private void getOperationToken(char operationChar) {
        addToken(BitwiseOperations.getOperationTokenType(operationChar));
    }

    private char getNextChar() {
        if (position >= length) {
            return '\0';
        }
        return input.charAt(position++);
    }

    private void addToken(TokenType type) {
        tokens.add(new Token(type, false));
    }

    private void addToken(TokenType type, boolean value) {
        tokens.add(new Token(type, value));
    }


}
