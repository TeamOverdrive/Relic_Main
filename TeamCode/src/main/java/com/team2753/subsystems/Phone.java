package com.team2753.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * For the phone servo only lel
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/29/2018.
 */

public class Phone implements Subsystem{

    private Servo phoneServo = null;

    private static final double INITPOS = 0.77;
    private static final double PICPOS = 0.72;
    private static final double JEWELPOS = 0.95;
    private static final double CRYPTOPOS = 0.35;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        phoneServo = linearOpMode.hardwareMap.servo.get("phone_servo");
        initPosition();
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void stop() {
        initPosition();
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Phone Angle", phoneServo.getPosition());
    }

    public void setPosition(double position){phoneServo.setPosition(position);}

    public void initPosition(){
        phoneServo.setPosition(INITPOS);
    }

    public void picturePosition(){phoneServo.setPosition(PICPOS);}

    public void jewelPosition(){
        phoneServo.setPosition(JEWELPOS);
    }

    public void cryptoPosition(){phoneServo.setPosition(CRYPTOPOS);}

    @Override
    public String toString() {
        return "Phone";
    }
}
