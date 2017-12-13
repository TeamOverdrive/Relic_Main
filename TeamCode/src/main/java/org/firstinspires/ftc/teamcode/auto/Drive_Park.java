package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.Team2753Linear;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/12/2017.
 */

public class Drive_Park extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true);

        getDrive().encoderDrive(0.6, 30, 30, 10);
        sleep(1000);

        finalAction();
    }
}
