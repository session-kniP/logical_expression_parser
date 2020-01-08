package com.sessionknip.controls;



import javax.swing.*;
import java.awt.*;

public abstract class Sizer {



    public static void setRelativeSize(JFrame frame, Component component, int widthPerCent, int heightPerCent){

        float sizerX = frame.getWidth() / 100;
        float sizerY = frame.getHeight() / 100;

        Font font = component.getFont();

        FontMetrics fm = component.getFontMetrics(component.getFont());

        //fm.stringWidth(component.get)

        //component.setFont(new Font(font.getName(), font.getStyle(), (int)(font.getSize()*((sizerX + sizerY)/2))));

        component.setSize(new Dimension((int)(sizerX * widthPerCent), (int)(sizerY * heightPerCent)));

    }
}
