package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/16/2017.
 */

@Autonomous
public class Auto_Test extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException {

        //this.startVuforia();

        waitForStart(this, true);

        //glyphLoad();

        int i = 0;

        while(opModeIsActive() && i == 0) {

            getJewel().deploy();
            sleep(300);

            jewelRed();

            getJewel().retract();
            sleep(300);

            switch (columnVote(this, 7)){
                case LEFT:
                    telemetry.addData("Column", "Left");
                    telemetry.update();
                    break;
                case CENTER:
                    telemetry.addData("Column", "Center");
                    telemetry.update();
                    break;
                case RIGHT:
                    telemetry.addData("Column", "Right");
                    telemetry.update();
                    break;
                case UNKNOWN:
                    telemetry.addData("Column", "Unknown");
                    telemetry.update();
                    break;
                }

            i++;
        }

        finalAction();

    }
}
