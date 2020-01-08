package com.sessionknip.application.windows.app;

import javax.swing.*;

public class OpButton extends JButton {

    private char operation;

    public OpButton(String title, char operation) {
        super(title);
        this.operation = operation;
    }

    public char getOperation() {
        return operation;
    }

}
