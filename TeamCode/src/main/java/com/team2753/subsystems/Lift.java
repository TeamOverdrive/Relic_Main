package com.team2753.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public class Lift implements Subsystem {

    private DcMotor liftMotor = null;
    private static final double brakepower = 0;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        RobotLog.v("============= Lift Init Started =============");
        liftMotor = linearOpMode.hardwareMap.get(DcMotor.class, "lift_motor");

        liftMotor.setDirection(REVERSE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RobotLog.v("============= Lift Init Finished =============");
    }

    @Override
    public void zeroSensors() {
        stop();
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void stop() {
        brakeLift();
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Intake Power", liftMotor.getPower());
        telemetry.addData("Current Lift Position", liftMotor.getCurrentPosition());
    }

    public void setRunMode(DcMotor.RunMode runMode){
        liftMotor.setMode(runMode);
    }


    public void setLiftPower(double power){
        if(liftMotor.getMode()!= DcMotor.RunMode.RUN_WITHOUT_ENCODER)
            setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setPower(power);
    }

    public void brakeLift(){
        liftMotor.setPower(brakepower);
    }

    public boolean shouldLiftStop(){
        return getPosition()>400;
    }

    public int getPosition(){
        return liftMotor.getCurrentPosition();
    }

    @Override
    public String toString() {
        return "Lift";
    }
}
