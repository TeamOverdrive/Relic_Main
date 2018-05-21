package com.team2753.path;

/**
 * Created by heinr_000 on 5/19/2018.
 */

public class Trajectory {
    Segment[] segments = null;
    boolean invertY = false;

    public Trajectory(int length){
        segments = new Segment[length];
        for (int i = 0; i < length; i++) {
            segments[i] = new Segment();
        }
    }

    public Trajectory(Segment[] segments){
        this.segments = segments;
    }

    public void setInvertedY(boolean inverted){
        this.invertY = inverted;
    }

    
}
