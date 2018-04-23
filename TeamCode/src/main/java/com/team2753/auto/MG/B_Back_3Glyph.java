package com.team2753.auto.MG;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team2753.auto.AutoModeBase;
import com.team2753.auto.AutoParams;
import com.team2753.auto.actions.DriveEncoderDrive;
import com.team2753.auto.actions.JewelHitColor;
import com.team2753.auto.actions.TrackPosition;

import static com.team2753.subsystems.Drive.WHEEL_BASE;
import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 3/15/2018.
 */

@Autonomous(name = "Blue Back 3",group = "3 Glyph")
public class B_Back_3Glyph extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart("BBack", AutoParams.AUTO, true );
        Robot.getDrive().zeroSensors();

        TrackPosition trackPosition = new TrackPosition();

        runAction(new JewelHitColor(this.jewel_Color, Jewel_Color.Blue));

        runAction(new DriveEncoderDrive(0.3, -36, -36, 4));
        ThreadAction(trackPosition);

        runAction(new DriveEncoderDrive(0.3, 0, (WHEEL_BASE*PI*(82))/180, 4));

        runAction(new DriveEncoderDrive(0.3, 7, 7, 3));

        Robot.getSlammer().stopperUp();
        sleep(600);
        runAction(new DriveEncoderDrive(0.3, -10, -10, 3));

        trackPosition.stopTracking();

        telemetry.addData("X", trackPosition.currentVectorX);
        telemetry.addData("Y",  trackPosition.currentVectorY);
        telemetry.update();
        sleep(10000);
    }
}
