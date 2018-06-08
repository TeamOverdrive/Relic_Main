package com.team2753.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.team2753.Team2753Linear;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team2753.Constants.COUNTS_PER_INCH;
import static com.team2753.auto.AutoParams.autoSpeed;
import static com.team2753.auto.AutoParams.autoTurnSpeed;


/**
 * Created by joshua9889 on 3/23/2018.
 */

public abstract class AutoModeBase extends Team2753Linear {

    public void hitJewel(Team2753Linear.Jewel_Color scannedJewel, Team2753Linear.Jewel_Color alliance_color){
        Robot.getJewel().deploy(true);
        sleep(500);


        if(scannedJewel==alliance_color){
            Robot.getJewel().rightHit();
        } else {
            Robot.getJewel().leftHit();
        }

        sleep(200);
        Robot.getJewel().retract(false);
        sleep(300);
    }

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
            Robot.getDrive().setLeftRightPower(Math.abs(speed), Math.abs(speed));

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
                    Robot.getDrive().setLeftRightPower(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.3 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(newLeftTarget - Robot.getDrive().getLeftCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(newRightTarget - Robot.getDrive().getRightCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    Robot.getDrive().setLeftRightPower(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            Robot.getDrive().setLeftRightPower(0,0);

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
            Robot.getDrive().setLeftRightPower(Math.abs(speed), Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (linearOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (Robot.getDrive().leftIsBusy() || Robot.getDrive().rightIsBusy())) {
                //slow the motors down to half the original speed when we get within 4 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(Robot.getDrive().getLeftCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && (Math.abs(Robot.getDrive().getRightCurrentPosition()) < (4.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    Robot.getDrive().setLeftRightPower(Math.abs(speed * 0.75), Math.abs(speed * 0.75));
                }
                //slow the motors down to 0.3 of the original speed when we get within 2 inches of our target and the speed is greater than 0.1.
                if ((Math.abs(Robot.getDrive().getLeftCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && (Math.abs(Robot.getDrive().getRightCurrentPosition()) < (2.0 * COUNTS_PER_INCH))
                        && speed > 0.1) {
                    Robot.getDrive().setLeftRightPower(Math.abs(speed * 0.3), Math.abs(speed * 0.3));
                }
            }
            // Stop all motion;
            Robot.getDrive().setLeftRightPower(0,0);

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

//        Robot.getSlammer().setPower(0.35);

        waitForTick(450);
        Robot.getSlammer().stop();
        Robot.getIntake().releaseIntake();
        Robot.getDrive().encoderDirectDrive(autoSpeed, -3, -3, 1);

//        Robot.getSlammer().setPower(-0.3);

        Robot.getDrive().encoderDrive(autoSpeed, 6,6, 1.5);
        Robot.getSlammer().stop();
        Robot.getDrive().encoderDrive(autoSpeed, -6, -6, 3);
    }

    public void scoreGlyph(){
        Robot.getSlammer().stopperUp();
        waitForTick(350);

//        Robot.getSlammer().setPower(0.35);

        waitForTick(500);
        Robot.getSlammer().stop();
        Robot.getIntake().releaseLock();
        Robot.getDrive().encoderDirectDrive(autoSpeed, -3, -3, 2);
        Robot.getDrive().encoderDrive(autoSpeed, 6, 6, 1.5);

//        Robot.getSlammer().setPower(-0.3);

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
}
