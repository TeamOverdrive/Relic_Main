package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static java.lang.Thread.sleep;

/**
 * Subsystem for grabbers
 *
 * Created by joshua9889 on 12/10/2017.
 */

public class Hand implements Subsystem {
    private Servo grabf1, grabf2 = null;
    private Servo grabb1, grabb2 = null;

    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {

        grabf1 = linearOpMode.hardwareMap.servo.get("grab_front1");
        grabf2 = linearOpMode.hardwareMap.servo.get("grab_front2");
        grabb1 = linearOpMode.hardwareMap.servo.get("grab_back1");
        grabb2 = linearOpMode.hardwareMap.servo.get("grab_back2");

        stop();
    }

    @Override
    public void zeroSensors() {
    }

    @Override
    public void stop() {

        grabFrontStop();
        grabBackStop();

    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Grab Front 1", grabf1.getPosition());
        telemetry.addData("Grab Front 2", grabf2.getPosition());
        telemetry.addData("Grab Back 1", grabb1.getPosition());
        telemetry.addData("Grab Back 2", grabb2.getPosition());
    }


    //Front Grabber Positions
    public void grabFrontStop(){
        grabf1.setPosition(0.73);
        grabf2.setPosition(0.2);
    }

    public void grabFrontOpen(){
        grabf1.setPosition(0.5);
        grabf2.setPosition(0.45);
    }

    public void grabFrontClose(){
        grabf1.setPosition(0.18);
        grabf2.setPosition(0.77);
    }


    //Back Grabber Positions
    public void grabBackStop(){
        grabb1.setPosition(0.07);
        grabb2.setPosition(0.73);
    }

    public void grabBackOpen(){
        grabb1.setPosition(0.3);
        grabb2.setPosition(0.4);
    }

    public void grabBackClose(){
        grabb1.setPosition(0.63);
        grabb2.setPosition(0.12);
    }
}
