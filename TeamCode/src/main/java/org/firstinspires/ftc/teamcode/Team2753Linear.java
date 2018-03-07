package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.detectors.JewelDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.auto.AutoParams;

import static com.disnodeteam.dogecv.detectors.JewelDetector.*;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelArmDelayMS;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelColorTimeoutS;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelTurn;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelTurnTimeoutS;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelVotes;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.vuMarkVotes;
import static org.firstinspires.ftc.teamcode.subsystems.Drive.COUNTS_PER_INCH;

/**
 * This class extends linearopmode and makes it
 * easier to make code for the robot and not copy
 * and pasting init code.
 *
 * See this for an example: http://bit.ly/2B8scLB
 * Created by joshua9889 on 12/10/2017.
 */

public abstract class Team2753Linear extends LinearOpMode {

    private org.firstinspires.ftc.teamcode.subsystems.Drive Drive = new org.firstinspires.ftc.teamcode.subsystems.Drive();
    private org.firstinspires.ftc.teamcode.subsystems.Jewel Jewel = new org.firstinspires.ftc.teamcode.subsystems.Jewel();
    private org.firstinspires.ftc.teamcode.subsystems.Lift Lift = new org.firstinspires.ftc.teamcode.subsystems.Lift();
    private org.firstinspires.ftc.teamcode.subsystems.Intake Intake = new org.firstinspires.ftc.teamcode.subsystems.Intake();
    private org.firstinspires.ftc.teamcode.subsystems.Slammer Slammer = new org.firstinspires.ftc.teamcode.subsystems.Slammer();
    private org.firstinspires.ftc.teamcode.subsystems.Phone Phone = new org.firstinspires.ftc.teamcode.subsystems.Phone();

    //private LinearOpMode linearOpMode = null;
    public static VuMark vumark = new VuMark();
    public static ElapsedTime runtime = new ElapsedTime();
    private boolean isAuton = false; // Are we running auto
    private int Column = 0;
    private JewelDetector jewelDetector = null;


    //Init Methods

    public void waitForStart(LinearOpMode linearOpMode) {
        linearOpMode.waitForStart();
        runtime.reset();
    }

    public void initializeRobot(LinearOpMode linearOpMode, boolean auton){
        getDrive().init(linearOpMode, auton);
        getJewel().init(linearOpMode, auton);
        getLift().init(linearOpMode, auton);
        getIntake().init(linearOpMode, auton);
        getSlammer().init(linearOpMode, auton);
        getPhoneServo().init(linearOpMode, auton);
        if(auton){

            AutoTransitioner.transitionOnStop(linearOpMode, "Teleop"); //Auto Transitioning
            this.isAuton = auton;
        }
    }

    public void resetRuntime(){runtime.reset();}


    //Auto Methods

    //Lift

    /*
    public void liftLower(){
        getLift().setLiftPower(-0.2);
        sleep(250);
        getLift().brakeLift();
    }
    */


    //Vuforia

    public void startVuforia(VuforiaLocalizer.CameraDirection direction){this.vumark.setup(direction);}

    public RelicRecoveryVuMark initColumnVoteLoop(LinearOpMode linearOpMode, double timeoutS){
        int leftVotes = 0;
        int centerVotes = 0;
        int rightVotes = 0;
        runtime.reset();
        while(!linearOpMode.isStarted()
                && leftVotes < vuMarkVotes  &&  centerVotes < vuMarkVotes && rightVotes < vuMarkVotes
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
        if(leftVotes == vuMarkVotes)
            return RelicRecoveryVuMark.LEFT;
        else if (centerVotes == vuMarkVotes)
            return RelicRecoveryVuMark.CENTER;
        else if(rightVotes ==vuMarkVotes)
            return RelicRecoveryVuMark.RIGHT;
        else
            return RelicRecoveryVuMark.UNKNOWN;
    }

    public RelicRecoveryVuMark columnVoteLoop(LinearOpMode linearOpMode, double timeoutS){
        int leftVotes = 0;
        int centerVotes = 0;
        int rightVotes = 0;
        runtime.reset();
        while(linearOpMode.opModeIsActive()
                &&  leftVotes < vuMarkVotes  &&  centerVotes < vuMarkVotes && rightVotes < vuMarkVotes
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
                case UNKNOWN:
                    getPhoneServo().picturePosition();
                    break;
            }
            //linearOpMode.telemetry.addData("Left Votes", leftVotes);
            //linearOpMode.telemetry.addData("Center Votes", centerVotes);
            //linearOpMode.telemetry.addData("Right Votes", rightVotes);
            //linearOpMode.telemetry.update();
        }
        if(leftVotes == vuMarkVotes)
            return RelicRecoveryVuMark.LEFT;
        else if (centerVotes == vuMarkVotes)
            return RelicRecoveryVuMark.CENTER;
        else if(rightVotes ==vuMarkVotes)
            return RelicRecoveryVuMark.RIGHT;
        else
            return RelicRecoveryVuMark.UNKNOWN;
    }

    public void initColumnVote(LinearOpMode linearOpMode){
        switch (initColumnVoteLoop(linearOpMode, 5)){
            case LEFT:
                Column = 1;
                linearOpMode.telemetry.addData("Column", "Left");
                linearOpMode.telemetry.update();
                break;
            case CENTER:
                Column = 2;
                linearOpMode.telemetry.addData("Column", "Center");
                linearOpMode.telemetry.update();
                break;
            case RIGHT:
                Column = 3;
                linearOpMode.telemetry.addData("Column", "Right");
                linearOpMode.telemetry.update();
                break;
            case UNKNOWN:
                Column = 0;
                linearOpMode.telemetry.addData("Column", "Unknown");
                linearOpMode.telemetry.update();
                break;
        }
    }

    public void columnVote(LinearOpMode linearOpMode){

        if(Column == 0){
            switch (columnVoteLoop(linearOpMode, 5)){
                case LEFT:
                    Column = 1;
                    linearOpMode.telemetry.addData("Column", "Left");
                    linearOpMode.telemetry.update();
                    break;
                case CENTER:
                    Column = 2;
                    linearOpMode.telemetry.addData("Column", "Center");
                    linearOpMode.telemetry.update();
                    break;
                case RIGHT:
                    Column = 3;
                    linearOpMode.telemetry.addData("Column", "Right");
                    linearOpMode.telemetry.update();
                    break;
                case UNKNOWN:
                    Column = 0;
                    linearOpMode.telemetry.addData("Column", "Unknown");
                    linearOpMode.telemetry.update();
                    break;
            }
        }
        else if (Column == 1){
            linearOpMode.telemetry.addData("Column", "Left");
            linearOpMode.telemetry.update();
        }
        else if(Column == 2){
            linearOpMode.telemetry.addData("Column", "Center");
            linearOpMode.telemetry.update();
        }
        else if(Column == 3){
            linearOpMode.telemetry.addData("Column", "Right");
            linearOpMode.telemetry.update();
        }
    }

    public int getColumnValue(){return Column;}

    public void closeVuforia(){vumark.closeVuforia();}


    //Jewel

    public void initJewelDetector(){
        jewelDetector = new JewelDetector();
        jewelDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        //Jewel Detector Settings
        jewelDetector.areaWeight = 0.02;
        jewelDetector.detectionMode = JewelDetectionMode.PERFECT_AREA;
        //jewelDetector.detectionMode = JewelDetectionMode.MAX_AREA;
        jewelDetector.perfectArea = 1600;
        jewelDetector.debugContours = false;
        jewelDetector.maxDiffrence = 15;
        jewelDetector.ratioWeight = 15;
        jewelDetector.minArea = 800;
        //getPhoneServo().jewelPosition();
    }

    public void enableJewelDetector(){
        jewelDetector.enable();
        getPhoneServo().jewelPosition();
    }

    public JewelOrder findJewel(LinearOpMode linearOpMode, double timeoutS){

        int brVotes = 0;
        int rbVotes = 0;
        runtime.reset();

        while(opModeIsActive() &&
                brVotes < jewelVotes && rbVotes < jewelVotes &&
                runtime.seconds() < timeoutS)
        {
            switch (jewelDetector.getCurrentOrder()) {
                case BLUE_RED:
                    brVotes++;
                    break;
                case RED_BLUE:
                    rbVotes++;
                    break;
            }
            //linearOpMode.telemetry.addData("Jewel Order", jewelDetector.getCurrentOrder().toString());
            //linearOpMode.telemetry.addData("BLUE_RED Votes", brVotes);
            //linearOpMode.telemetry.addData("RED_BLUE Votes", rbVotes);
            //linearOpMode.telemetry.update();
        }
        if(brVotes + rbVotes != 0) {

            if (brVotes == jewelVotes) {
                linearOpMode.telemetry.addData("Jewel_Order", "Blue Red");
                return JewelOrder.BLUE_RED;
            }
            else if (rbVotes == jewelVotes) {
                linearOpMode.telemetry.addData("Jewel_Order", "Red Blue");
                return JewelOrder.RED_BLUE;
            }
            else if (brVotes > rbVotes) {
                linearOpMode.telemetry.addData("Jewel_Order", "Blue Red");
                return JewelOrder.BLUE_RED;
            }
            else if (rbVotes > brVotes) {
                linearOpMode.telemetry.addData("Jewel_Order", "Red Blue");
                return JewelOrder.RED_BLUE;
            }
            else {
                linearOpMode.telemetry.addData("Jewel_Order", "Unknown");

                return JewelOrder.UNKNOWN;
            }
        }
        else{
            linearOpMode.telemetry.addData("Jewel_Order", "Unknown");
            return JewelOrder.UNKNOWN;
            }
    }

    public void jewelRed(LinearOpMode linearOpMode){
        if(opModeIsActive()){
            switch(findJewel(linearOpMode, jewelColorTimeoutS)){
                case BLUE_RED:
                    getJewel().deploy();
                    waitForTick(jewelArmDelayMS);
                    /*
                    getDrive().turnCCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    getJewel().retract();
                    waitForTick(150);
                    getDrive().turnCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    waitForTick(100);
                    */
                    break;
                case RED_BLUE:
                    getJewel().deploy();
                    waitForTick(jewelArmDelayMS);
                    getDrive().turnCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    getJewel().retract();
                    //waitForTick(150);
                    //getDrive().turnCCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    jewelTurnReturn(linearOpMode, jewelTurnSpeed, jewelTurnTimeoutS);
                    //waitForTick(100);
                    break;
            }
        }
    }

    public void jewelBlue(LinearOpMode linearOpMode){
        if(opModeIsActive()){
            switch(findJewel(linearOpMode, jewelColorTimeoutS)){
                case BLUE_RED:
                    getJewel().deploy();
                    waitForTick(jewelArmDelayMS);
                    /*
                    getDrive().turnCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    getJewel().retract();
                    waitForTick(150);
                    getDrive().turnCCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    waitForTick(100);
                    */
                    break;
                case RED_BLUE:
                    getJewel().deploy();
                    waitForTick(jewelArmDelayMS);
                    getDrive().turnCCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    getJewel().retract();
                    //waitForTick(150);
                    getDrive().turnCW(jewelTurn, jewelTurnSpeed, jewelTurnTimeoutS);
                    jewelTurnReturn(linearOpMode, jewelTurnSpeed, jewelTurnTimeoutS);
                    //waitForTick(100);
                    break;
            }
        }
    }

    public void jewelDrive(LinearOpMode linearOpMode, double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = getDrive().getLeftCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = getDrive().getRightCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            getDrive().setLeftRightTarget(newLeftTarget, newRightTarget);
            //int counter1 = 0;
            //int counter2 = 0;

            // Turn On RUN_TO_POSITION
            getDrive().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            getDrive().setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (getDrive().leftIsBusy() || getDrive().rightIsBusy())) {
                if((Math.abs(getDrive().getLeftCurrentPosition())>(newLeftTarget-(leftInches*COUNTS_PER_INCH)+(6*COUNTS_PER_INCH)))){
                    getJewel().retract();
                }
                //slow the motors down to half the original speed when we get within 4 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(newLeftTarget - getDrive().getLeftCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && (Math.abs(newRightTarget - getDrive().getRightCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    getDrive().setLeftRightPowers(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.3 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(newLeftTarget - getDrive().getLeftCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(newRightTarget - getDrive().getRightCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    getDrive().setLeftRightPowers(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            getDrive().setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            getDrive().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  linearOpMode.sleep(250);   // optional pause after each move
        }
    }

    public void jewelTurnReturn(LinearOpMode linearOpMode, double speed, double timeoutS) {

        // Ensure that the opmode is still active
        if(linearOpMode.opModeIsActive()){

            getDrive().setLeftRightTarget(0, 0);

            // Turn On RUN_TO_POSITION
            getDrive().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            getDrive().setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (getDrive().leftIsBusy() || getDrive().rightIsBusy())) {
                //slow the motors down to half the original speed when we get within 4 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(getDrive().getLeftCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && (Math.abs(getDrive().getRightCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    getDrive().setLeftRightPowers(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.3 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(getDrive().getLeftCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(getDrive().getRightCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    getDrive().setLeftRightPowers(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            getDrive().setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            getDrive().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  linearOpMode.sleep(250);   // optional pause after each move
        }
    }

    public void disableJewelDetector(){jewelDetector.disable();}


    //Glyph

    public void glyphScoreB1(LinearOpMode linearOpMode){

        if(Column == 1){
            jewelDrive(linearOpMode, autoSpeed, -28, -28, 4);
        }
        else if(Column == 2){
            jewelDrive(linearOpMode, autoSpeed, -36, -36, 4);
        }
        else if(Column == 3){
            jewelDrive(linearOpMode, autoSpeed, -44, -44, 4);;
        }
        else{
            jewelDrive(linearOpMode, autoSpeed, -36, -36, 4);
        }
        getDrive().turnCW(90, autoTurnSpeed, 4);
        getDrive().encoderDrive(autoSpeed, 8, 8, 2);
        //waitForTick(75);
        scoreGlyphDropIntake();
    }

    public void glyphScoreR1(LinearOpMode linearOpMode){

        if(Column == 1){
            jewelDrive(linearOpMode, autoSpeed, 44, 44, 4);
        }
        else if(Column == 2){
            jewelDrive(linearOpMode, autoSpeed, 36, 36, 4);
        }
        else if(Column == 3){
            jewelDrive(linearOpMode, autoSpeed, 28, 28, 4);
        }
        else{
            jewelDrive(linearOpMode, autoSpeed, 36, 36, 4);
        }
        getDrive().turnCW(90, autoTurnSpeed, 4);
        getDrive().encoderDrive(autoSpeed, 8, 8, 2);
        //waitForTick(75);
        scoreGlyphDropIntake();
    }

    public void glyphScoreB2(){

        //if(retarded){kms}

        getDrive().encoderDrive(autoSpeed, -24,-24, 5);
        getJewel().retract();
        //getDrive().encoderDrive(autoSpeed + 0.05, 0, -19.83, 4);
        getDrive().turnCW(90, autoTurnSpeed, 4);

        if(Column == 1){
            getDrive().encoderDrive(autoSpeed + 0.1, -4, -4, 5);
        }
        else if(Column == 2){
            getDrive().encoderDrive(autoSpeed, -12, -12, 4);
        }
        else if(Column == 3){
            getDrive().encoderDrive(autoSpeed, -20, -20, 4);

        }
        else{
            getDrive().encoderDrive(autoSpeed, -12, -12, 4);

        }

        //waitForTick(1500);
        getDrive().turnCW(90, autoTurnSpeed, 4);
        getDrive().encoderDrive(autoSpeed, 5, 5, 4);
        scoreGlyph();
    }

    public void glyphScoreR2(){

        getDrive().encoderDrive(autoSpeed, 26,26, 5);
        getJewel().retract();
        //getDrive().encoderDrive(autoSpeed + 0.05, 0, -19.83, 4);
        //getDrive().turnCCW(90, autoTurnSpeed, 4);
        getDrive().turnCW(-90, autoTurnSpeed, 4);

        if(Column == 1){
            getDrive().encoderDrive(autoSpeed, 20, 20, 4);
        }
        else if(Column == 2){
            getDrive().encoderDrive(autoSpeed, 12, 12, 4);
        }
        else if(Column == 3){
            getDrive().encoderDrive(autoSpeed, 4, 4, 4);
        }
        else{
            getDrive().encoderDrive(autoSpeed, 12, 12, 4);
        }

        //waitForTick(100);
        getDrive().turnCW(90, autoTurnSpeed, 4);
        getDrive().encoderDrive(autoSpeed, 4, 4, 4);
        scoreGlyph();
    }

    public void scoreGlyphDropIntake(){
        getSlammer().stopperUp();
        waitForTick(350);
        getSlammer().setPower(0.35);
        waitForTick(450);
        getSlammer().stop();
        getIntake().releaseIntake();
        getDrive().encoderDirectDrive(autoSpeed, -3, -3, 1);
        getSlammer().setPower(-0.3);
        getDrive().encoderDrive(autoSpeed, 6,6, 1.5);
        getSlammer().stop();
        getDrive().encoderDrive(autoSpeed, -6, -6, 3);
    }

    public void scoreGlyph(){
        getSlammer().stopperUp();
        waitForTick(350);
        getSlammer().setPower(0.35);
        waitForTick(500);
        getSlammer().stop();
        getIntake().releaseLock();
        getDrive().encoderDirectDrive(autoSpeed, -3, -3, 2);
        getDrive().encoderDrive(autoSpeed, 6, 6, 1.5);
        getSlammer().setPower(-0.3);
        getDrive().encoderDrive(autoSpeed, -6, -6, 1.5);
        getSlammer().stop();
    }

    //use timeoutS to ensure we have enough time to park before the end of autonomous
    public void multiGlyphPos1(){
        //v 2.0 - Final version tune for more consistent results and for different columns

        getIntake().reverse();
        getSlammer().stopperDown();
        getDrive().encoderDirectDrive(autoSpeed + 0.15, -20, -20, 3);
        getIntake().intake();
        getDrive().encoderDrive(0.75, -4, -4, 2);
        waitForTick(200);
        int leftPosition = getDrive().getLeftCurrentPosition();
        int rightPosition = getDrive().getRightCurrentPosition();
        if(Column == 1) {
            getDrive().encoderDrive(0.75, 0, -9.915, 2);
            getDrive().encoderTargetDrive(autoSpeed, leftPosition, rightPosition, 2);
        }
        else {
            getDrive().encoderDrive(0.75, -9.915, 0, 2);
            getDrive().encoderTargetDrive(autoSpeed, leftPosition, rightPosition, 2);
        }
        waitForTick(250);
        getIntake().stop();
        /*
        if(Column == 1){
            getDrive().turnCW(27, autoTurnSpeed, 2);
            getDrive().encoderDrive(autoSpeed, 26.83, 26.83,2);
            getDrive().turnCCW(63, autoTurnSpeed, 2);
        }
        else if (Column == 2){
            getDrive().turnCCW(14, autoTurnSpeed, 2);
            getDrive().encoderDrive(autoSpeed, 24.73, 24.73, 2);
            getDrive().turnCW(76, autoTurnSpeed, 2);
        }
        else if (Column == 3){
            getDrive().turnCCW(27, autoTurnSpeed, 2);
            getDrive().encoderDrive(autoSpeed, 26.83, 26.83, 2);
            getDrive().turnCW(63, autoTurnSpeed, 2);
        }
        else*/
            getDrive().encoderDrive(autoSpeed + 0.15 , 23, 23, 3);
        getDrive().encoderDrive(autoSpeed, 0, 2, 1.5);
        scoreGlyph();

    }

    public void multiGlyphR2(){}

    public void multiGlyphB2(){}

    public void intakeDrive(){}


    //Telemetry

    public void updateTelemetry(LinearOpMode linearOpMode) {

        if(isAuton){
            linearOpMode.telemetry.addData("Match Time", 30 - linearOpMode.getRuntime());
        }
        if (!isAuton) {
            linearOpMode.telemetry.addData("Match Time", 120 - runtime.seconds());
            if(runtime.seconds() > 90){
                linearOpMode.telemetry.addData("Phase", "End game");
            }
            if(runtime.seconds() > 120){
                linearOpMode.telemetry.addData("Phase", "Overtime");
            }
        }
        getDrive().outputToTelemetry(linearOpMode.telemetry);
        getJewel().outputToTelemetry(linearOpMode.telemetry);
        getIntake().outputToTelemetry(linearOpMode.telemetry);
        getLift().outputToTelemetry(linearOpMode.telemetry);
        getSlammer().outputToTelemetry(linearOpMode.telemetry);
        getPhoneServo().outputToTelemetry(linearOpMode.telemetry);
        linearOpMode.telemetry.update();
    }


    //Other

    public void finalAction(){

            getDrive().stop();
            getJewel().stop();
            getLift().stop();
            getIntake().stop();
            getPhoneServo().stop();
            getSlammer().stop();

            requestOpModeStop();

    }

    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)runtime.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        runtime.reset();
    }


    //Getter Methods

    public org.firstinspires.ftc.teamcode.subsystems.Drive getDrive() {
        return Drive;
    }

    public org.firstinspires.ftc.teamcode.subsystems.Jewel getJewel() {
        return Jewel;
    }

    public org.firstinspires.ftc.teamcode.subsystems.Lift getLift () { return Lift; }

    public org.firstinspires.ftc.teamcode.subsystems.Intake getIntake() {return Intake;}

    public org.firstinspires.ftc.teamcode.subsystems.Slammer getSlammer() {return Slammer;}

    public org.firstinspires.ftc.teamcode.subsystems.Phone getPhoneServo() {return Phone;}
}


