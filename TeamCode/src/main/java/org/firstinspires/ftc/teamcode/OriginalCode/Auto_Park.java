package org.firstinspires.ftc.teamcode.OriginalCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/10/2017.
 */


@Autonomous(name = "Auto Park", group = "Auto")
@Disabled
public class Auto_Park extends AutoSuper{

    @Override
    public void runOpMode() {
        super.runOpMode();
        waitForStart();
        encoderDrive(0.75, 36, 36, 10);
        sleep(5000);
    }


}
