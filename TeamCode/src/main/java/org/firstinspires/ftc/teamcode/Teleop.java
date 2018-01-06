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

@TeleOp
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

            //Jewel Test  Gamepad 1 LB
            if(gamepad1.left_bumper)
                getJewel().deploy();
            else
                getJewel().retract();




            /*Gamepad 2 Controls*/

            // Hand

            if(gamepad2.left_bumper){getHand().grabFrontOpen();}

            else if(gamepad2.left_trigger>0){getHand().grabFrontClose();}

            else if (gamepad2.right_bumper){getHand().grabBackOpen();}

            else if (gamepad2.right_trigger>0){getHand().grabBackClose();}


            //Lift Control  Gamepad 2 Left Joystick
            float liftThrottle = gamepad2.left_stick_y;
            //CLip
            liftThrottle = Range.clip(liftThrottle, -1, 1);
            //Scale
            liftThrottle = (float) OverdriveLib.scaleInput(liftThrottle);
            //Invert
            liftThrottle = liftThrottle*-1;

            getLift().setLiftPower(liftThrottle);


            //Slide Control Gamepad 2 Right Joystick
            float slideThrottle = gamepad2.right_stick_y;
            //Clip
            slideThrottle = Range.clip(slideThrottle, -1, 1);
            //Scale
            slideThrottle = (float) OverdriveLib.scaleInput(slideThrottle);
            //set to slide motor
            getLift().setSlidePower(slideThrottle);

            updateTelemetry(this);

        }

        finalAction();
    }
}
