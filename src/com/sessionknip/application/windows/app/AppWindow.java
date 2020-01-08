package com.sessionknip.application.windows.app;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {

    private final String title;
    private int width, height;

    private Container container;

    public AppWindow(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        init();
    }

    private void init() {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(width, height));

        setLocationRelativeTo(null);
        setLayout(null);

        container = getContentPane();
        setResizable(false);

        AppWindowController.fillContainer(this, container);

        setVisible(true);

    }


}
