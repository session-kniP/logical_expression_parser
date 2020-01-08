package com.sessionknip.controls;

import javax.swing.*;
import java.awt.*;

public abstract class Locator {


    public enum Directions {
        RIGHT,
        LEFT,
        UP,
        DOWN,
        DIAGONAL_RIGHT_UP,
        DIAGONAL_RIGHT_DOWN,
        DIAGONAL_LEFT_UP,
        DIAGONAL_LEFT_DOWN
    }

    public static Rectangle getRelativeBounds(JComponent restElement, Directions dir, int offset, int rectWidth, int rectHeight) {
        int x = restElement.getX();
        int y = restElement.getY();

        int width = restElement.getWidth();
        int height = restElement.getHeight();

        switch (dir) {
            case RIGHT:
                x += width + offset;
                break;

            case LEFT:
                x -= offset;
                break;

            case UP:
                y -= offset;
                break;

            case DOWN:
                y += height + offset;
                break;

            case DIAGONAL_RIGHT_UP:
                x += width + offset;
                y -= offset;
                break;

            case DIAGONAL_RIGHT_DOWN:
                x += width + offset;
                y += height + offset;
                break;

            case DIAGONAL_LEFT_UP:
                x -= offset;
                y -= offset;
                break;

            case DIAGONAL_LEFT_DOWN:
                x -= offset;
                y += height + offset;
                break;
        }

        return new Rectangle(x, y, rectWidth, rectHeight);
    }


    public static void setRelativeLocation(JFrame frame, Component restElement, Component targetElement, Directions dir, int offsetPerCent) {
        int x = restElement.getX();
        int y = restElement.getY();

        int restElWidth = restElement.getWidth();
        int restElHeight = restElement.getHeight();

        int mulX = frame.getWidth() / 100;
        int mulY = frame.getHeight() / 100;

        switch (dir) {
            case RIGHT:
                x += restElWidth + offsetPerCent * mulX;
                break;

            case LEFT:
                x -= offsetPerCent * mulX;
                break;

            case UP:
                y -= offsetPerCent * mulY;
                break;

            case DOWN:
                y += restElHeight + offsetPerCent * mulY;
                break;

            case DIAGONAL_RIGHT_UP:
                x += restElWidth + offsetPerCent * mulX;
                y -= offsetPerCent * mulY;
                break;

            case DIAGONAL_RIGHT_DOWN:
                x += restElWidth + offsetPerCent * mulX;
                y += restElHeight + offsetPerCent * mulY;
                break;

            case DIAGONAL_LEFT_UP:
                x -= offsetPerCent * mulX;
                y -= offsetPerCent * mulY;
                break;

            case DIAGONAL_LEFT_DOWN:
                x -= offsetPerCent * mulX;
                y += restElHeight + offsetPerCent * mulY;
                break;
        }

        targetElement.setLocation(x, y);
    }



    public static void setRelativeLocation(JFrame frame, Component targetElement, Directions dir, int offsetPerCent) {
        int x = 0;
        int y = 0;

        int restElWidth = 0;
        int restElHeight = 0;

        int mulX = frame.getWidth() / 100;
        int mulY = frame.getHeight() / 100;

        switch (dir) {
            case RIGHT:
                x += restElWidth + offsetPerCent * mulX;
                break;

            case LEFT:
                x -= offsetPerCent * mulX;
                break;

            case UP:
                y -= offsetPerCent * mulY;
                break;

            case DOWN:
                y += restElHeight + offsetPerCent * mulY;
                break;

            case DIAGONAL_RIGHT_UP:
                x += restElWidth + offsetPerCent * mulX;
                y -= offsetPerCent * mulY;
                break;

            case DIAGONAL_RIGHT_DOWN:
                x += restElWidth + offsetPerCent * mulX;
                y += restElHeight + offsetPerCent * mulY;
                break;

            case DIAGONAL_LEFT_UP:
                x -= offsetPerCent * mulX;
                y -= offsetPerCent * mulY;
                break;

            case DIAGONAL_LEFT_DOWN:
                x -= offsetPerCent * mulX;
                y += restElHeight + offsetPerCent * mulY;
                break;
        }

        targetElement.setLocation(x, y);
    }


    public static int getAbsoluteOffsetX(JFrame restFrame, int offsetPerCent) {
        return restFrame.getWidth() / 100 * offsetPerCent;
    }


    public static int getAbsoluteOffsetY(JFrame restFrame, int offsetPerCent) {
        return restFrame.getHeight() / 100 * offsetPerCent;
    }

}
