package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelVotes;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public class Jewel implements Subsystem {

    private Servo arm, wrist;

    final private static double ARMUP = 0.94;
    final private static double ARMDOWN = 0.25;

    final private static double CenterJewelWrist = 0.52;
    final private static double LeftJewelWrist = 0.0;
    final private static double RightJewelWrist = 1.0;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        arm = linearOpMode.hardwareMap.get(Servo.class, "jewel_arm");
        wrist = linearOpMode.hardwareMap.get(Servo.class, "TBD");

        if(auto)
            retract();
    }

    @Override
    public void zeroSensors() {

    }

    @Override
    public void stop() {
        retract();
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Jewel Arm Position", arm.getPosition());
        telemetry.addData("Jewel Wrist Position", wrist.getPosition());
    }

    // Deploy jewel mech
    public void deploy(){
        arm.setPosition(ARMDOWN);
        wrist.setPosition(CenterJewelWrist);
    }

    // Retract jewel mech
    public void retract(){
        arm.setPosition(ARMUP);
        wrist.setPosition(CenterJewelWrist);
    }

    public void left(){
        wrist.setPosition(LeftJewelWrist);
    }

    public void right(){
        wrist.setPosition(RightJewelWrist);
    }

    public void leftHit(){
        wrist.setPosition((CenterJewelWrist+LeftJewelWrist)/2);
    }

    public void rightHit(){
        wrist.setPosition((CenterJewelWrist+RightJewelWrist)/2);
    }
}
