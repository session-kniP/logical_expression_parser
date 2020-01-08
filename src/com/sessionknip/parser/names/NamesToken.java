package com.sessionknip.parser.names;

import com.sessionknip.parser.TokenType;

public class NamesToken {

    private final TokenType type;
    private final String name;

    public NamesToken(TokenType type, String name) {
        this.type = type;
        this.name = name;
    }

    public TokenType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
