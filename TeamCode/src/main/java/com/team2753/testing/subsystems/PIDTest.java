package com.team2753.testing.subsystems;

import com.team2753.Team2753Linear;

import static com.team2753.auto.AutoParams.AUTO;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 3/5/2018.
 */

public class PIDTest extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("PID Test", AUTO, false);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            Robot.getDrive().encoderPIDDrive(this, 24, 24, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            Robot.getDrive().encoderPIDDrive(this,-24, -24, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            Robot.getDrive().encoderPIDDrive(this, 12, 12, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            Robot.getDrive().encoderPIDDrive(this, -12, -12, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            Robot.getDrive().turnPIDCCW(this, 90, 10, 0.05, 0, 0, 0.1);
            waitForTick(2000);
            Robot.getDrive().turnPIDCW(this, 90, 10, 0.05, 0, 0, 0.1);

            i++;
        }

        finalAction();
    }
}
