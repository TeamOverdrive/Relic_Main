package org.firstinspires.ftc.robotcontroller.internal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.qualcomm.ftcrobotcontroller.R;

import java.util.ArrayList;

/**
 * Created by joshua9889 on 3/24/2018.
 */

public class Team2753GlyphChosingActivity extends Activity {

    private SharedPreferences globalPrefs;
    private Button glyphPlacement, saveAndExit;
    private SeekBar glyphSlider;
    private double position = 0;

    private ArrayList<Double> positions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glyph_choser);

        this.globalPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        this.glyphPlacement = (Button)findViewById(R.id.chosePlacement);
        this.saveAndExit = (Button)findViewById(R.id.saveAndExit);
        this.glyphSlider = (SeekBar)findViewById(R.id.Position1Box);

        this.glyphSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                position = 24*(progress/100.0);
                //RobotLog.a("Position: " + position);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        this.glyphPlacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positions.add(position);
                save();
            }
        });

        saveAndExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
    }

    public void save(){
        SharedPreferences.Editor editor = globalPrefs.edit();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        int[] list = new int[10];
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
            str.append(list[i]).append(",");
        }
        prefs.edit().putString("string", str.toString());

        for (int i=0;i<positions.size();i++){
            editor.putLong("GlyphPosition"+String.valueOf(i), (long) position);
        }
        editor.putInt("Positions",positions.size());
        editor.commit();
    }

    public void exit(){
        save();
        Intent launchNewIntent = new Intent(this, FtcRobotControllerActivity.class);
        startActivityForResult(launchNewIntent, 0);
    }
}
