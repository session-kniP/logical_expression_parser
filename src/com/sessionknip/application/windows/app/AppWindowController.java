package com.sessionknip.application.windows.app;

import com.sessionknip.application.windows.help.HelpWindow;
import com.sessionknip.controls.Locator;
import com.sessionknip.controls.Sizer;
import com.sessionknip.parser.operations.names.BitwiseOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppWindowController {

    protected static JTextField txtExpression;
    protected static JMenuBar menu;
    protected static JTable table;
    protected static JScrollPane scrollPane;
    protected static JButton btnExecute;
    protected static JPanel panel;
    protected static JPanel tablePanel;
    protected static JComboBox<String> generates;
    protected static JTextField op1, op2;
    protected static JButton btnGenerate;

    private static HelpWindow helpWindow;

    protected static AdditionalOperator[] additionalOperations =
            {
                    new AdditionalOperator("Schaffer Bar", "-(%s * %s)"),
                    new AdditionalOperator("Pierce Arrow", "-(%s + %s)"),
                    new AdditionalOperator("Exclusive OR", "(-%1$S * %2$s + %1$s * -%2$s)")
            };

    private static void fillMenu(JFrame frame) {
        JMenu file = new JMenu("File");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem exit = new JMenuItem("Exit");

        help.addActionListener(e -> {
            if(helpWindow == null)
                helpWindow = new HelpWindow(frame.getWidth() / 2, frame.getHeight() - frame.getHeight() / 40);
            helpWindow.setVisible(true);
        });

        exit.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(menu),
                    "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        file.add(help);
        file.add(exit);

        menu.add(file);
    }

    private static void addOpButtons(JFrame frame, Container container) {
        int xLocation = 0, yLocation = 0;
        char[] operators = BitwiseOperations.getOpChars().toCharArray();

        int length = BitwiseOperations.getOpCount();
        if (length == 0) {
            return;
        }

        for (int i = 0; i < length; i++) {
            String opName = BitwiseOperations.getOpName(i);
            OpButton temp = new OpButton(opName + " (" + operators[i] + ")", operators[i]);

            if (i > 0) {
                temp.setLocation(xLocation + 10, yLocation);
            } else {
                Locator.setRelativeLocation(frame, txtExpression, temp, Locator.Directions.DOWN, 3);
            }

            temp.setSize(new Dimension(130, 30));
            temp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    txtExpression.setText(txtExpression.getText() + temp.getOperation());
                }
            });

            container.add(temp);

            xLocation = temp.getX() + temp.getWidth();
            yLocation = temp.getY();
        }

        table.setLocation(table.getX(), table.getY() + 20);
    }

    private static void fillPanel(JFrame frame, Container container) {
        Locator.setRelativeLocation(frame, table, panel, Locator.Directions.RIGHT, 5);

        String[] additionalStrings = new String[additionalOperations.length];

        for (int i = 0; i < additionalStrings.length; i++) {
            additionalStrings[i] = additionalOperations[i].getName();
        }

        generates = new JComboBox<>(additionalStrings);
        generates.setSize(130, 30);
        generates.setLocation(panel.getX() + 10, panel.getY() + 10);

        Dimension opFieldSize = new Dimension(50, 30);

        op1 = new JTextField("Name1");
        op1.setSize(opFieldSize);
        Locator.setRelativeLocation(frame, generates, op1, Locator.Directions.DOWN, 5);

        op2 = new JTextField("Name2");
        op2.setSize(opFieldSize);
        Locator.setRelativeLocation(frame, op1, op2, Locator.Directions.RIGHT, 3);

        btnGenerate = new JButton("Generate");
        btnGenerate.setSize(new Dimension(130, 30));
        Locator.setRelativeLocation(frame, op1, btnGenerate, Locator.Directions.DOWN, 3);

        container.add(generates);
        container.add(op1);
        container.add(op2);
        container.add(btnGenerate);

    }

    public static void fillContainer(JFrame frame, Container container) {

        menu = new JMenuBar();
        fillMenu(frame);

        frame.setJMenuBar(menu);
        txtExpression = new JTextField();
        txtExpression.setSize(new Dimension(frame.getWidth() * 70 / 100, 30));
        txtExpression.setLocation(20, 30);
        txtExpression.setFont(new Font("Arial", Font.BOLD, 14));
        txtExpression.setToolTipText("Boolean Expression");
        ToolTipManager.sharedInstance().setDismissDelay(1000);

        JToolTip toolTip = txtExpression.createToolTip();
        toolTip.setBackground(Color.WHITE);
        toolTip.setForeground(Color.BLACK);
        toolTip.setComponent(txtExpression);

        btnExecute = new JButton("Calculate");
        Sizer.setRelativeSize(frame, btnExecute, 10, 5);
        Locator.setRelativeLocation(frame, txtExpression, btnExecute, Locator.Directions.RIGHT, 10);


        table = new JTable(1, 1);
        table.setSize(txtExpression.getWidth(), txtExpression.getHeight());
        table.setRowHeight(txtExpression.getHeight());
        Locator.setRelativeLocation(frame, txtExpression, table, Locator.Directions.DOWN, 10);
        table.setVisible(true);

        scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(table.getX(), table.getY(), table.getWidth(), table.getHeight());
//        scrollPane.setHorizontalScrollBar(new JScrollBar());


        panel = new JPanel();
        Locator.setRelativeLocation(frame, table, panel, Locator.Directions.RIGHT, 10);
        panel.setSize(new Dimension(160, 200));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 3f, 3f));



        fillPanel(frame, container);

        addOpButtons(frame, container);

        AppWindowListener.addListeners(container);

        container.add(txtExpression);
        container.add(btnExecute);
//        container.add(tablePanel);
//        container.add(scrollPane);
        container.add(table);
        container.add(panel);



    }

}
