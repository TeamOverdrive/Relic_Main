package com.team2753.splines.field;

/**
 * Created by joshua9889 on 5/28/2018.
 */

public class JoshuaField implements FieldConfig {
    @Override
    public double getFarRedCryptoboxToFarStone() {
        return 48.5;
    }

    @Override
    public double getFarRedCenterToWall() {
        return 36.0;
    }

    @Override
    public double getCloseRedCryptoboxToCloseStone() {
        return 0;
    }

    @Override
    public double getCloseRedCryptoboxToWall() {
        return 0;
    }

    @Override
    public double getFarBlueCryptoboxToFarStone() {
        return 0;
    }

    @Override
    public double getFarBlueCenterToWall() {
        return 0;
    }

    @Override
    public double getCloseBlueCryptoboxToCloseStone() {
        return 0;
    }

    @Override
    public double getCloseBlueCryptoboxToWall() {
        return 0;
    }
}
