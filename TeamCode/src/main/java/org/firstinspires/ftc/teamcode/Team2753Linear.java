package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static java.lang.Boolean.TRUE;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;

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
    private VuMark vumark = new VuMark();
    private ElapsedTime matchTimer = new ElapsedTime(); // Match Timer thing
    private boolean isAuton = false; // Are we running auto


    public void startVuforia(){
            this.vumark.setup(BACK, TRUE);
            //this.vumark.updateTarget();
            //this.telemetry.addData("Vuforia Target", "%s visible", vumark.getOuputVuMark());
            //this.telemetry.update();
    }

    public RelicRecoveryVuMark glyphColumn(){
        RelicRecoveryVuMark currentVumark = vumark.getOuputVuMark();
        return currentVumark;
    }

    public RelicRecoveryVuMark columnVote(){
        int leftVotes = 0;
        int centerVotes = 0;
        int rightVotes = 0;
        while(opModeIsActive()  &&  leftVotes < 300  &&  centerVotes < 300 && rightVotes < 300){
            switch (vumark.targetColumn()){
                case LEFT:
                    leftVotes++;
                    break;
                case CENTER:
                    centerVotes++;
                    break;
                case RIGHT:
                    rightVotes++;
                    break;
            }
            telemetry.addData("Left Votes", leftVotes);
            telemetry.addData("Center Votes", centerVotes);
            telemetry.addData("Right Votes", rightVotes);
            telemetry.update();
        }
        if(leftVotes == 300)
            return RelicRecoveryVuMark.LEFT;
        else if (centerVotes == 300)
            return RelicRecoveryVuMark.CENTER;
        else if(rightVotes ==300)
            return RelicRecoveryVuMark.RIGHT;
        else
            return RelicRecoveryVuMark.UNKNOWN;
    }

    public void waitForStart(LinearOpMode linearOpMode, boolean auton) {
        getDrive().init(linearOpMode, auton);
        getJewel().init(linearOpMode, auton);
        getHand().init(linearOpMode, auton);
        getLift().init(linearOpMode, auton);

        if(auton)
            AutoTransitioner.transitionOnStop(linearOpMode, "Teleop"); //Auto Transitioning

            this.isAuton = auton;

            //startVuforia();

            linearOpMode.telemetry.addData("Waiting for Start", "");
            //linearOpMode.telemetry.addData("Vuforia Target", "%s visible", vumark.getOuputVuMark());
            linearOpMode.telemetry.update();

            linearOpMode.waitForStart();
            //firstGrab();

            matchTimer.reset();
    }

    public void glyphLoad(){
            getHand().closeBottom();
            sleep(300);
            getLift().setLiftPower(1.0);
            sleep(500);
            getLift().brake();
    }

    // Should work
    public void updateTelemetry(LinearOpMode linearOpMode) {
            if (!isAuton)
                linearOpMode.telemetry.addData("Match Time", 120 - matchTimer.seconds());
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


