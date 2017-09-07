/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogkiller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javax.swing.JPanel;

/**
 *
 * @author dprokopiuk
 */
public class GamePanel extends JPanel implements KeyListener {

    private Timer t;
    private Frog frog;
    private Bullet bullet;
    private List<Victim> victimList;
    private static final int FROG_WIDTH = 15;
    private static final int FROG_HEIGHT = 15;
    private static final int VICITIM_WIDTH = 15;
    private static final int VICITIM_HEIGHT = 15;
    public static final int AMOUNT_OF_VICTIMS = 3;
    public static final int PANEL_WIDTH = 350;
    public static final int PANEL_HEIGHT = 350;
    public static final int BULLET_RADIUS = 10;
    public static final int BULLET_SPEED = 1;

    public GamePanel() {
        init();
        t.scheduleAtFixedRate(frog, 0, Frog.DELAY);
        for (Victim item : victimList) {
            t.scheduleAtFixedRate(item, 0, Victim.DELAY);
        }
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setFocusable(true);
        this.addKeyListener(this);
        //Toolkit.getDefaultToolkit().
    }

    private void init() {
        t = new Timer();
        bullet = new Bullet(BULLET_RADIUS, BULLET_SPEED);
        frog = new Frog(this, FROG_WIDTH, FROG_HEIGHT, bullet);
        victimList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Victim v = new Victim(this, VICITIM_WIDTH, VICITIM_HEIGHT, i);
            victimList.add(v);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        frog.getX();
        g.fillRect(frog.getX(), frog.getY(), frog.getWidth(), frog.getHeight());
        for (Victim victim : victimList) {
            g.fillRect(victim.getX(), victim.getY(), victim.getWidth(), victim.getHeight());
        }
        if (!bullet.isFinish()) {
            g.setColor(Color.red);
            bullet.setY(bullet.getY() + bullet.getSpeed());
            g.fillOval(bullet.getX(), bullet.getY(), bullet.getRadius(), bullet.getRadius());
            g.setColor(Color.black);
            if ((bullet.getY() + bullet.getRadius()) >= getHeight()) {
                bullet.setFinish(true);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            frog.fire();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
