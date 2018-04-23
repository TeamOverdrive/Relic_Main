package com.team2753.auto.actions;

/**
 * Created by joshua9889 on 3/23/2018.
 */

public interface Action {
    //Called once before the Action is updated
    void start();

    //Called in a continuously after start
    void update();

    //Check and see if Action is still running
    boolean isFinished();

    //Called after the Action is finished
    void done();
}
