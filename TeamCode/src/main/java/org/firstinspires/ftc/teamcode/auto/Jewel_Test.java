package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/16/2017.
 */

@Autonomous
public class Jewel_Test extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {

        //Init
        //startVuforia();

        waitForStart(this, true);


        int i = 0;

        while(opModeIsActive() && i == 0) {

            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            switch (getJewel().vote(this)) {

                case RED:
                    //rotate clockwise
                    getDrive().turnCW(30, 4);

                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);

                    //rotate counter-clockwise
                    getDrive().turnCCW(30, 4);
                    break;

                case BLUE:
                    //rotate counter-clockwise
                    getDrive().turnCCW(30, 4);

                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);

                    //rotate clockwise
                    getDrive().turnCW(30, 4);
                    break;

                default:
                    //not used because jewel vote does not have a timeout
                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);
            }

            //park
            getDrive().encoderDrive(0.8, 35, 35, 10);

            i++;
        }

        finalAction();

    }
}
