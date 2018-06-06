package com.team2753.controllers;

/**
 * Created by joshua9889 on 5/19/2018.
 */

public abstract class Controller {
    protected boolean m_enabled = false;

    public abstract void reset();

    public abstract boolean isOnTarget();

    public abstract double calculate(double target, double current);
}
