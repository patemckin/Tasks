/**
 * Created by admin on 03/10/15.
 */
package ru.spbstu.appmath.bazaliy;

public class Point {
    private static int x,y;

    public Point(int _x, int _y)
    {
        x = _x;
        y = _y;
    }
    public Point(){
        x = 0;
        y = 0;
    }
    public static int getX(){
        return x;
    }

    public static int getY(){
        return y;
    }

    public static void setX(int _x){
        x = _x;
    }

    public static void setY(int _y){
        y = _y;
    }
}


