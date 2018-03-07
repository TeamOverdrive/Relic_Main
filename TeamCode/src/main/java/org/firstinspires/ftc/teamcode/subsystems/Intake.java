package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.FLOAT;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/25/2018.
 */

public class Intake implements Subsystem{

    private LinearOpMode linearOpMode = null;
    private DcMotor leftIntake, rightIntake = null;
    private Servo intakeRelease = null;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        this.linearOpMode = linearOpMode;
        leftIntake = linearOpMode.hardwareMap.dcMotor.get("intake_left");
        leftIntake.setDirection(FORWARD);
        rightIntake = linearOpMode.hardwareMap.dcMotor.get("intake_right");
        rightIntake.setDirection(REVERSE);

        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeRelease = linearOpMode.hardwareMap.servo.get("intake_servo");
        releaseLock();
        if(!auto)
            releaseIntake();
        zeroSensors();
        stop();
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void stop() {
        leftIntake.setZeroPowerBehavior(FLOAT);
        rightIntake.setZeroPowerBehavior(FLOAT);
        setPower(0);
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Left Intake Power", leftIntake.getPower());
        telemetry.addData("Right Intake Power", rightIntake.getPower());
    }

    public void setPower(double power){
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

    public void setRunMode(DcMotor.RunMode runMode){
        leftIntake.setMode(runMode);
        rightIntake.setMode(runMode);
    }

    public void intake(){setPower(1.0);}

    public void reverse(){setPower(-1.0);}

    public void shiftLeft(){
        leftIntake.setPower(1.0);
        rightIntake.setPower(0.8);
    }

    public void shiftRight(){
        rightIntake.setPower(1.0);
        leftIntake.setPower(0.8);
    }

    public void releaseLock(){intakeRelease.setPosition(0.16);}

    public void releaseIntake(){intakeRelease.setPosition(0.35);}
}
