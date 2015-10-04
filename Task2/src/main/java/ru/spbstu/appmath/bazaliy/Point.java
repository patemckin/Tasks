/**
 * Created by admin on 03/10/15.
 */
package ru.spbstu.appmath.bazaliy;

public class Point {
    private static int x, y;

    public Point(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public Point() {
        x = 0;
        y = 0;
    }

    public boolean Equal(Point p) {
        return (p.getX() == x) && (p.getY() == y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int _x) {
        x = _x;
    }

    public void setY(int _y) {
        y = _y;
    }
}


