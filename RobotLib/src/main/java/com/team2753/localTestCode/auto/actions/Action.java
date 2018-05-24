package com.team2753.localTestCode.auto.actions;

/**
 * Created by joshua9889 on 4/24/2018.
 */

public abstract class Action {
    long timeoutM = 10000000;

    public abstract void start();

    public abstract void loop();

    public abstract boolean isFinished();

    public abstract void stop();

    public void setTimeout(long timeoutMilli) {
        this.timeoutM = timeoutMilli;
    }
}
