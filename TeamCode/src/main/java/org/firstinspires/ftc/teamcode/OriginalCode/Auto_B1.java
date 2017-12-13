package org.firstinspires.ftc.teamcode.OriginalCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 11/11/2017.
 */

@Autonomous(name = "Auto Blue 1", group = "Auto")
@Disabled
public class Auto_B1 extends AutoSuper{

    @Override
    public void runOpMode()
    {

        super.runOpMode();
        waitForStart();


        pushJewel(BLUE);
        sleep(500);

        encoderDrive(DRIVE_SPEED,30,30,5);


    }

}
