/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogkiller;

/**
 *
 * @author dprokopiuk
 */
public class Frog extends Player {

    public static final int DELAY = 10;
    private Bullet bullet;

    public Frog(GamePanel panel, int width, int height, Bullet bullet) {
        super(panel, width, height);
        this.bullet = bullet;
    }

    @Override
    public void run() {
        int x = getX() + getSpeed();
        setX(x > getGamePanel().getWidth() ? -getWidth() : x);
        getGamePanel().repaint();
    }

    public void fire() {
        if (bullet.isFinish()) {
            bullet.setFinish(false);
            bullet.setX(getX() + getWidth() / 2);
            bullet.setY(getY() + getHeight());
        }
    }
}
