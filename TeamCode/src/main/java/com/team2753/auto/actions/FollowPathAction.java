package com.team2753.auto.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.team254.lib.trajectory.Path;
import com.team2753.subsystems.Drive;
import com.team2753.subsystems.Robot;

/**
 * Created by joshua9889 on 4/21/2018.
 */

public class FollowPathAction implements Action {

    private Path pathToFollow;
    private Drive mDrive = Robot.getInstance().getDrive();

    public FollowPathAction(Path path){
        this.pathToFollow = path;
    }

    @Override
    public void start() {
        mDrive.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void done() {

    }
}
