package com.team2753.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/25/2018.
 */

public class Slammer implements Subsystem{

    private DcMotor slamMotor = null;
    private Servo stopServo = null;

    private static final double ARMUP = 0.35;
    private static final double ARMDOWN = 0.95;

    private int initFollowerPosition = 0;

    protected ElapsedTime runtime = new ElapsedTime();// FORWARD_SPEED was running the robot in reverse to the TeleOp program setup.  Speed is reversed to standardize the robot orientation.

    private static final double COUNTS_PER_MOTOR_REV = 1680; //AndyMark NeveRest 60


    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        slamMotor = linearOpMode.hardwareMap.dcMotor.get("slammer");
        slamMotor.setDirection(FORWARD);
        setRunMode(RUN_WITHOUT_ENCODER);

        initFollowerPosition = slamMotor.getCurrentPosition();

        stopServo = linearOpMode.hardwareMap.servo.get("slammer_stop");

        stop();
        if(auto)
            stopperDown();
        else
            stopperUp();
    }

    @Override
    public void zeroSensors() {
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        initFollowerPosition = followerWheel();
        Thread.yield();
    }

    @Override
    public void stop() {
        slamMotor.setZeroPowerBehavior(BRAKE);
        setPower(0);
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {}

    public void setRunMode(DcMotor.RunMode runMode){slamMotor.setMode(runMode);}

    public void setPower(double power){slamMotor.setPower(power);}

    public void autoSlam(){
        stopperUp();
        waitForTick(350);
        setPower(0.6);
        waitForTick(1200);
        stop();
    }

    public void stopperDown(){stopServo.setPosition(ARMDOWN);}

    public void stopperUp(){stopServo.setPosition(ARMUP);}

    public int followerWheel(){
        return slamMotor.getCurrentPosition()-initFollowerPosition;
    }

    public void resetFollowerWheel(){
        initFollowerPosition = slamMotor.getCurrentPosition();
    }


    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)runtime.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        runtime.reset();
    }

    @Override
    public String toString() {
        return "Slammer";
    }
}
