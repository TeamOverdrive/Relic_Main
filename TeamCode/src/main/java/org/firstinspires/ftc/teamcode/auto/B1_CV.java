package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.BLUE;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */
@Autonomous(name = "Blue 1 CV", group = "CV")
public class B1_CV extends Team2753Linear {

    @Override
    public void runOpMode() throws InterruptedException {


        waitForStart("B1 CV", AUTO, true);

        int i = 0;

        while(opModeIsActive() && i == 0) {

            //score cryptokey
            SetStatus("Cryptokey");
            glyphScoreB1(this);


            //grab more glyphs
            SetStatus("Multiglyph");
            //multiGlyphPos1(20);

            //park
            SetStatus("Parking");
            telemetry.update();

            i++;
        }

        finalAction();
    }
}
