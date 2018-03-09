package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.subsystems.Drive.COUNTS_PER_INCH;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/30/2018.
 */

@Autonomous(name = "Auto Test", group = "test")
@Disabled
public class fullAutoTest extends Team2753Linear{

    //Runs position b1 for now bc its the closest on test field

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("B1 Multiglyph", AUTO, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {


            //score cryptokey
            SetStatus("Cryptokey");
            glyphScoreB1(this);

            //grab more glyphs
            SetStatus("Multiglyph");
            telemetry.update();
            multiGlyphPos1();

            getDrive().encoderDrive(autoSpeed, -4, -4, 3);


            //score extra glyphs

            //park
            SetStatus("Parking");
            telemetry.update();

            i++;
        }

        finalAction();
    }
}
