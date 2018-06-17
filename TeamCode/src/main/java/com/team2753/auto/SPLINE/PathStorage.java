package com.team2753.auto.SPLINE;

import com.team2753.splines.field.FieldConfig;
import com.team2753.splines.field.JoshuaField;

import static com.team2753.auto.SPLINE.paths.BlueClose_Paths.calculateBlueClose;
import static com.team2753.auto.SPLINE.paths.BlueFar_Paths.calculateBlueFar;
import static com.team2753.auto.SPLINE.paths.RedClose_Paths.calculateRedClose;
import static com.team2753.auto.SPLINE.paths.RedFar_Paths.calculateRedFar;

/**
 * Created by joshua9889 on 5/31/2018.
 */

public class PathStorage {

    public static void calculateAll(FieldConfig field) {
        calculateRedFar(field);
        calculateRedClose(field);
        calculateBlueFar(field);
        calculateBlueClose(field);
    }

    static {
        PathStorage.calculateAll(new JoshuaField());
    }
}
