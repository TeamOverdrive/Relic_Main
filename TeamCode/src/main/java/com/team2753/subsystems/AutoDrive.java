package com.team2753.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.team2753.Constants;
import com.team2753.controllers.SynchronousPIDF;

/**
 * Created by joshua9889 on 5/19/2018.
 */

public class AutoDrive extends Drive {
    public AutoDrive(double max_speed){
        this.max_speed = max_speed;
    }

    public AutoDrive(){}

    private double max_speed=1.0;

    public void configureForSpeedControl(){
        max_speed = 0.7;
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void setLeftRightPower(double left, double right) {
        double right_adjusted = Math.copySign(Math.min(Math.max(Math.abs(right), 0), max_speed), right);
        double left_adjusted = Math.copySign(Math.min(Math.max(Math.abs(left), 0), max_speed), left);

        super.setLeftRightPower(left_adjusted, right_adjusted);
    }

    public void gotToDistance(double leftDistance, double rightDistance){
        configureForSpeedControl();

        double leftEncoderDistance = Constants.COUNTS_PER_INCH * leftDistance;
        double rightEncoderDistance = Constants.COUNTS_PER_INCH * rightDistance;

        SynchronousPIDF left = new SynchronousPIDF(10, 0, 2);
        SynchronousPIDF right = new SynchronousPIDF(10, 0, 2);
        left.setOutputRange(-1, 1);
        right.setOutputRange(-1, 1);
        left.setSetpoint(leftEncoderDistance);
        right.setSetpoint(rightEncoderDistance);

        double lastTime = System.nanoTime();

        while (Math.abs(left.getError())>3){
            double dt = System.nanoTime() - lastTime;
            setLeftRightPower(left.calculate(getLeftCurrentPosition(), dt),
                    right.calculate(getRightCurrentPosition(), dt));

            lastTime = System.nanoTime();
        }
    }
}
