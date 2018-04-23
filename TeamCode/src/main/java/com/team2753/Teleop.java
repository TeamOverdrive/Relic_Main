package com.team2753;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.team2753.libs.OverdriveLib;

import static com.team2753.auto.AutoParams.TELEOP;

/**
 * Created by joshua9889 on 12/10/2017.
 *
 * Replicated 2753's Teleop Main_Relic with new backend.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team2753Linear {

    private boolean stopper = true;
    private boolean lastPressed = false;
    private boolean deploy = false;

    private boolean intakeRelease = true;
    private boolean releaseLastPressed = false;
    private boolean release = true;

    private enum Relic{
        Retract, Extend
    }

    private Relic current = Relic.Retract;
    private boolean isRelicDeployed = false;

    private ElapsedTime t = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("Teleop", TELEOP, false);
        SetStatus("Teleop");

        Robot.getRelic().setAngles(0,0);
        Robot.getRelic().setWristPostion(65);
        Robot.getRelic().close();

        if(opModeIsActive()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(700);
                    Robot.getRelic().lock();
                }
            }).start();
        }

        waitForStart();


        // Loop while we are running Teleop
        while (opModeIsActive()) {

            /*Gamepad 1 Controls*/

            /* Drivetrain Controls */ //Gamepad 1 joysticks

            if(true){
                //D-pad controls for slower movement
                if (Math.abs(Ryan.right_stick_y) < 0.01 && Math.abs(Ryan.left_stick_y) < 0.01) {
                    if (Ryan.dpad_up) {
                        Robot.getDrive().setLeftRightPowers(-0.3, -0.3);
                    } else if (Ryan.dpad_down) {
                        Robot.getDrive().setLeftRightPowers(0.3, 0.3);
                    } else if (Ryan.dpad_left) {
                        Robot.getDrive().setLeftRightPowers(-0.35, 0.35);
                    } else if (Ryan.dpad_right) {
                        Robot.getDrive().setLeftRightPowers(0.35, -0.35);
                    } else {
                        Robot.getDrive().setLeftRightPowers(0, 0);
                    }
                } else {
                    float leftThrottle = Ryan.left_stick_y;
                    float rightThrottle = Ryan.right_stick_y;

                    /* Clip the left and right throttle values so that they never exceed +/- 1.  */
                    leftThrottle = Range.clip(leftThrottle, -1, 1);
                    rightThrottle = Range.clip(rightThrottle, -1, 1);

                    /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
                    leftThrottle = (float) OverdriveLib.scaleInput(leftThrottle);
                    rightThrottle = (float) OverdriveLib.scaleInput(rightThrottle);

                    Robot.getDrive().setLeftRightPowers(leftThrottle, rightThrottle);
                }
            } else {
                double speed = Ryan.left_stick_y;
                double turn = -Ryan.right_stick_x;

                speed = Range.clip(speed, -1, 1);
                turn = Range.clip(turn, -1, 1);

                double left = speed + turn;
                double right = speed - turn;

                Robot.getDrive().setLeftRightPowers(left, right);
            }

            /* Intake Controls */

            //Both Gamepad 1 and 2
            //Press and hold control


            if (Ryan.left_bumper)
                Robot.getIntake().reverse();
            else if (Ryan.right_bumper)
                Robot.getIntake().intake();

            else if (Ryan.left_trigger > 0)
                Robot.getIntake().shiftLeft();

            else if (Ryan.right_trigger > 0)
                Robot.getIntake().shiftRight();
            else
                Robot.getIntake().stop();

            if(Ryan.x){
                Robot.getJewel().deploy(true);
            }
            else{
                Robot.getJewel().retract(true);
            }

            //Intake Release
            if (Ryan.y) {
                if (releaseLastPressed != intakeRelease) {
                    if (release) {
                        Robot.getIntake().releaseIntake();
                        release = false;
                    } else {
                        Robot.getIntake().releaseLock();
                        release = true;
                    }
                }
                releaseLastPressed = intakeRelease;
            }
            else {
                releaseLastPressed = !intakeRelease;
            }


            /*  Gamepad 2 Controls  */

            /*Lift Control  Gamepad 2 Left Joystick*/

            Robot.getLift().setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            float liftThrottle = Seth.left_stick_y;
            //CLip
            liftThrottle = Range.clip(liftThrottle, -1, 1);
            //Scale
            liftThrottle = (float) OverdriveLib.scaleInput(liftThrottle);
            //Invert
            liftThrottle = liftThrottle * -1;
            //Apply power to motor
            Robot.getLift().setLiftPower(liftThrottle);

            //Slammer
            if (Seth.y) {
                Robot.getSlammer().setPower(0.35);
            } else if (Seth.a) {
                Robot.getSlammer().setPower(-0.2);
            } else
                Robot.getSlammer().setPower(0);



            //Stopper
            if(Seth.right_bumper){
                if(lastPressed!=stopper){
                    if(deploy) {
                        Robot.getSlammer().stopperDown();
                        if(isRelicDeployed){
                            Robot.getRelic().open();
                        }
                        deploy = false;
                    } else {
                        Robot.getSlammer().stopperUp();
                        if(isRelicDeployed)
                            Robot.getRelic().close();
                        deploy = true;
                    }
                }
                lastPressed = stopper;
            } else {
                lastPressed = !stopper;
            }

            SetStatus("Running OpMode");
            updateTelemetry();

            if(gamepad2.dpad_down){
                Robot.getRelic().setAngles(0, 190);
                Robot.getRelic().setWristPostion(34);
                Robot.getRelic().open();
                current = Relic.Retract;
                isRelicDeployed = true;
            } else if(gamepad2.dpad_up){
                Robot.getRelic().setAngles(140, 190);
                Robot.getRelic().setWristPostion(50);
                Robot.getRelic().close();
                current = Relic.Extend;
                isRelicDeployed = true;
            } else if(gamepad2.dpad_right){
                Robot.getRelic().setAngles(0,0);
                Robot.getRelic().setWristPostion(50);
                Robot.getRelic().close();
                current = Relic.Retract;
                isRelicDeployed = true;
            } else if(gamepad2.dpad_left){
                Robot.getRelic().setAngles(0,0);
                Robot.getRelic().setWristPostion(70);
                isRelicDeployed = false;
            }

            if(current == Relic.Extend){
                if(t.milliseconds()<2000){
                    Robot.getRelic().setWristPostion(50);
                }else if(t.milliseconds()>2000) {
                    Robot.getRelic().setWristPostion(35);
                }
            } else {
                t.reset();
            }

            SetStatus("Running OpMode");

            updateTelemetry();
        }


        finalAction();
    }
}
