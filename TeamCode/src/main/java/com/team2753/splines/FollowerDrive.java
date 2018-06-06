package com.team2753.splines;

import com.team2753.subsystems.Drive;
import com.team2753.subsystems.FollowerWheel;

/**
 * Created by joshua9889 on 6/1/2018.
 */

public class FollowerDrive {

    public FollowerDrive(Drive drive, FollowerWheel followerWheel){
        mDrive = drive;
        mFollowerWheel = followerWheel;
    }

    private FollowerWheel mFollowerWheel = null;
    private Drive mDrive = null;

    private double x, y = 0;
    private double offsetX, offsetY = 0;
    private double lastFollowerPosition = 0;
    private double lastAngle = 0;
    private double angleOfChange, radius;

    public void setZero(){
        offsetX = x;
        offsetY = y;
    }

    public void update(){
        double currentFollower = mFollowerWheel.getInches();
        double changeInCenter = currentFollower - lastFollowerPosition;

        if(Math.abs(changeInCenter)>0.01){
            changeInCenter = currentFollower - lastFollowerPosition;

            radius = changeInCenter;
            angleOfChange = mDrive.getGyroAngleRadians();

            double changeInX = radius * Math.cos(angleOfChange+lastAngle);
            double changeInY = radius * Math.sin(angleOfChange+lastAngle);

            x += changeInX;
            y += changeInY;

            lastFollowerPosition = currentFollower;
            lastAngle = angleOfChange;
        }
    }


    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    public double radius(){
        return radius;
    }

    public double angle(){
        return angleOfChange + lastAngle;
    }
}
