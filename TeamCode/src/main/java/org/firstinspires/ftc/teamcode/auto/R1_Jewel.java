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


        int i = 0;

        while(opModeIsActive() && i == 0) {

            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            switch (getJewel().vote(this, 10)) {
                case RED:
                    //getDrive().encoderDrive(0.4, -5, -5, 5);
                    //rotate clockwise
                    getDrive().turnCW(jewelTurn, 4);


                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);

                    //rotate counter-clockwise
                    getDrive().turnCCW(jewelTurn, 4);
                    break;
                case BLUE:
                    //getDrive().encoderDrive(0.4, 5, 5, 5);
                    //rotate counter-clockwise
                    getDrive().turnCCW(jewelTurn, 4);

                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);

                    //rotate clockwise
                    getDrive().turnCW(jewelTurn, 4);
                    break;
                case UNKNOWN:
                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);
                    break;
                default:
                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);
            }

            //park
            getJewel().retract();
            sleep(1000);
            getDrive().encoderDrive(0.8, 30, 30, 10);

            i++;
        }

        finalAction();
    }
}
