package com.team2753.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 2/26/2018.
 */

public class Relic implements Subsystem{

    private DcMotor slideMotor = null;
    private Servo wristServo = null;
    private Servo fingerServo = null;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        wristServo = linearOpMode.hardwareMap.get(Servo.class, "yellow");
        fingerServo = linearOpMode.hardwareMap.get(Servo.class, "blue");
        slideMotor = linearOpMode.hardwareMap.get(DcMotor.class, "slammer");

        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        if(auto)
            setWristAngle(0);
        else
            hold();
        close();
    }

    @Override
    public void zeroSensors() {}

    @Override
    public void stop() {}

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Wrist Position", wristServo.getPosition());
        telemetry.addData("Finger Position", fingerServo.getPosition());
    }

    public void open(){
        fingerServo.setPosition(0.9);
    }

    public void close(){
        fingerServo.setPosition(0);
    }

    public void lock(){
        fingerServo.setPosition(0.35);
    }

    private double oneRotation = ((1.0/2.25)/8.0);
    private double oneDegree = (oneRotation/360);

    public void setWristAngle(double angle){
        wristServo.setPosition(angle*oneDegree);
    }

    public void setWristPostion(double pos){
        wristServo.setPosition(pos/255.0);
    }

    public void setPower(double power){
        slideMotor.setPower(power);
    }

    public void hold(){
        setWristAngle(100);
    }

    @Override
    public String toString() {
        return "Relic";
    }
}
