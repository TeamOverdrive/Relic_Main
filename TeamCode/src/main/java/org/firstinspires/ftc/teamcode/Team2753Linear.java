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
    public static final double jewelTurn = 30;


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


    protected ElapsedTime runtime = new ElapsedTime();
    public RelicRecoveryVuMark columnVote(LinearOpMode linearOpMode, double timeoutS){
        int leftVotes = 0;
        int centerVotes = 0;
        int rightVotes = 0;
        while(linearOpMode.opModeIsActive()
                &&  leftVotes < 300  &&  centerVotes < 300 && rightVotes < 300
                && runtime.seconds() < timeoutS){
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
            linearOpMode.telemetry.addData("Left Votes", leftVotes);
            linearOpMode.telemetry.addData("Center Votes", centerVotes);
            linearOpMode.telemetry.addData("Right Votes", rightVotes);
            linearOpMode.telemetry.update();
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
            getHand().closeSide();
            sleep(300);
            getHand().closeBottom();
            getLift().setLiftPower(0.8);
            sleep(1000);
    }

    public void jewelBlue(){
        switch (getJewel().vote(this, 10)) {
            case RED:
                //getDrive().encoderDrive(0.2, -5, -5, 5);
                //rotate counter-clockwise
                getDrive().turnCCW(30, 4);

                getJewel().retract(); // Retract Jewel arm
                sleep(500);

                //rotate clockwise
                getDrive().turnCW(30, 4);
                break;
            case BLUE:
                //getDrive().encoderDrive(0.2, 5, 5, 5);
                //rotate clockwise
                getDrive().turnCW(30, 4);

                getJewel().retract(); // Retract Jewel arm
                sleep(500);

                //rotate counter-clockwise
                getDrive().turnCCW(30, 4);
                break;
            case UNKNOWN:
                getJewel().retract(); // Retract Jewel arm
                sleep(750);
                break;
            default:
                getJewel().retract(); // Retract Jewel arm
                sleep(500);
        }
    }

    public void jewelRed(){

        switch (getJewel().vote(this, 10)) {
            case RED:
                //getDrive().encoderDrive(0.4, -5, -5, 5);
                //rotate clockwise
                getDrive().turnCW(30, 4);


                getJewel().retract(); // Retract Jewel arm
                sleep(750);

                //rotate counter-clockwise
                getDrive().turnCCW(30, 4);
                break;
            case BLUE:
                //getDrive().encoderDrive(0.4, 5, 5, 5);
                //rotate counter-clockwise
                getDrive().turnCCW(30, 4);

                getJewel().retract(); // Retract Jewel arm
                sleep(750);

                //rotate clockwise
                break;
            case UNKNOWN:
                getJewel().retract(); // Retract Jewel arm
                sleep(750);
                break;
            default:
                getJewel().retract(); // Retract Jewel arm
                sleep(750);
        }
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
            //vumark.disableVuforia();


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


