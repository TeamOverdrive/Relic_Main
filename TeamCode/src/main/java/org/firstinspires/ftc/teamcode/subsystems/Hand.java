package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Subsystem for claw/hand thingy
 * Created by joshua9889 on 12/10/2017.
 */

public class Hand implements Subsystem {
    private Servo claw1, claw2 = null;
    private Servo grabBottom, grabTop = null;

    // Positions for servos
    private final static double CLAWOPEN = 0.4;
    private final static double CLAWCLOSED = 0.0;
    private final static double GRABCLOSE = 1.0;


    @Override
    public void init(LinearOpMode linearOpMode, boolean auto) {
        claw1 = linearOpMode.hardwareMap.servo.get("grab1");
        claw2 = linearOpMode.hardwareMap.servo.get("grab2");
        claw2.setDirection(Servo.Direction.REVERSE);

        grabBottom = linearOpMode.hardwareMap.servo.get("grab_bottom");
        grabTop = linearOpMode.hardwareMap.servo.get("grab_top");
        stop();
    }

    @Override
    public void zeroSensors() {}

    @Override
    public void stop() {
        // Just move it a little farther back then normal
        claw1.setPosition(0.5);
        claw2.setPosition(0.5);
    }

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Grab1", claw1.getPosition());
        telemetry.addData("Grab2", claw2.getPosition());
        telemetry.addData("GrabTop", grabTop.getPosition());
        telemetry.addData("GrabBottom", grabBottom.getPosition());
    }

    // Open the hand
    public void openClaw(){
        claw1.setPosition(CLAWOPEN);
        claw2.setPosition(CLAWOPEN);
        grabBottom.setPosition(0.66);
        grabTop.setPosition(0.5);
    }

    // Close the hand
    public void closeClaw(){
        claw1.setPosition(CLAWCLOSED);
        claw2.setPosition(CLAWCLOSED + 0.05);
    }

    public void closeTop(){
        closeClaw();
        grabTop.setPosition(GRABCLOSE);
        grabBottom.setPosition(0.66);
    }

    public void closeBottom() {
        closeClaw();
        grabTop.setPosition(0.5);
        grabBottom.setPosition(GRABCLOSE - 0.16);
    }


}
