package com.sessionknip.application.windows.app;

import com.sessionknip.Executor;
import com.sessionknip.parser.expressions.values.OperandExpression;
import com.sessionknip.parser.names.NamesProcessor;
import com.sessionknip.parser.operations.names.BitwiseOperations;
import com.sessionknip.parser.values.Parser;
import com.sessionknip.parser.values.Processor;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Visibility;
import java.util.List;

public class AppWindowListener extends AppWindowController {

    public static void addListeners(Container container) {

        btnExecute.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String text = txtExpression.getText();
                if (!text.equals("")) {

                    NamesProcessor processor = Executor.getExpressionProcessor(text);
                    List<String> names = Executor.getOperandNames(processor);
                    List<String> subExpressions = Executor.getSubExpressions(processor);
                    int width = table.getSize().width;
                    int height = table.getRowHeight();
                    Point location = table.getLocation();

//                    scrollPane.remove(table);
//                    panel.remove(table);
                    container.remove(table);

                    int rowCount = (int) (Math.pow(2, names.size()) + 1);

                    table = new JTable(rowCount, subExpressions.size());
                    table.setSize(width, height * rowCount);
                    table.setRowHeight(height);

                    table.setLocation(location);
                    table.setVisible(true);

                    for (int i = 0; i < subExpressions.size(); i++) {
                        TableColumn column = table.getColumnModel().getColumn(i);
                        column.setWidth(subExpressions.get(i).replaceAll(" ", "").length() * 12);
                        table.setValueAt(subExpressions.get(i), 0, i);
                    }


                    int size = names.size();
                    List<char[]> values = Executor.generateOperandValues(size);

                    for (int value = 0; value < values.size(); value++) {

                        for (int expr = 0; expr < subExpressions.size(); expr++) {
                            String replacedExpression = (String) table.getValueAt(0, expr);
                            for (int ch = 0; ch < values.get(value).length; ch++) {
                                replacedExpression = replacedExpression.replaceAll(names.get(ch), String.valueOf(values.get(value)[ch]));
                            }
                            OperandExpression expression = new Processor(new Parser(replacedExpression).getTokens()).parse();

                            table.setValueAt(expression.getValue() == true ? 1 : 0, value + 1, expr);
                        }
                    }

//                    container.add(table);
//                    scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//                    container.add(scrollPane);
                    container.add(table);

//                    container.add(panel);
                    container.repaint();

                } else {
                    JOptionPane.showMessageDialog(JFrame.getFrames()[0], "Wrong String Format");
                    return;
                }
            }
        });


        btnGenerate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = generates.getSelectedIndex();
                String expression = String.format(additionalOperations[index].getExpression(), op1.getText(), op2.getText());

                txtExpression.setText(txtExpression.getText() + expression);

            }
        });


        txtExpression.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typed = e.getKeyChar();

                if (!(Character.isDigit(typed) || Character.isLetter(typed) || (BitwiseOperations.getOpChars().indexOf(typed) != -1)
                        || (typed == KeyEvent.VK_BACK_SPACE) || (typed == KeyEvent.VK_DELETE) || (typed == KeyEvent.VK_SPACE))) {

                    KeyEvent event = new KeyEvent(txtExpression, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                            KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);

                    String toolTipText = txtExpression.getToolTipText();
                    txtExpression.setToolTipText("<html><p>Wrong format. <p>See \"help\" to get instructions</html>");
                    txtExpression.dispatchEvent(event);
                    txtExpression.setToolTipText(toolTipText);
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

}
