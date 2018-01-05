package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/12/2017.
 */

@Autonomous(name = "Blue 2 Jewel")
public class B2_Jewel extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true);

        int i = 0;

        while (opModeIsActive() && i == 0) {

            getHand().frontClose();
            sleep(300);


            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            jewelBlue();

            //park
            getDrive().encoderDrive(0.8, -24, -24, 4);
            getDrive().turnCW(45, 4);
            getDrive().encoderDrive(0.8, -17, -17, 4);

            i++;
        }


        finalAction();
    }
}
