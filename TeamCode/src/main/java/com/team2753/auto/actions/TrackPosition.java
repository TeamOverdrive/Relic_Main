package com.team2753.auto.actions;

import com.team2753.subsystems.Robot;

/**
 * Created by joshua9889 on 4/19/2018.
 */

public class TrackPosition implements Action {

    private double currentVectorDistance = 0;
    private double lastVectorDistance = 0;
    private double currentVectorAngle = 0;

    private double lastVectorX = 0;
    private double lastVectorY = 0;
    public double currentVectorX = 0;
    public double currentVectorY = 0;

    public boolean finished = false;

    private Robot Robot = com.team2753.subsystems.Robot.getInstance();

    public TrackPosition(){}

    @Override
    public void start() {

    }

    @Override
    public void update() {
        currentVectorDistance = Robot.getSlammer().followerWheel();
        currentVectorAngle = Robot.getDrive().getGyroAngleDegrees();

        double nextVectorX = currentVectorDistance * Math.cos(Math.toRadians(currentVectorAngle));
        double nextVectorY = currentVectorDistance * Math.sin(Math.toRadians(currentVectorAngle));

        currentVectorX = nextVectorX + lastVectorX;
        currentVectorY = nextVectorY + lastVectorY;

        lastVectorX = currentVectorX;
        lastVectorY = currentVectorY;
        Robot.getSlammer().resetFollowerWheel();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void done() {

    }

    public void stopTracking(){
        finished = true;
    }
}
