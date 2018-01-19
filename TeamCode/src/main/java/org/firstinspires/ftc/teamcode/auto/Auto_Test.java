package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Team2753Linear;

import static java.lang.Thread.sleep;
import static org.firstinspires.ftc.teamcode.AutoParams.RED;
import static org.firstinspires.ftc.teamcode.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelArmDelayMS;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurn;
import static org.firstinspires.ftc.teamcode.AutoParams.jewelTurnTimeoutS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 12/16/2017.
 */

@Autonomous
//@Disabled
public class Auto_Test extends Team2753Linear{
    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart(this, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            getDrive().turnCCW(90, autoTurnSpeed, 4);
            sleep(500);
            getDrive().turnCW(90, autoTurnSpeed, 4);
            sleep(1000);
            getDrive().encoderDrive(autoSpeed, -10, -10, 5);
            sleep(1000);
            getDrive().turnCCW(180, autoTurnSpeed, 4);
            sleep(500);
            getDrive().turnCW(180, autoTurnSpeed, 4);

            i++;
        }

        finalAction();

    }
}
