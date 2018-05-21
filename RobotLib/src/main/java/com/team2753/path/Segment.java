package com.team2753.path;

/**
 * Created by joshua9889 on 5/19/2018.
 */

public class Segment {
    public double pos, vel, acc, jerk, dt, x, y;

    public Segment(){}

    public Segment(double pos, double vel, double acc, double jerk,
                   double dt, double x, double y){
        this.pos=pos;
        this.vel=vel;
        this.acc=acc;
        this.jerk=jerk;
        this.dt=dt;
        this.x=x;
        this.y=y;
    }

    public Segment(Segment to_copy){
        pos = to_copy.pos;
        vel = to_copy.vel;
        acc = to_copy.acc;
        jerk = to_copy.jerk;
        dt = to_copy.dt;
        x = to_copy.x;
        y = to_copy.y;
    }
}
