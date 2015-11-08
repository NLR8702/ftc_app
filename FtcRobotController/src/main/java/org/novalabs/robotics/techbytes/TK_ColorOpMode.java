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
    ColorAnalyzer analyzer = new ColorAnalyzer();
    int debug_count = 0;
    @Override
    public void init() {
        sensorRGB = hardwareMap.colorSensor.get("color");
    }

    @Override
    public void loop() {
        debug_count = debug_count + 1;
        int red = sensorRGB.red();
        int blue = sensorRGB.blue();
        int green = sensorRGB.green();
        int alpha = sensorRGB.alpha();

//        telemetry.addData("TEST ", debug_count);
//        telemetry.addData("TKS_red",sensorRGB.red());
//        telemetry.addData("TKS _blue",sensorRGB.blue());
//        telemetry.addData("TKS_green",sensorRGB.green());
//        telemetry.addData("TKS_clear",sensorRGB.alpha());
          //telemetry.addData("detecting: ",  analyzer.Color_Result(red,blue, green, alpha));
        ColorTypes result = analyzer.Color_Result(red,blue, green, alpha);
        telemetry.addData("detecting: ", result);
    }
}
