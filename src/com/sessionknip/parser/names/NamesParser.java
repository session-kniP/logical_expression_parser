package com.sessionknip.parser.names;

import com.sessionknip.parser.TokenType;
import com.sessionknip.parser.operations.names.BitwiseOperations;

import java.util.ArrayList;
import java.util.List;

public class NamesParser {

    private final String input;
    private List<NamesToken> tokens;
    private int length;

    private int position = 0;


    public NamesParser(String input) {
        this.input = input;
        length = input.length();

        tokens = new ArrayList<>();
    }


    public List<NamesToken> getTokens() {
        String buf = "";
        while (position < length) {
            char current = getNextChar();
            if (BitwiseOperations.getOpChars().indexOf(current) == -1) {
                if (position != length) {
                    buf += current;
                    continue;
                }
            } else {
                if (current != '-' && current != '(' && !buf.equals("")) {
                    getOperandToken(buf);
                }
                buf = "";

                getOperationToken(current);

                continue;
            }
            getOperandToken(buf += current);
        }

        return tokens;
    }

    private void getOperandToken(String operandName) {
        addToken(operandName);
    }

    private void getOperationToken(char operationChar) {
        addToken(BitwiseOperations.getOperationTokenType(operationChar));
    }

    private char getNextChar() {
        if (position > length) {
            return '\0';
        }
        return input.charAt(position++);
    }

    private void addToken(TokenType type) {
        tokens.add(new NamesToken(type, ""));
    }

    private void addToken(String name) {
        tokens.add(new NamesToken(TokenType.OPERAND, name));
    }


}
