package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

import static java.lang.Math.abs;
import static java.lang.Math.round;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.TELEOP;

/**
 * Created by joshua9889 on 12/10/2017.
 *
 * Replicated 2753's Teleop Main_Relic with new backend.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team2753Linear {

    private enum intakeState{
        OFF,
        INTAKE,
        REVERSE
    }

    private int releaseState = 0;

    org.firstinspires.ftc.teamcode.subsystems.Lift.liftState wantedState = org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.INTAKING;

    @Override
    public void runOpMode() throws InterruptedException {

        //Set up Telemetry
        telemetry.setAutoClear(true);
        Telemetry.Item status = telemetry.addData("Status", "Initializing");
        Telemetry.Item currentOpMode = telemetry.addData("Running", "UNKOWN");
        Telemetry.Item phase = telemetry.addData("Phase", "Init Routine");
        telemetry.update();

        //Initialize Robot
        status.setValue("Initializing...");
        currentOpMode.setValue("Teleop");
        telemetry.update();
        initializeRobot(this, TELEOP);
        intakeState currentIntakeState = intakeState.OFF;

        //Waiting for Start
        status.setValue("Initialized, Waiting for Start");
        telemetry.update();
        waitForStart(this);
        status.setValue("Running OpMode");
        currentOpMode.setValue("Teleop");
        phase.setValue("Driver Control");
        telemetry.update();

        // Loop while we are running Teleop
        while (opModeIsActive()) {
/*
                     _______                                    _______
                  __/_______\_____                       ______/_______\__
                /                 \                     /                  \
               /                   \___________________/                    \
              |         __                                                   |
              |      __|  |__                                 ( Y )          |
              |     |__    __|                           ( X )     ( B )     |
             |         |__|                                   ( A )           |
             |                                                                |
             |                                                                |
             |                                                                |
            |                                                                  |
            |                                                                  |
            |                                                                  |
            |                                                                  |
           |                                                                    |
           |                                                                    |
           |                                                                    |
           |                                                                    |
           |                                                                    |
            \________/                                                \________/
*/

            /*Gamepad 1 Controls*/

            /* Drivetrain Controls */ //Gamepad 1 joysticks
            float leftThrottle = gamepad1.left_stick_y;
            float rightThrottle = gamepad1.right_stick_y;

            /* Clip the left and right throttle values so that they never exceed +/- 1.  */
            leftThrottle = Range.clip(leftThrottle, -1, 1);
            rightThrottle = Range.clip(rightThrottle, -1, 1);

            /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
            leftThrottle = (float) OverdriveLib.scaleInput(leftThrottle);
            rightThrottle = (float) OverdriveLib.scaleInput(rightThrottle);

            getDrive().setLeftRightPowers(leftThrottle, rightThrottle);

            //D-pad controls for slower movement
            if (Math.abs(leftThrottle) == 0 && Math.abs(rightThrottle) == 0) {
                if (gamepad1.dpad_up) {
                    getDrive().setLeftRightPowers(-0.3, -0.3);
                    waitForTick(200);
                } else if (gamepad1.dpad_down) {
                    getDrive().setLeftRightPowers(0.3, 0.3);
                    waitForTick(200);
                } else if (gamepad1.dpad_left) {
                    getDrive().setLeftRightPowers(0.35, -0.35);
                    waitForTick(200);
                } else if (gamepad1.dpad_right) {
                    getDrive().setLeftRightPowers(-0.35, 0.35);
                    waitForTick(200);
                } else {
                    getDrive().setLeftRightPowers(0, 0);
                }
            }


            /* Intake Controls */

            //Both Gamepad 1 and 2
            //Press and hold control


            if (gamepad1.left_bumper)
                getIntake().reverse();
            else if (gamepad1.right_bumper)
                getIntake().intake();
            /*
            else if (gamepad1.left_trigger > 0)
                getIntake().shiftLeft();
                */
            else if (gamepad1.right_trigger > 0)
                getIntake().shiftRight();
            else if (gamepad2.left_trigger > 0)
                getIntake().reverse();
             else if (gamepad2.right_trigger > 0)
                getIntake().intake();
            else
                getIntake().stop();


            //Fancy Intake FSM Controls

            /*
            //Change states depending on what button is pushed and what the current state is
            if(gamepad1.left_bumper){
                switch (currentIntakeState){
                    case OFF:
                        currentIntakeState = intakeState.REVERSE;
                        break;
                    case INTAKE:
                        currentIntakeState = intakeState.REVERSE;
                        break;
                    case REVERSE:
                        currentIntakeState = intakeState.OFF;
                        break;
                }
            }

            if(gamepad1.right_bumper){
                switch (currentIntakeState){
                    case OFF:
                        currentIntakeState = intakeState.INTAKE;
                        break;
                    case INTAKE:
                        currentIntakeState = intakeState.OFF;
                        break;
                    case REVERSE:
                        currentIntakeState = intakeState.INTAKE;
                        break;
                }
            }

            //set the motor based on the state
            switch(currentIntakeState){
                case OFF:
                    getIntake().stop();
                    break;
                case INTAKE:
                    getIntake().intake();
                    break;
                case REVERSE:
                    getIntake().reverse();
                    break;
            }
            */

            //Jewel Arm
            if (gamepad1.left_trigger > 0)
                getJewel().deploy();
            else
                getJewel().retract();


            //Intake Release

            if (gamepad1.y) {
                getIntake().releaseIntake();
            }
            else if (gamepad1.x){
                getIntake().releaseLock();
            }

            /*  Gamepad 2 Controls  */

            /*Lift Control  Gamepad 2 Left Joystick*/




                if(Math.abs(gamepad2.left_stick_y)<0.01){
                    if(gamepad2.dpad_up){
                        getSlammer().stopperUp();
                        wantedState = org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.UPPER;
                        getIntake().stop();
                    }
                    else if (gamepad2.dpad_down){
                        wantedState = org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.INTAKING;
                    }

                    getLift().goTo(wantedState);
                } else {
                    getLift().setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                    float liftThrottle = gamepad2.left_stick_y;
                    //CLip
                    liftThrottle = Range.clip(liftThrottle, -1, 1);
                    //Scale
                    liftThrottle = (float) OverdriveLib.scaleInput(liftThrottle);
                    //Invert
                    liftThrottle = liftThrottle * -1;
                    //Apply power to motor
                    getLift().setLiftPower(liftThrottle);
                }



            //Slammer
            if (gamepad2.y) {
                getSlammer().setPower(0.35);
            } else if (gamepad2.a) {
                getSlammer().setPower(-0.2);
            } else
                getSlammer().setPower(0);



            //Stopper
            if (gamepad2.left_bumper)
                getSlammer().stopperUp();
            else if (gamepad2.right_bumper)
                getSlammer().stopperDown();

            //Phone servo test

            /*
            if(gamepad2.right_bumper)
                getPhoneServo().jewelPosition();
            else
                getPhoneServo().initPosition();
            */



            status.setValue("Running OpMode");
            currentOpMode.setValue("Teleop");
            phase.setValue("Driver Control");
            updateTelemetry(this);

        }

        finalAction();
    }
}
