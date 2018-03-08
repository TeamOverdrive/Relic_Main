package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.libs.OverdriveLib;

import static org.firstinspires.ftc.teamcode.auto.AutoParams.TELEOP;
import org.firstinspires.ftc.teamcode.subsystems.Lift.liftState;

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

    private liftState wantedState = liftState.INTAKING;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("Teleop", TELEOP, false);
        SetStatus("Teleop");

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
            float leftThrottle = Ryan.left_stick_y;
            float rightThrottle = Ryan.right_stick_y;

            /* Clip the left and right throttle values so that they never exceed +/- 1.  */
            leftThrottle = Range.clip(leftThrottle, -1, 1);
            rightThrottle = Range.clip(rightThrottle, -1, 1);

            /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
            leftThrottle = (float) OverdriveLib.scaleInput(leftThrottle);
            rightThrottle = (float) OverdriveLib.scaleInput(rightThrottle);

            getDrive().setLeftRightPowers(leftThrottle, rightThrottle);

            //D-pad controls for slower movement
            if (Math.abs(leftThrottle) == 0 && Math.abs(rightThrottle) == 0) {
                if (Ryan.dpad_up) {
                    getDrive().setLeftRightPowers(-0.3, -0.3);
                    waitForTick(200);
                } else if (Ryan.dpad_down) {
                    getDrive().setLeftRightPowers(0.3, 0.3);
                    waitForTick(200);
                } else if (Ryan.dpad_left) {
                    getDrive().setLeftRightPowers(-0.35, 0.35);
                    waitForTick(200);
                } else if (Ryan.dpad_right) {
                    getDrive().setLeftRightPowers(0.35, -0.35);
                    waitForTick(200);
                } else {
                    getDrive().setLeftRightPowers(0, 0);
                }
            }


            /* Intake Controls */

            //Both Gamepad 1 and 2
            //Press and hold control


            if (Ryan.left_bumper)
                getIntake().reverse();
            else if (Ryan.right_bumper)
                getIntake().intake();
            /*
            else if (Ryan.left_trigger > 0)
                getIntake().shiftLeft();
                */
            else if (Ryan.right_trigger > 0)
                getIntake().shiftRight();
            else if (Seth.left_trigger > 0)
                getIntake().reverse();
             else if (Seth.right_trigger > 0)
                getIntake().intake();
            else
                getIntake().stop();


            //Fancy Intake FSM Controls

            /*
            //Change states depending on what button is pushed and what the current state is
            if(Ryan.left_bumper){
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

            if(Ryan.right_bumper){
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
//            if (Ryan.left_trigger > 0)
//                getJewel().deploy();
//            else
            getJewel().retract();


            //Intake Release

            if (Ryan.y) {
                getIntake().releaseIntake();
            }
            else if (Ryan.x){
                getIntake().releaseLock();
            }

            /*  Gamepad 2 Controls  */

            /*Lift Control  Gamepad 2 Left Joystick*/
            if(Math.abs(Seth.left_stick_y)<0.01){
                if(Seth.dpad_up){
                    getSlammer().stopperUp();
                    wantedState = org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.UPPER;
                    getIntake().stop();
                }
                else if (Seth.dpad_down){
                    wantedState = org.firstinspires.ftc.teamcode.subsystems.Lift.liftState.INTAKING;
                }

                getLift().goTo(wantedState);
            } else {
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
            }


            //Slammer
            if (Seth.y) {
                getSlammer().setPower(0.35);
            } else if (Seth.a) {
                getSlammer().setPower(-0.2);
            } else
                getSlammer().setPower(0);



            //Stopper
            if (Seth.left_bumper)
                getSlammer().stopperUp();
            else if (Seth.right_bumper)
                getSlammer().stopperDown();

            //Phone servo test

            /*
            if(Seth.right_bumper)
                getPhoneServo().jewelPosition();
            else
                getPhoneServo().initPosition();
            */



            SetStatus("Running OpMode");
            updateTelemetry();

        }

        finalAction();
    }
}
