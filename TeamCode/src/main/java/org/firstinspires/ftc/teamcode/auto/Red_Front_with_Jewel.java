package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

/**
 * Same as Auto_B1, but with new backend
 * See this for an example(it's a little jank, but it works OK): http://bit.ly/2AtqtkR
 * Created by joshua9889 on 12/10/2017.
 */
@Autonomous(group = "9889")
public class Red_Front_with_Jewel extends Team2753Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true); // Wait for start

        getJewel().deploy(); // Deploy Jewel mech
        sleep(300); // Wait for it to get there

        // Vote and then hit it off
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

        finalAction();

    }
}