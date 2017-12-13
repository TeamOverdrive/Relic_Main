package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This class extends linearopmode and makes it
 * easier to make code for the robot and not copy
 * and pasting init code.
 *
 * See this for an example: http://bit.ly/2B8scLB
 * Created by joshua9889 on 12/10/2017.
 */

public abstract class Team2753Linear extends LinearOpMode {
    private org.firstinspires.ftc.teamcode.subsystems.Drive Drive = new org.firstinspires.ftc.teamcode.subsystems.Drive(); // Drivetrain
    private org.firstinspires.ftc.teamcode.subsystems.Jewel Jewel = new org.firstinspires.ftc.teamcode.subsystems.Jewel(); // Jewel mech
    private org.firstinspires.ftc.teamcode.subsystems.Hand Hand = new org.firstinspires.ftc.teamcode.subsystems.Hand(); // Claw for glyphs and things
    private org.firstinspires.ftc.teamcode.subsystems.Lift Lift = new org.firstinspires.ftc.teamcode.subsystems.Lift();
    private ElapsedTime matchTimer = new ElapsedTime(); // Match Timer thing
    private boolean isAuton = false; // Are we running auto

    public void waitForStart(LinearOpMode linearOpMode, boolean auton) {
        getDrive().init(linearOpMode, auton);
        getJewel().init(linearOpMode, auton);
        getHand().init(linearOpMode, auton);
        getLift().init(linearOpMode, auton);

        // You can add vuforia code here

        if(auton)
            AutoTransitioner.transitionOnStop(linearOpMode, "Teleop"); //Auto Transitioning

        this.isAuton = auton;

        linearOpMode.telemetry.addData("Waiting for Start","");
        linearOpMode.telemetry.update();

        linearOpMode.waitForStart();

        matchTimer.reset();
    }

    // Should work
    public void updateTelemetry(LinearOpMode linearOpMode) {
        if (!isAuton)
            linearOpMode.telemetry.addData("Match Time", 120-matchTimer.seconds());
        getDrive().outputToTelemetry(linearOpMode.telemetry);
        getJewel().outputToTelemetry(linearOpMode.telemetry);
        getHand().outputToTelemetry(linearOpMode.telemetry);
        linearOpMode.telemetry.update();
    }

    public void finalAction(){
        getDrive().stop();
        getJewel().stop();
        getHand().stop();
        //requestOpModeStop();
    }

    public org.firstinspires.ftc.teamcode.subsystems.Drive getDrive() {
        return Drive;
    }

    public org.firstinspires.ftc.teamcode.subsystems.Jewel getJewel() {
        return Jewel;
    }

    public org.firstinspires.ftc.teamcode.subsystems.Hand getHand() {
        return Hand;
    }

    public org.firstinspires.ftc.teamcode.subsystems.Lift getLift () {return Lift;}
}


