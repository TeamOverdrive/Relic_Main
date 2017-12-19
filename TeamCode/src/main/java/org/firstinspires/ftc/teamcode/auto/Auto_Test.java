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

        this.startVuforia();

        waitForStart(this, true);

        //glyphLoad();

        int i = 0;

        while(opModeIsActive() && i == 0) {

            getJewel().deploy();
            sleep(300);

            // Vote and then hit jewel off
            switch (getJewel().vote(this, 7)) {

                case RED:
                    //rotate clockwise
                    getDrive().turnCW(40, 4);

                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);

                    //rotate counter-clockwise
                    getDrive().turnCCW(40, 4);
                    break;

                case BLUE:
                    //rotate counter-clockwise
                    getDrive().turnCCW(40, 4);

                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);

                    //rotate clockwise
                    getDrive().turnCW(40, 4);
                    break;

                default:
                    //not used because jewel vote does not have a timeout
                    getJewel().retract(); // Retract Jewel arm
                    sleep(750);
                    break;
            }

            switch (columnVote()){
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



            //park
            //getDrive().encoderDrive(0.8, 35, 35, 10);
            //getDrive().turnCW(90, 4);

            i++;
        }

        finalAction();

    }
}
