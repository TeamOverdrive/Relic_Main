package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

import static java.lang.Math.abs;
import static java.lang.Math.round;

/**
 * Created by joshua9889 on 12/10/2017.
 *
 * Replicated 2753's TeleopMain_Relic with new backend.
 *Edited by David Zheng | 2753 Team Overdrive
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart(this, false);

        // Loop while we are running Teleop
        while (opModeIsActive()){
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


            /*Gamepad 1 Controls*/

            /* Drivetrain Controls */ //Gamepad 1 joysticks
            float leftThrottle = gamepad1.left_stick_y;
            float rightThrottle = gamepad1.right_stick_y;

            /* Clip the left and right throttle values so that they never exceed +/- 1.  */
            leftThrottle = Range.clip(leftThrottle,-1,1);
            rightThrottle = Range.clip(rightThrottle,-1,1);

            /* Scale the throttle values to make it easier to control the robot more precisely at slower speeds.  */
            leftThrottle = (float) OverdriveLib.scaleInput(leftThrottle);
            rightThrottle = (float) OverdriveLib.scaleInput(rightThrottle);

            getDrive().setLeftRightPowers(leftThrottle, rightThrottle);

            //D-pad controls for more precise movement
            if (Math.abs(leftThrottle) == 0 && Math.abs(rightThrottle) == 0) {
                if (gamepad1.dpad_up) {
                    getDrive().setLeftRightPowers(-0.3, -0.3);
                    sleep(250);
                }
                else if (gamepad1.dpad_down) {
                    getDrive().setLeftRightPowers(0.3, 0.3);
                    sleep(250);
                }
                else {
                    getDrive().setLeftRightPowers(0,0);
                }
            }



            /*Gamepad 2 Controls*/

            /*Grabber Controls*/
            if(gamepad2.right_bumper){getHand().grabFrontOpen();}
            if(gamepad2.right_trigger>0){getHand().grabFrontClose();}

            if (gamepad2.left_bumper){getHand().grabBackOpen();}
            if (gamepad2.left_trigger>0){getHand().grabBackClose();}

            if (gamepad2.b){getHand().grabFrontStop();}
            if (gamepad2.x){getHand().grabBackStop();}


            /*Lift Control  Gamepad 2 Left Joystick*/
            float liftThrottle = gamepad2.left_stick_y;
            //CLip
            liftThrottle = Range.clip(liftThrottle, -1, 1);
            //Scale
            liftThrottle = (float) OverdriveLib.scaleInput(liftThrottle);
            //Invert
            liftThrottle = liftThrottle*-1;
            //Apply power to motor
            getLift().setLiftPower(liftThrottle);


            updateTelemetry(this);

        }

        finalAction();
    }
}
