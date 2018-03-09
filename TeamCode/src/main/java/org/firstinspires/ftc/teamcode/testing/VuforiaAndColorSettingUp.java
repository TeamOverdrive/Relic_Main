package org.firstinspires.ftc.teamcode.testing;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.Team2753Linear;
import org.firstinspires.ftc.teamcode.libs.VuMark;

import static org.firstinspires.ftc.teamcode.libs.VuMark.SaveImage;

/**
 * Created by joshua9889 on 1/13/2018.
 */

@Autonomous(name = "Setting Up VuCam")
public class VuforiaAndColorSettingUp extends LinearOpMode {
    // Use your licence key in place of Constants.kVuforiaLicenceKey
    VuMark vuMark = new VuMark(Team2753Linear.vuforiaKey);

    @Override
    public void runOpMode() throws InterruptedException {
        vuMark.setup(VuforiaLocalizer.CameraDirection.BACK);

        vuMark.update();

        Bitmap bm = vuMark.getBm(1);

        // Scan area for red and blue pixels
        for (int x = (bm.getWidth()/2)+(bm.getWidth()/4); x < bm.getWidth(); x++) {
            for (int y = (bm.getHeight() / 4) + (bm.getHeight() / 2); y < bm.getHeight(); y++) {
                bm.setPixel(x, y, Color.GREEN);
            }
        }

        SaveImage(bm);
    }
}