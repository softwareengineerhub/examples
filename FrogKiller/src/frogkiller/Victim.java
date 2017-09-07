/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogkiller;

import java.util.Random;

/**
 *
 * @author dprokopiuk
 */
public class Victim extends Player {

    public static final int DELAY = 10;
    private Direction xDirection = Direction.E;
    private Direction yDirection = Direction.S;
    private static final Random RND = new Random();
    private int number;

    public Victim(GamePanel panel, int width, int height, int number) {
        super(panel, width, height);
        this.number = number;
        setStartCordinates();
    }

    @Override
    public void run() {
        int x = getX() + getSpeed() * xDirection.getDirection();
        if ((x + getWidth()) > getGamePanel().getWidth()) {
            xDirection = Direction.W;
            x = getDrawPanel().getWidth() - getWidth();
        }
        if ((x) <= 0) {
            xDirection = Direction.E;
            x = 0;
        }
        setX(x);
        int y = getY() + getSpeed() * getRandomDirection().getDirection();
        if ((y + getHeight()) > getGamePanel().getHeight()) {
            yDirection = Direction.N;
            y = getGamePanel().getHeight() - getHeight();
        }
        if ((y + getHeight()) <= getGamePanel().getHeight() / 2) {
            yDirection = Direction.S;
            y = getGamePanel().getHeight() / 2 - getHeight();
        }
        setY(y);
        getGamePanel().repaint();
    }

    private Direction getRandomDirection() {
        switch (RND.nextInt(2)) {
            case 1:
                return Direction.NO_Y;
            default:
                return yDirection;

        }
    }

    private void setStartCordinates() {
        switch ((number + GamePanel.AMOUNT_OF_VICTIMS) % GamePanel.AMOUNT_OF_VICTIMS) {
            case 0:
                setX(GamePanel.PANEL_WIDTH / 2);
                setY(GamePanel.PANEL_HEIGHT / 2);
                break;
            case 1:
                setX(0);
                setY(GamePanel.PANEL_WIDTH - getHeight());
                break;
            default:
                setX(GamePanel.PANEL_WIDTH - getWidth());
                setY(GamePanel.PANEL_WIDTH / 2);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Victim #" + number + "[" + getX() + ";" + getY() + "]";
    }
}
