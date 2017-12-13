package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by joshua9889 on 10/14/2017.
 * Class for everything related to the VuMark/Vuforia
 */

public class VuMark {
    public VuMark(){}

    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;
    private RelicRecoveryVuMark ouputVuMark = RelicRecoveryVuMark.UNKNOWN;
    ClosableVuforiaLocalizer vuforia;

    public void setup(VuforiaLocalizer.CameraDirection cameraDirection) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = "UR thingy here";
        parameters.cameraDirection = cameraDirection;
        parameters.useExtendedTracking = false;
        vuforia = new ClosableVuforiaLocalizer(parameters);
        this.relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        this.relicTemplate = relicTrackables.get(0);
        this.ouputVuMark = RelicRecoveryVuMark.UNKNOWN;
        this.activateVuforia();
    }

    public void activateVuforia(){
        this.relicTrackables.activate();
    }

    public void disableVuforia(){
        this.relicTrackables.deactivate();
    }

    public void updateTarget(){
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if(vuMark != RelicRecoveryVuMark.UNKNOWN)
            this.ouputVuMark = vuMark;
    }

    public RelicRecoveryVuMark getOuputVuMark(){
        return this.ouputVuMark;
    }

    public void closeVuforia() {
        this.vuforia.close();
    }
}
