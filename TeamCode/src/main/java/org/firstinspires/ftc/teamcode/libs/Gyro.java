package org.firstinspires.ftc.teamcode.libs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by joshua9889 on 3/7/2018.
 */

public interface Gyro {
    void calibrate(LinearOpMode opMode);
    void reset();
    double getAngleDegrees();
    double getAngleRadians();
}
