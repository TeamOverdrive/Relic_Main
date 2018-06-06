package com.team2753.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

/**
 * Created by joshua9889 on 6/1/2018.
 */

public class FollowerWheel implements Subsystem {

    private DcMotor followerWheel = null;
    private int initFollowerPosition = 0;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        followerWheel = linearOpMode.hardwareMap.get(DcMotor.class, "slammer");
        resetFollowerWheel();
    }

    @Override
    public void zeroSensors() {
        resetFollowerWheel();
    }

    @Override
    public void stop() {}

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Follower Position", getTicks());
    }

    public int getTicks(){
        return followerWheel.getCurrentPosition()-initFollowerPosition;
    }

    public double getInches(){
        return getTicks()/307.53;
    }

    public void resetFollowerWheel(){
        followerWheel.setDirection(FORWARD);
        followerWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        initFollowerPosition = followerWheel.getCurrentPosition();
    }
}
