package org.firstinspires.ftc.teamcode.auto.SG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Team2753Linear;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.AUTO;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.RED;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.TELEOP;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.autoTurnSpeed;
import static org.firstinspires.ftc.teamcode.auto.AutoParams.jewelArmDelayMS;

/**
 * Created by David Zheng | FTC 2753 Team Overdrive on 1/10/2018.
 */

@Autonomous(name = "Red 2 CV", group = "CV")
@Disabled
public class R2_CV extends Team2753Linear {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart("R2 CV", AUTO, true);




        //score cryptokey
        SetStatus("Cryptokey");
        glyphScoreR2();

        //grab more glyphs
        SetStatus("Multiglyph");
        //multiGlyphPos2(13);

        //score extra glyphs

        //park
        SetStatus("Parking");



        finalAction();

    }
}
