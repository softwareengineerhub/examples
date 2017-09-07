/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogkiller;

/**
 *
 * @author dprokopiuk
 */
public class Bullet {

    private int radius;
    private int x;
    private int y;
    private boolean finish = true;
    private int speed;

    public Bullet(int radius, int speed) {
        this.radius = radius;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRadius() {
        return radius;
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

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
