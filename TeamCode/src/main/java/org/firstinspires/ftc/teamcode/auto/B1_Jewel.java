package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/12/2017.
 */

@Autonomous(name = "Blue 1 Jewel")
public class B1_Jewel extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true);

        getJewel().deploy();
        sleep(300);

        switch (getJewel().vote(this)){
            case RED:
                getDrive().encoderDrive(0.2, 5, 5, 5);
                break;
            case BLUE:
                getDrive().encoderDrive(0.2, -5, -5, 5);
                break;
        }

        getJewel().retract();
        sleep(500);

        //park

        finalAction();
    }
}
