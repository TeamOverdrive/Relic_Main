package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static java.lang.Boolean.TRUE;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurnTimeoutS;

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
    protected ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime matchTimer = new ElapsedTime(); // Match Timer thing
    private boolean isAuton = false; // Are we running auto
    //public static final double jewelTurn = 30;

    public void waitForStart(LinearOpMode linearOpMode, boolean auton) {
        getDrive().init(linearOpMode, auton);
        getJewel().init(linearOpMode, auton);
        getHand().init(linearOpMode, auton);
        getLift().init(linearOpMode, auton);

        if(auton) {
            AutoTransitioner.transitionOnStop(linearOpMode, "Teleop"); //Auto Transitioning

            this.isAuton = auton;

            //startVuforia();
            this.vumark.setup(BACK);

            linearOpMode.telemetry.addData("Waiting for Start", "");
            //linearOpMode.telemetry.addData("Vuforia Target", "%s visible", vumark.getOuputVuMark());
            linearOpMode.telemetry.update();
        }

        linearOpMode.waitForStart();
        //firstGrab();

        matchTimer.reset();
    }

    public RelicRecoveryVuMark columnVote(LinearOpMode linearOpMode, double timeoutS){
        int leftVotes = 0;
        int centerVotes = 0;
        int rightVotes = 0;
        runtime.reset();
        while(linearOpMode.opModeIsActive()
                &&  leftVotes < 200  &&  centerVotes < 200 && rightVotes < 200
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
            //sleep(5     );
        }
        if(leftVotes == 200)
            return RelicRecoveryVuMark.LEFT;
        else if (centerVotes == 200)
            return RelicRecoveryVuMark.CENTER;
        else if(rightVotes ==200)
            return RelicRecoveryVuMark.RIGHT;
        else
            return RelicRecoveryVuMark.UNKNOWN;
    }

    public void jewelRed(){

        switch (getJewel().vote(this, 5)) {
            case RED:
                //getDrive().encoderDrive(0.4, -5, -5, 5);
                //rotate clockwise
                getDrive().turnCW(jewelTurn, jewelTurnTimeoutS);


                getJewel().retract(); // Retract Jewel arm
                sleep(750);

                //rotate counter-clockwise
                getDrive().turnCCW(jewelTurn, jewelTurnTimeoutS);
                break;
            case BLUE:
                //getDrive().encoderDrive(0.4, 5, 5, 5);
                //rotate counter-clockwise
                getDrive().turnCCW(jewelTurn, jewelTurnTimeoutS);

                getJewel().retract(); // Retract Jewel arm
                sleep(750);

                //rotate clockwise
                getDrive().turnCW(jewelTurn, jewelTurnTimeoutS);
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

    public void jewelBlue(){
        switch (getJewel().vote(this, 5)) {
            case RED:
                //getDrive().encoderDrive(0.2, -5, -5, 5);
                //rotate counter-clockwise
                getDrive().turnCCW(jewelTurn, jewelTurnTimeoutS);

                getJewel().retract(); // Retract Jewel arm
                sleep(500);

                //rotate clockwise
                getDrive().turnCW(jewelTurn, jewelTurnTimeoutS);
                break;
            case BLUE:
                //getDrive().encoderDrive(0.2, 5, 5, 5);
                //rotate clockwise
                getDrive().turnCW(jewelTurn, jewelTurnTimeoutS);

                getJewel().retract(); // Retract Jewel arm
                sleep(500);

                //rotate counter-clockwise
                getDrive().turnCCW(jewelTurn, jewelTurnTimeoutS);
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

    public void testGlyph(){
        switch (columnVote(this, 7)){
            case LEFT:
                telemetry.addData("Column", "Left");
                telemetry.update();

                //put glyph into left column
                break;
            case CENTER:
                telemetry.addData("Column", "Center");
                telemetry.update();

                getDrive().turnCCW(90, 5);
                //sleep(5000);

                //put glyph into center column
                break;
            case RIGHT:
                telemetry.addData("Column", "Right");
                telemetry.update();

                getDrive().turnCW(180, 5);
                //sleep(5000);

                //put glyph into right column
                break;
            case UNKNOWN:
                telemetry.addData("Column", "Unknown");
                telemetry.update();
                //sleep(5000);

                //put glyph into center column
                break;
        }
    }

    public void glyphScoreR1(){

        switch (columnVote(this, 7)){

            case LEFT:
                telemetry.addData("Column", "Left");
                telemetry.update();

                getDrive().encoderDrive(0.7, 42, 42, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);

                //put glyph into left column
                break;

            case CENTER:
                telemetry.addData("Column", "Center");
                telemetry.update();

                getDrive().encoderDrive(0.7, 36, 36, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);
                //sleep(5000);

                //put glyph into center column
                break;

            case RIGHT:
                telemetry.addData("Column", "Right");
                telemetry.update();

                getDrive().encoderDrive(0.7, 30, 30, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);

                //sleep(5000);

                //put glyph into right column
                break;
            case UNKNOWN:
                telemetry.addData("Column", "Unknown");
                telemetry.update();

                getDrive().encoderDrive(0.7, 36, 36, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);

                //sleep(5000);

                //put glyph into center column
                break;
        }
    }

    public void glyphScoreB1(){

        switch (columnVote(this, 7)){

            case LEFT:
                telemetry.addData("Column", "Left");
                telemetry.update();

                getDrive().encoderDrive(0.7, -42, -42, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);

                //put glyph into left column
                break;

            case CENTER:
                telemetry.addData("Column", "Center");
                telemetry.update();

                getDrive().encoderDrive(0.7, -36, -36, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);
                //sleep(5000);

                //put glyph into center column
                break;

            case RIGHT:
                telemetry.addData("Column", "Right");
                telemetry.update();

                getDrive().encoderDrive(0.7, -30, -30, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);

                //sleep(5000);

                //put glyph into right column
                break;
            case UNKNOWN:
                telemetry.addData("Column", "Unknown");
                telemetry.update();

                getDrive().encoderDrive(0.7, -36, -36, 4);
                getDrive().turnCW(90, 4);
                getDrive().encoderDrive(0.5, 20, 20, 4);

                //sleep(5000);

                //put glyph into center column
                break;
        }
    }


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
            getLift().stop();
           //this.vumark.disableVuforia();


            requestOpModeStop();

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


