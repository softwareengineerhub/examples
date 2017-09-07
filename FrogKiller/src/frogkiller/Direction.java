/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frogkiller;

/**
 *
 * @author dprokopiuk
 */
public enum Direction {

    S(1), N(-1), E(1), W(-1), NO_Y(0);
    private int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
