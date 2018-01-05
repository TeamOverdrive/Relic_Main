package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Thread.sleep;

/**
 * Subsystem for claw/hand thingy
 * Created by joshua9889 on 12/10/2017.
 *
 * edited by David Zheng
 */

public class Hand implements Subsystem {
    private Servo grabf1, grabf2 = null;
    private Servo grabb1, grabb2 = null;

    // Positions for servos

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {


        grabf1 = linearOpMode.hardwareMap.servo.get("grab_front1");
        grabf2 = linearOpMode.hardwareMap.servo.get("grab_front2");
        grabb1 = linearOpMode.hardwareMap.servo.get("grab_back1");
        grabb2 = linearOpMode.hardwareMap.servo.get("grab_back2");

        stop();
    }

    @Override
    public void zeroSensors() {}

    @Override
    public void stop() {

        grabf1.setPosition(0.7);
        grabf2.setPosition(0.7);
        grabb1.setPosition(0.7);
        grabb2.setPosition(0.7);

        /*

        */

    }

    public void clawTest() throws InterruptedException {

        grabf1.setPosition(1.0);
        sleep(3000);
        grabf1.setPosition(0.75);
        sleep(3000);
        grabf1.setPosition(0.5);
        sleep(3000);
        grabf1.setPosition(0.25);
        sleep(3000);
        grabf1.setPosition(0);
        sleep(3000);

        grabf2.setPosition(1.0);
        sleep(3000);
        grabf2.setPosition(0.75);
        sleep(3000);
        grabf2.setPosition(0.5);
        sleep(3000);
        grabf2.setPosition(0.25);
        sleep(3000);
        grabf2.setPosition(0);
        sleep(3000);

        grabb1.setPosition(1.0);
        sleep(3000);
        grabb1.setPosition(0.75);
        sleep(3000);
        grabb1.setPosition(0.5);
        sleep(3000);
        grabb1.setPosition(0.25);
        sleep(3000);
        grabb1.setPosition(0);
        sleep(3000);

        grabb2.setPosition(1.0);
        sleep(3000);
        grabb2.setPosition(0.75);
        sleep(3000);
        grabb2.setPosition(0.5);
        sleep(3000);
        grabb2.setPosition(0.25);
        sleep(3000);
        grabb2.setPosition(0);
        sleep(3000);

    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Grab Front 1", grabf1.getPosition());
        telemetry.addData("Grab Front 2", grabf2.getPosition());
        telemetry.addData("Grab Back 1", grabb1.getPosition());
        telemetry.addData("Grab Back 2", grabb2.getPosition());
    }

    public void frontOpen(){

        grabf1.setPosition(0.1);
        grabf2.setPosition(0.9);

    }

    public void frontClose(){
        stop();
    }

    public void backOpen(){

        grabb1.setPosition(0.9);
        grabb2.setPosition(0.1);

    }

    public void backClose(){
        stop();
    }
}
