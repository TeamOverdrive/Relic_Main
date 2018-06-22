package com.team2753.testing.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team2753.Team2753Linear;

import static com.team2753.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/30/2018.
 */

@Autonomous(name = "Drive Test", group = "test")
@Disabled
public class DriveTurn extends Team2753Linear{

    //Runs position b1 for now bc its the closest on test field
    private static final double turnSpeed = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("Drive Test", AUTO);

        int i = 0;

            while(opModeIsActive() && i == 0) {

                Robot.getDrive().encoderProportionDrive(0.00625, 0, 0.1, 24, 24, 10);
                waitForTick(2000);
                Robot.getDrive().encoderProportionDrive(0.00625, 0, 0.1, -24, -24, 10);
                waitForTick(2000);
                Robot.getDrive().encoderProportionDrive(0.00625, 0, 0.1, 3, 3, 10);
                waitForTick(2000);
                Robot.getDrive().encoderProportionDrive(0.00625, 0, 0.1, -3, -3, 10);
                waitForTick(2000);
                Robot.getDrive().turnProportionCCW(90, 10);
                waitForTick(2000);
                Robot.getDrive().turnProportionCW(90, 10);

            i++;
        }

        finalAction();
    }
}
