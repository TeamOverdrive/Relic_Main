package com.team2753.path;

/**
 * Created by joshua9889 on 5/19/2018.
 */

public class Waypoint {

    public double x,y,theda;

    public Waypoint(double x, double y, double theda){
        this.set(x,y,theda);
    }

    public void set(double x, double y, double theda){
        this.x=x;
        this.y=y;
        this.theda=theda;
    }
}
