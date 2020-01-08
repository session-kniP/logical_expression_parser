package com.sessionknip.parser.values;

import com.sessionknip.parser.TokenType;

public class Token {

    private final TokenType type;
    private final boolean value;

    public Token(TokenType type, boolean value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public boolean getValue() {
        return value;
    }

}
