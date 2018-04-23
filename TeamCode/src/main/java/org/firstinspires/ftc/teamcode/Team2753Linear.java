package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.libs.AutoTransitioner;
import org.firstinspires.ftc.teamcode.libs.CameraBlinker;
import org.firstinspires.ftc.teamcode.libs.subsystems.VuMark;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.libs.subsystems.VuMark.SaveImage;
import static org.firstinspires.ftc.teamcode.libs.subsystems.VuMark.blue;
import static org.firstinspires.ftc.teamcode.libs.subsystems.VuMark.red;
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

    // Robot class for all subsystems
    public Robot Robot = new Robot();

    public static final String vuforiaKey = "AeUsQDb/////AAAAGXsDAQwNS0SWopXJpAHyRntcnTcoWD8Tns"+
            "R6PWGX9OwmlIhNxQgn8RX/1cH2RXXTsuSkHh6OjfMoCuHt35rhumaUsLnk8MZZJ7P9PEu+uSsUbH1hHcnnB"+
            "6GzJnX/FqlZJX5HWWfeQva5s4OHJEwSbPR2zxhkRxntAjeuIPGVSHeIseAikPB0NF0SqEiPZea+PWrxpryP"+
            "/bxKqy7VA77krKFtgDi6amam+vWvBCqyIo6tXxbo0w8q/HCXo4v/4UYyoFLRx1l1d2Wya5an5SwFfU3eKxy"+
            "0BYc3tnsaaDJww59RNJ6IK9D3PZM+oPDrmF9ukQrc/jw+u+6Zm4wQHieHt9urSwLR7dgz0V3aatDx1V7y";

    private static VuMark vumark = new VuMark(vuforiaKey);
    private RelicRecoveryVuMark savedVumark = RelicRecoveryVuMark.UNKNOWN;

    private Bitmap bm=null;
    private int redVotes = 0;
    private int blueVotes = 0;

    // Jewel
    public enum Jewel_Color {
        Red, Blue
    }
    protected Jewel_Color jewel_Color = null;

    private static ElapsedTime runtime = new ElapsedTime();
    private boolean isAuton = false; // Are we running auto

    private Telemetry.Item status;

    //Init Methods

    public void waitForStart(String OpModeName, boolean auton, boolean camera) {

        CameraBlinker cameraBlinker = new CameraBlinker();
        cameraBlinker.on();
        Ryan = gamepad1;
        Seth = gamepad2;

        telemetry.setAutoClear(false);
        status = telemetry.addData("Status", "Initializing...");
        Telemetry.Item currentOpMode = telemetry.addData("Running", OpModeName);
        telemetry.update();

        Robot.init(this, auton);


        if(auton) {
            cameraBlinker.off();
            RobotLog.v("================ Start VuCam =============");
            vumark.setup(VuforiaLocalizer.CameraDirection.BACK, true);

            RobotLog.v("================ AutoTransitioner =============");
            AutoTransitioner.transitionOnStop(this, "Teleop"); //Auto Transitioning

            RobotLog.v("================ VuCam Loop =============");
            while (!isStarted() && !isStopRequested()){
                vumark.update();

                telemetry.clearAll();
                telemetry.addData("VuMark", vumark.getOuputVuMark());
                telemetry.update();

                savedVumark = vumark.getOuputVuMark();
                try {
                    bm = vumark.getBm(20);
                } catch (Exception e){
                    bm = null;
                }

            }

            while (bm==null){
                try {
                    bm = vumark.getBm(20);
                } catch (Exception e){
                    bm = null;
                }
            }

            RobotLog.v("================ VuCam Loop Finished =============");

            RobotLog.v("================ Scan Bitmap =============");
            int redValue = 0;
            int blueValue = 0;

            // Scan area for red and blue pixels
            for (int x = (bm.getWidth()/2)+(bm.getWidth()/6); x < ((bm.getWidth()/2)+(18*bm.getWidth()/64)); x++) {
                for (int y = (2*bm.getHeight() / 5) + (bm.getHeight() / 2); y < bm.getHeight(); y++) {
                    int pixel = bm.getPixel(x,y);
                    redValue = red(pixel);
                    blueValue = blue(pixel);

                    if(redValue>blueValue) {
                        redVotes++;
                        bm.setPixel(x,y, Color.RED);
                    }
                    else if(blueValue>redValue) {
                        blueVotes++;
                        bm.setPixel(x, y, Color.BLUE);
                    }
                }
            }

            SaveImage(bm);

            if(redVotes>blueVotes)
                jewel_Color=Jewel_Color.Red;
            else if(redVotes<blueVotes)
                jewel_Color=Jewel_Color.Blue;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(1000);
                    vumark.disableVuforia();
                    Thread.yield();
                }
            }).start();

        } else {
            SetStatus("Initialized, Waiting for Start");
            waitForStart();
            cameraBlinker.off();
        }

        runtime.reset();
        SetStatus("Running OpMode");
        RobotLog.v("================ Running OpMode =============");
    }

    public void SetStatus(String update){
        status.setValue(update);
        telemetry.update();
    }

    public void resetRuntime(){runtime.reset();}


    //Auto Methods

    //Vuforia
    public void startVuforia(VuforiaLocalizer.CameraDirection direction){vumark.setup(direction);}

    public void jewelDrive(LinearOpMode linearOpMode, double speed, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (linearOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = Robot.getDrive().getLeftCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = Robot.getDrive().getRightCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            Robot.getDrive().setLeftRightTarget(newLeftTarget, newRightTarget);
            //int counter1 = 0;
            //int counter2 = 0;

            // Turn On RUN_TO_POSITION
            Robot.getDrive().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            Robot.getDrive().setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (Robot.getDrive().leftIsBusy() || Robot.getDrive().rightIsBusy())) {
                if((Math.abs(Robot.getDrive().getLeftCurrentPosition())>(newLeftTarget-(leftInches*COUNTS_PER_INCH)+(6*COUNTS_PER_INCH)))){
                    Robot.getJewel().retract(true);
                }
                //slow the motors down to half the original speed when we get within 4 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(newLeftTarget - Robot.getDrive().getLeftCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && (Math.abs(newRightTarget - Robot.getDrive().getRightCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    Robot.getDrive().setLeftRightPowers(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.3 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(newLeftTarget - Robot.getDrive().getLeftCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(newRightTarget - Robot.getDrive().getRightCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    Robot.getDrive().setLeftRightPowers(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            Robot.getDrive().setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            Robot.getDrive().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  linearOpMode.sleep(250);   // optional pause after each move
        }
    }

    public void jewelTurnReturn(LinearOpMode linearOpMode, double speed, double timeoutS) {

        // Ensure that the opmode is still active
        if(linearOpMode.opModeIsActive()){

            Robot.getDrive().setLeftRightTarget(0, 0);

            // Turn On RUN_TO_POSITION
            Robot.getDrive().setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            Robot.getDrive().setLeftRightPowers(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (Robot.getDrive().leftIsBusy() || Robot.getDrive().rightIsBusy())) {
                //slow the motors down to half the original speed when we get within 4 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(Robot.getDrive().getLeftCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && (Math.abs(Robot.getDrive().getRightCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    Robot.getDrive().setLeftRightPowers(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.3 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(Robot.getDrive().getLeftCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(Robot.getDrive().getRightCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    Robot.getDrive().setLeftRightPowers(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            Robot.getDrive().setLeftRightPowers(0,0);

            // Turn off RUN_TO_POSITION
            Robot.getDrive().setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

        Robot.getDrive().turnCW(90, autoTurnSpeed, 4);
        Robot.getDrive().encoderDrive(autoSpeed, 8, 8, 2);
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

        Robot.getDrive().turnCW(90, autoTurnSpeed, 4);
        Robot.getDrive().encoderDrive(autoSpeed, 8, 8, 2);
        //waitForTick(75);
        scoreGlyphDropIntake();
    }

    public void glyphScoreB2(){

        //if(retarded){kms}

        Robot.getDrive().encoderDrive(autoSpeed, -24,-24, 5);
        Robot.getJewel().retract(true);
        //getDrive().encoderDrive(autoSpeed + 0.05, 0, -19.83, 4);
        Robot.getDrive().turnCW(90, autoTurnSpeed, 4);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                Robot.getDrive().encoderDrive(autoSpeed + 0.1, -4, -4, 5);
                break;
            case CENTER:
                Robot.getDrive().encoderDrive(autoSpeed, -12, -12, 4);
                break;
            case RIGHT:
                Robot.getDrive().encoderDrive(autoSpeed, -20, -20, 4);
                break;
            case UNKNOWN:
                Robot.getDrive().encoderDrive(autoSpeed, -12, -12, 4);
                break;
        }

        //waitForTick(1500);
        Robot.getDrive().turnCW(90, autoTurnSpeed, 4);
        Robot.getDrive().encoderDrive(autoSpeed, 5, 5, 4);
        scoreGlyph();
    }

    public void glyphScoreR2(){

        Robot.getDrive().encoderDrive(autoSpeed, 26,26, 5);
        Robot.getJewel().retract(true);
        //getDrive().encoderDrive(autoSpeed + 0.05, 0, -19.83, 4);
        //getDrive().turnCCW(90, autoTurnSpeed, 4);
        Robot.getDrive().turnCW(-90, autoTurnSpeed, 4);

        switch (WhatColumnToScoreIn()){
            case LEFT:
                Robot.getDrive().encoderDrive(autoSpeed, 20, 20, 4);
                break;
            case CENTER:
                Robot.getDrive().encoderDrive(autoSpeed, 12, 12, 4);
                break;
            case RIGHT:
                Robot.getDrive().encoderDrive(autoSpeed, 4, 4, 4);
                break;
            case UNKNOWN:
                Robot.getDrive().encoderDrive(autoSpeed, 12, 12, 4);
                break;
        }

        //waitForTick(100);
        Robot.getDrive().turnCW(90, autoTurnSpeed, 4);
        Robot.getDrive().encoderDrive(autoSpeed, 4, 4, 4);
        scoreGlyph();
    }

    public void scoreGlyphDropIntake(){
        Robot.getSlammer().stopperUp();
        waitForTick(350);
        Robot.getSlammer().setPower(0.35);
        waitForTick(450);
        Robot.getSlammer().stop();
        Robot.getIntake().releaseIntake();
        Robot.getDrive().encoderDirectDrive(autoSpeed, -3, -3, 1);
        Robot.getSlammer().setPower(-0.3);
        Robot.getDrive().encoderDrive(autoSpeed, 6,6, 1.5);
        Robot.getSlammer().stop();
        Robot.getDrive().encoderDrive(autoSpeed, -6, -6, 3);
    }

    public void scoreGlyph(){
        Robot.getSlammer().stopperUp();
        waitForTick(350);
        Robot.getSlammer().setPower(0.35);
        waitForTick(500);
        Robot.getSlammer().stop();
        Robot.getIntake().releaseLock();
        Robot.getDrive().encoderDirectDrive(autoSpeed, -3, -3, 2);
        Robot.getDrive().encoderDrive(autoSpeed, 6, 6, 1.5);
        Robot.getSlammer().setPower(-0.3);
        Robot.getDrive().encoderDrive(autoSpeed, -6, -6, 1.5);
        Robot.getSlammer().stop();
    }

    //use timeoutS to ensure we have enough time to park before the end of autonomous
    public void multiGlyphPos1(){
        //v 2.0 - Final version tune for more consistent results and for different columns

        Robot.getIntake().reverse();
        Robot.getSlammer().stopperDown();
        Robot.getDrive().encoderDirectDrive(autoSpeed + 0.15, -20, -20, 3);
        Robot.getIntake().intake();
        Robot.getDrive().encoderDrive(0.75, -4, -4, 2);
        waitForTick(200);
        int leftPosition = Robot.getDrive().getLeftCurrentPosition();
        int rightPosition = Robot.getDrive().getRightCurrentPosition();

        switch (WhatColumnToScoreIn()){
            case LEFT:
                Robot.getDrive().encoderDrive(0.75, 0, -9.915, 2);
                Robot.getDrive().encoderTargetDrive(autoSpeed, leftPosition, rightPosition, 2);
                break;
            default:
                Robot.getDrive().encoderDrive(0.75, -9.915, 0, 2);
                Robot.getDrive().encoderTargetDrive(autoSpeed, leftPosition, rightPosition, 2);
                break;
        }

        waitForTick(250);
        Robot.getIntake().stop();

        Robot.getDrive().encoderDrive(autoSpeed + 0.15 , 23, 23, 3);
        Robot.getDrive().encoderDrive(autoSpeed, 0, 2, 1.5);
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

        telemetry.clearAll();
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

        Robot.outputToTelemetry(telemetry);

        telemetry.update();
    }


    //Other

    public void finalAction(){
        Robot.stop();

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
}


