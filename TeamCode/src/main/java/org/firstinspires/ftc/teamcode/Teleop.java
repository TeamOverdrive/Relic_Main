package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.libs.OverdriveLib;

import static org.firstinspires.ftc.teamcode.auto.AutoParams.TELEOP;

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

    private Relic current = Teleop.Relic.Retract;
    private boolean isRelicDeployed = false;

    private ElapsedTime t = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("Teleop", TELEOP, false);
        SetStatus("Teleop");

        getRelic().setAngles(0,0);
        getRelic().setWristPostion(65);
        getRelic().close();

        if(opModeIsActive()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sleep(700);
                    getRelic().lock();
                }
            }).start();
        }

        // Loop while we are running Teleop
        while (opModeIsActive()) {

            /*Gamepad 1 Controls*/

            /* Drivetrain Controls */ //Gamepad 1 joysticks

            if(true){
                //D-pad controls for slower movement
                if (Math.abs(Ryan.right_stick_y) < 0.01 && Math.abs(Ryan.left_stick_y) < 0.01) {
                    if (Ryan.dpad_up) {
                        getDrive().setLeftRightPowers(-0.3, -0.3);
                    } else if (Ryan.dpad_down) {
                        getDrive().setLeftRightPowers(0.3, 0.3);
                    } else if (Ryan.dpad_left) {
                        getDrive().setLeftRightPowers(-0.35, 0.35);
                    } else if (Ryan.dpad_right) {
                        getDrive().setLeftRightPowers(0.35, -0.35);
                    } else {
                        getDrive().setLeftRightPowers(0, 0);
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

                    getDrive().setLeftRightPowers(leftThrottle, rightThrottle);
                }
            } else {
                double speed = Ryan.left_stick_y;
                double turn = -Ryan.right_stick_x;

                speed = Range.clip(speed, -1, 1);
                turn = Range.clip(turn, -1, 1);

                double left = speed + turn;
                double right = speed - turn;

                getDrive().setLeftRightPowers(left, right);
            }

            /* Intake Controls */

            //Both Gamepad 1 and 2
            //Press and hold control


            if (Ryan.left_bumper)
                getIntake().reverse();
            else if (Ryan.right_bumper)
                getIntake().intake();

            else if (Ryan.left_trigger > 0)
                getIntake().shiftLeft();

            else if (Ryan.right_trigger > 0)
                getIntake().shiftRight();
            else
                getIntake().stop();

            if(Ryan.x){
                getJewel().deploy(true);
            }
            else{
                getJewel().retract(true);
            }

            //Intake Release
            if (Ryan.y) {
                if (releaseLastPressed != intakeRelease) {
                    if (release) {
                        getIntake().releaseIntake();
                        release = false;
                    } else {
                        getIntake().releaseLock();
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

            getLift().setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            float liftThrottle = Seth.left_stick_y;
            //CLip
            liftThrottle = Range.clip(liftThrottle, -1, 1);
            //Scale
            liftThrottle = (float) OverdriveLib.scaleInput(liftThrottle);
            //Invert
            liftThrottle = liftThrottle * -1;
            //Apply power to motor
            getLift().setLiftPower(liftThrottle);

            //Slammer
            if (Seth.y) {
                getSlammer().setPower(0.35);
            } else if (Seth.a) {
                getSlammer().setPower(-0.2);
            } else
                getSlammer().setPower(0);



            //Stopper
            if(Seth.right_bumper){
                if(lastPressed!=stopper){
                    if(deploy) {
                        getSlammer().stopperDown();
                        if(isRelicDeployed){
                            getRelic().open();
                        }
                        deploy = false;
                    } else {
                        getSlammer().stopperUp();
                        if(isRelicDeployed)
                            getRelic().close();
                        deploy = true;
                    }
                }
                lastPressed = stopper;
            } else {
                lastPressed = !stopper;
            }

            getJewel().retract(true);

            SetStatus("Running OpMode");
            updateTelemetry();

            if(gamepad2.dpad_down){
                getRelic().setAngles(0, 190);
                getRelic().setWristPostion(34);
                getRelic().open();
                current = Teleop.Relic.Retract;
                isRelicDeployed = true;
            } else if(gamepad2.dpad_up){
                getRelic().setAngles(140, 190);
                getRelic().setWristPostion(50);
                getRelic().close();
                current = Teleop.Relic.Extend;
                isRelicDeployed = true;
            } else if(gamepad2.dpad_right){
                getRelic().setAngles(0,0);
                getRelic().setWristPostion(50);
                getRelic().close();
                current = Teleop.Relic.Retract;
                isRelicDeployed = true;
            } else if(gamepad2.dpad_left){
                getRelic().setAngles(0,0);
                getRelic().setWristPostion(70);
                isRelicDeployed = false;
            }

            if(current == Teleop.Relic.Extend){
                if(t.milliseconds()<2000){
                    getRelic().setWristPostion(50);
                }else if(t.milliseconds()>2000) {
                    getRelic().setWristPostion(35);
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
