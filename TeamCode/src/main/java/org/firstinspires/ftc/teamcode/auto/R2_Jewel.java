package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;

/**
 * Same as Auto_B1, but with new backend
 * See this for an example(it's a little jank, but it works OK): http://bit.ly/2AtqtkR
 * Created by joshua9889 on 12/10/2017.
 */
@Autonomous(name = "Red 2 Jewel")
public class R2_Jewel extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true); // Wait for start

        int i = 0;

        while(opModeIsActive() && i == 0) {

            getHand().grabFrontClose();
            sleep(300);

            getJewel().deploy(); // Deploy Jewel mech
            sleep(300); // Wait for it to get there

            // Vote and then hit jewel off
            jewelRed();

            //park
            getDrive().encoderDrive(0.8, 24, 24, 7);
            getDrive().turnCCW(45, 0.3,4);
            getDrive().encoderDrive(0.7, 17, 17, 4);

            i++;
            getJewel().retract();
            sleep(1000);
        }

        finalAction();
    }
}
