package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

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

            getJewel().deploy(); // Deploy Jewel mech
            sleep(300); // Wait for it to get there

            // Vote and then hit jewel off
            switch (getJewel().vote(this)) {
                case RED:
                    //getDrive().encoderDrive(0.2, -5, -5, 5);
                    //rotate clockwise


                    getJewel().retract(); // Retract Jewel arm
                    sleep(500);

                    //rotate counter-clockwise
                    break;
                case BLUE:
                    //getDrive().encoderDrive(0.2, 5, 5, 5);
                    //rotate counter-clockwise

                    getJewel().retract(); // Retract Jewel arm
                    sleep(500);

                    //rotate clockwise
                    break;
                default:
                    getJewel().retract(); // Retract Jewel arm
                    sleep(500);
            }


            //park

            i++;
        }

        finalAction();

    }
}
