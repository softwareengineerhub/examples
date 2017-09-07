/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogkiller;

import java.util.TimerTask;

/**
 *
 * @author dprokopiuk
 */
public abstract class Player extends TimerTask {

    private final GamePanel panel;
    private int x;
    private int y = 20;
    private int speed = 1;
    private int width;
    private int height;

    public Player() {
        panel = null;
    }

    public Player(GamePanel panel, int width, int height) {
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    public GamePanel getGamePanel() {
        return panel;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GamePanel getDrawPanel() {
        return panel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
