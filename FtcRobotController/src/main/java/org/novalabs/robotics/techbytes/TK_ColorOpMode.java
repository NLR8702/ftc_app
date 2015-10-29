package org.novalabs.robotics.techbytes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

/**
 * Created by tylerkim on 10/25/15.
 */
public class TK_ColorOpMode extends OpMode{
    ColorSensor sensorRGB;
    DeviceInterfaceModule cdim;

    int debug_count = 0;
    @Override
    public void init() {
        sensorRGB = hardwareMap.colorSensor.get("color");
    }

    @Override
    public void loop() {
        debug_count = debug_count + 1;
        telemetry.addData("TEST ", debug_count);
        telemetry.addData("TKS_red",sensorRGB.red());
        telemetry.addData("TKS _blue",sensorRGB.blue());
        telemetry.addData("TKS_green",sensorRGB.green());
        telemetry.addData("TKS_clear",sensorRGB.alpha());

    }
}
