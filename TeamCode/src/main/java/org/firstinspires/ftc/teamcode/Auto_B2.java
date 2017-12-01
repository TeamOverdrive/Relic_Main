package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 11/11/2017.
 */
@Autonomous(name = "Auto Blue 2", group = "Auto")
//@Disabled
public class Auto_B2 extends AutoSuper{

    @Override
    public void runOpMode()
    {

        super.runOpMode();
        waitForStart();

        /*jewelArm.setPosition(0.8);
        pushJewel(BLUE);
        sleep(500);
        jewelArm.setPosition(0.1);
        sleep(500);*/
        encoderDrive(DRIVE_SPEED,24,24,5);
        turnR90();
        encoderDrive(DRIVE_SPEED, 12, 12, 3.0);
    }

}
