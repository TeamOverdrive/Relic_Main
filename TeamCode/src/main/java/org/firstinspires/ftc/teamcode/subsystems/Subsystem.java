package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 12/10/2017.
 */

public interface Subsystem {
    void init(LinearOpMode linearOpMode, boolean auto);
    void zeroSensors();
    void stop();
    public abstract void outputToTelemetry(Telemetry telemetry);
}
