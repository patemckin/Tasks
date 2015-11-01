package ru.spbstu.appmath.bazaliy;

public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public boolean equals(Object o) {
        return ((o instanceof Point) && (((Point) o).getX() == x) && (((Point) o).getY() == y));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


