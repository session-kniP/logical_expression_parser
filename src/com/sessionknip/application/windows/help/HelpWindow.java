package com.sessionknip.application.windows.help;

import com.sessionknip.application.windows.app.AppWindow;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HelpWindow extends JFrame {

    private final int width, height;
    private JTextArea textArea;


    public HelpWindow(int width, int height) {
        setTitle("Help");

        this.width = width;
        this.height = height;

        init();
    }

    private void init() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(width, height));
        setResizable(false);
        setLocationRelativeTo(AppWindow.getWindows()[0]);

        textArea = new JTextArea();
        textArea.setSize(width - 20, height - 20);
        textArea.setLocation(10, 10);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        //textArea.setForeground(Color.BLACK);



        getContentPane().add(textArea);
        fillTextArea();

        setVisible(true);

    }


    private void fillTextArea() {
        String path = System.getProperty("user.dir");
        path += "\\src\\com\\sessionknip\\application\\windows\\help\\help.txt";

        try(FileInputStream inputStream = new FileInputStream(path)){

            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
//                line.replaceAll("(?!\\r)\\n", "\r\n");
                textArea.append(line);
                textArea.append("\n");

                //textArea.
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
