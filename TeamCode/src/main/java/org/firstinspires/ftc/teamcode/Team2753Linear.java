package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbDeviceInterfaceModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.libs.AutoTransitioner;
import org.firstinspires.ftc.teamcode.libs.VuMark;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

import java.util.Arrays;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.CENTER;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.LEFT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.RIGHT;
import static org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark.UNKNOWN;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.vuMarkVotes;
import static org.firstinspires.ftc.teamcode.libs.VuMark.blue;
import static org.firstinspires.ftc.teamcode.libs.VuMark.red;
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

    public Gamepad Ryan = gamepad1;
    public Gamepad Seth = gamepad2;

    private org.firstinspires.ftc.teamcode.subsystems.Drive Drive = new org.firstinspires.ftc.teamcode.subsystems.Drive();
    private org.firstinspires.ftc.teamcode.subsystems.Jewel Jewel = new org.firstinspires.ftc.teamcode.subsystems.Jewel();
    private org.firstinspires.ftc.teamcode.subsystems.Lift Lift = new org.firstinspires.ftc.teamcode.subsystems.Lift();
    private org.firstinspires.ftc.teamcode.subsystems.Intake Intake = new org.firstinspires.ftc.teamcode.subsystems.Intake();
    private org.firstinspires.ftc.teamcode.subsystems.Slammer Slammer = new org.firstinspires.ftc.teamcode.subsystems.Slammer();
    private org.firstinspires.ftc.teamcode.subsystems.Phone Phone = new org.firstinspires.ftc.teamcode.subsystems.Phone();

    // Used to init everything quickly
    private List<Subsystem> subsystems = Arrays.asList(Drive, Jewel, Lift, Intake, Slammer, Phone);

    public static final String vuforiaKey = "AeUsQDb/////AAAAGXsDAQwNS0SWopXJpAHyRntcnTcoWD8Tns"+
            "R6PWGX9OwmlIhNxQgn8RX/1cH2RXXTsuSkHh6OjfMoCuHt35rhumaUsLnk8MZZJ7P9PEu+uSsUbH1hHcnnB"+
            "6GzJnX/FqlZJX5HWWfeQva5s4OHJEwSbPR2zxhkRxntAjeuIPGVSHeIseAikPB0NF0SqEiPZea+PWrxpryP"+
            "/bxKqy7VA77krKFtgDi6amam+vWvBCqyIo6tXxbo0w8q/HCXo4v/4UYyoFLRx1l1d2Wya5an5SwFfU3eKxy"+
            "0BYc3tnsaaDJww59RNJ6IK9D3PZM+oPDrmF9ukQrc/jw+u+6Zm4wQHieHt9urSwLR7dgz0V3aatDx1V7y";

    public static VuMark vumark = new VuMark(vuforiaKey);
    private RelicRecoveryVuMark savedVumark = RelicRecoveryVuMark.UNKNOWN;
    private int redVotes = 0;
    private int blueVotes = 0;

    public enum Jewel_Color{
        RED, BLUE
    }
    public Jewel_Color scannedColor;

    public static ElapsedTime runtime = new ElapsedTime();
    private boolean isAuton = false; // Are we running auto
    private int Column = 0;

    private Telemetry.Item status;

    //Init Methods

    public void waitForStart(String OpModeName, boolean auton, boolean camera) {

        Ryan = gamepad1;
        Seth = gamepad2;

        telemetry.setAutoClear(false);
        status = telemetry.addData("Status", "Initializing...");
        Telemetry.Item currentOpMode = telemetry.addData("Running", OpModeName);
        telemetry.update();

        //Initialize Robot
        for (Subsystem subsystem:subsystems) {
            subsystem.init(this, auton);
        }

        if(camera && auton) {
            AutoTransitioner.transitionOnStop(this, "Teleop"); //Auto Transitioning

            while(!isStarted() && !isStopRequested()) {
                // Update VuMark
                vumark.update();

                if(vumark.getOuputVuMark()!= RelicRecoveryVuMark.UNKNOWN)
                    savedVumark = vumark.getOuputVuMark();

                // Value of all pixels
                int redValue = 0;
                int blueValue = 0;

                // Get current bitmap from Vuforia
                Bitmap bm = vumark.getBm(20);

                if(bm != null){
                    // Scan area for red and blue pixels
                    // TODO: Find the correct values for this
                    for (int x = 0; x < bm.getWidth()/5 && !isStarted() && !isStopRequested(); x++) {
                        for (int y = (bm.getHeight()/4)+(bm.getHeight()/2); y < bm.getHeight() && !isStarted() && !isStopRequested(); y++) {
                            int pixel = bm.getPixel(x,y);
                            redValue += red(pixel);
                            blueValue += blue(pixel);

                            if(redValue>blueValue)
                                redVotes++;
                            else if(blueValue>redValue)
                                blueVotes++;
                            idle();
                        }
                    }
                    bm.recycle();
                }

                // Cut off
                if (redVotes>300) {
                    redVotes = 50;
                    blueVotes = 0;
                    scannedColor=Jewel_Color.RED;
                } else if(blueVotes > 300){
                    redVotes = 0;
                    blueVotes = 50;
                    scannedColor=Jewel_Color.BLUE;
                }


                // Output Telemetry
                if(WhatColumnToScoreIn()!=UNKNOWN)
                    SetStatus("Initialized, Waiting for Start");

                telemetry.addData("VuMark", WhatColumnToScoreIn());
                telemetry.addData("Red Votes", redVotes);
                telemetry.addData("Blue Votes", blueVotes);
                telemetry.update();
                idle();
            }
        } else {
            SetStatus("Initialized, Waiting for Start");
            waitForStart();
        }

        runtime.reset();
        SetStatus("Running OpMode");
    }

    public void SetStatus(String update){
        status.setValue(update);
        telemetry.update();
    }

    public void resetRuntime(){runtime.reset();}


    //Auto Methods

    //Vuforia
    public void startVuforia(VuforiaLocalizer.CameraDirection direction){vumark.setup(direction);}

    public void hitJewelOff(Jewel_Color Alliance){
        Jewel.deploy(true);

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
                    getJewel().retract(true);
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


    //Glyph
    public void glyphScoreB1(LinearOpMode linearOpMode){

        switch (WhatColumnToScoreIn()){
            case LEFT:
                jewelDrive(linearOpMode, autoSpeed, -28, -28, 4);
                break;
            case CENTER:
                jewelDrive(linearOpMode, autoSpeed, -36, -36, 4);
                break;
            case RIGHT:
                jewelDrive(linearOpMode, autoSpeed, -44, -44, 4);
                break;
            case UNKNOWN:
                jewelDrive(linearOpMode, autoSpeed, -36, -36, 4);
                break;
        }

        getDrive().turnCW(90, autoTurnSpeed, 4);
        getDrive().encoderDrive(autoSpeed, 8, 8, 2);
        //waitForTick(75);
        scoreGlyphDropIntake();
    }

    public void glyphScoreR1(LinearOpMode linearOpMode){

        switch (WhatColumnToScoreIn()){
            case LEFT:
                jewelDrive(linearOpMode, autoSpeed, 44, 44, 4);
                break;
            case CENTER:
                jewelDrive(linearOpMode, autoSpeed, 36, 36, 4);
                break;
            case RIGHT:
                jewelDrive(linearOpMode, autoSpeed, 28, 28, 4);
                break;
            case UNKNOWN:
                jewelDrive(linearOpMode, autoSpeed, 36, 36, 4);
                break;
        }

        getDrive().turnCW(90, autoTurnSpeed, 4);
        getDrive().encoderDrive(autoSpeed, 8, 8, 2);
        //waitForTick(75);
        scoreGlyphDropIntake();
    }

    public void glyphScoreB2(){

        //if(retarded){kms}

        getDrive().encoderDrive(autoSpeed, -24,-24, 5);
        getJewel().retract(true);
        //getDrive().encoderDrive(autoSpeed + 0.05, 0, -19.83, 4);
        getDrive().turnCW(90, autoTurnSpeed, 4);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                getDrive().encoderDrive(autoSpeed + 0.1, -4, -4, 5);
                break;
            case CENTER:
                getDrive().encoderDrive(autoSpeed, -12, -12, 4);
                break;
            case RIGHT:
                getDrive().encoderDrive(autoSpeed, -20, -20, 4);
                break;
            case UNKNOWN:
                getDrive().encoderDrive(autoSpeed, -12, -12, 4);
                break;
        }

        //waitForTick(1500);
        getDrive().turnCW(90, autoTurnSpeed, 4);
        getDrive().encoderDrive(autoSpeed, 5, 5, 4);
        scoreGlyph();
    }

    public void glyphScoreR2(){

        getDrive().encoderDrive(autoSpeed, 26,26, 5);
        getJewel().retract(true);
        //getDrive().encoderDrive(autoSpeed + 0.05, 0, -19.83, 4);
        //getDrive().turnCCW(90, autoTurnSpeed, 4);
        getDrive().turnCW(-90, autoTurnSpeed, 4);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                getDrive().encoderDrive(autoSpeed, 20, 20, 4);
                break;
            case CENTER:
                getDrive().encoderDrive(autoSpeed, 12, 12, 4);
                break;
            case RIGHT:
                getDrive().encoderDrive(autoSpeed, 4, 4, 4);
                break;
            case UNKNOWN:
                getDrive().encoderDrive(autoSpeed, 12, 12, 4);
                break;
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

        switch (WhatColumnToScoreIn()){
            case LEFT:
                getDrive().encoderDrive(0.75, 0, -9.915, 2);
                getDrive().encoderTargetDrive(autoSpeed, leftPosition, rightPosition, 2);
                break;
            default:
                getDrive().encoderDrive(0.75, -9.915, 0, 2);
                getDrive().encoderTargetDrive(autoSpeed, leftPosition, rightPosition, 2);
                break;
        }

        waitForTick(250);
        getIntake().stop();

        getDrive().encoderDrive(autoSpeed + 0.15 , 23, 23, 3);
        getDrive().encoderDrive(autoSpeed, 0, 2, 1.5);
        scoreGlyph();

    }

    public void multiGlyphR2(){}

    public void multiGlyphB2(){}

    public void intakeDrive(){}

    public RelicRecoveryVuMark WhatColumnToScoreIn(){
        return savedVumark;
    }


    //Telemetry

    public void updateTelemetry() {

        if(isAuton){
            telemetry.addData("Match Time", 30 - getRuntime());
        }
        if (!isAuton) {
            telemetry.addData("Match Time", 120 - runtime.seconds());
            if(runtime.seconds() > 90){
                telemetry.addData("Phase", "End game");
            }
            if(runtime.seconds() > 120){
                telemetry.addData("Phase", "Overtime");
            }
        }
        for(Subsystem subsystem:subsystems)
            subsystem.outputToTelemetry(telemetry);

        telemetry.update();
    }


    //Other

    public void finalAction(){
        for (Subsystem subsystem:subsystems)
            subsystem.stop();

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


