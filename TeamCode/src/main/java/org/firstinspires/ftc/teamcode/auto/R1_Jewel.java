package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.VuMark;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/12/2017.
 */

@Autonomous(name = "Red 1 Jewel")
public class R1_Jewel extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {

        //Init
        //startVuforia();

        waitForStart(this, true);

        getJewel().deploy();
        sleep(300);

        switch (getJewel().vote(this)){
            case RED:
                getDrive().encoderDrive(0.2, -5, -5, 5);
                break;
            case BLUE:
                getDrive().encoderDrive(0.2, 5, 5, 5);
                break;
        }

        getJewel().retract(); // Retract Jewel mech
        sleep(500);

        getDrive().encoderDrive(0.8, 35, 35, 10);

        finalAction();
    }
}
