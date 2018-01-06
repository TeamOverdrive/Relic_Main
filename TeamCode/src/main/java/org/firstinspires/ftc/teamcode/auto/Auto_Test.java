package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static java.lang.Thread.sleep;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelArmDelayMS;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurnTimeoutS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/16/2017.
 */

@Autonomous
public class Auto_Test extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart(this, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            getHand().grabBackClose();
            sleep(300);
            initialLift();

            getJewel().deploy();
            sleep(jewelArmDelayMS);

            jewelRed();

            getJewel().retract();
            sleep(jewelArmDelayMS);



            switch (columnVote(this, 7)){

                case LEFT:
                    telemetry.addData("Column", "Left");
                    telemetry.update();

                    getDrive().encoderDrive(0.3, 42, 42, 4);
                    getDrive().turnCW(90, 0.3, 4);
                    getDrive().encoderDrive(0.3, 20, 20, 4);

                    //put glyph into left column
                    break;

                case CENTER:
                    telemetry.addData("Column", "Center");
                    telemetry.update();

                    getDrive().encoderDrive(0.3, 36, 36, 4);
                    getDrive().turnCW(90, 0.3, 4);
                    getDrive().encoderDrive(0.3, 20, 20, 4);
                    //sleep(5000);

                    //put glyph into center column
                    break;

                case RIGHT:
                    telemetry.addData("Column", "Right");
                    telemetry.update();

                    getDrive().encoderDrive(0.3, 30, 30, 4);
                    getDrive().turnCW(90, 0.3,4);
                    getDrive().encoderDrive(0.3, 20, 20, 4);

                    //sleep(5000);

                    //put glyph into right column
                    break;
                case UNKNOWN:
                    telemetry.addData("Column", "Unknown");
                    telemetry.update();

                    getDrive().encoderDrive(0.3, 36, 36, 4);
                    getDrive().turnCW(90, 0.3, 4);
                    getDrive().encoderDrive(0.3, 20, 20, 4);

                    //sleep(5000);

                    //put glyph into center column
                    break;
            }

            getDrive().encoderDrive(0.3, -36, -36, 4);
            getHand().grabFrontClose();
            getDrive().encoderDrive(0.3, 6, 6, 2);
            getDrive().turnCCW(180, 0.3, 3);


            i++;
        }

        finalAction();

    }
}
