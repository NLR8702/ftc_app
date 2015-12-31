package org.novalabs.robotics.techbytes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

/**
 * Created by tylerkim on 10/25/15.
 */
public class TK_ColorOpMode implements HardwareController {
    private static final int LED_CHANNEL = 5;
    ColorSensor sensorRGB;
    DeviceInterfaceModule cdim;
    ColorAnalyzer analyzer = new ColorAnalyzer();
    int debug_count = 0;
    private boolean bEnabled = false;

    @Override
    public void init(OpMode opMode) {
        sensorRGB = opMode.hardwareMap.colorSensor.get("color");
        cdim = opMode.hardwareMap.deviceInterfaceModule.get("dim");
        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(LED_CHANNEL, bEnabled);


    }

    @Override
    public void loop(OpMode opMode) {
        debug_count = debug_count + 1;
        int red = sensorRGB.red();
        int blue = sensorRGB.blue();
        int green = sensorRGB.green();
        int alpha = sensorRGB.alpha();


        float[] percentageArray = analyzer.getPercentage(red, blue, green);
        opMode.telemetry.addData("TEST ", debug_count);
        opMode.telemetry.addData("TKS_red", percentageArray[1]);
        opMode.telemetry.addData("TKS _blue", percentageArray[0]);
        opMode.telemetry.addData("TKS_green", percentageArray[2]);
        ColorTypes colorTypes = analyzer.Color_Result(percentageArray[1], percentageArray[0], percentageArray[2], 0);
        opMode.telemetry.addData("Color= ", colorTypes);

        //telemetry.addData("TKS_clear",sensorRGB.alpha());
        //telemetry.addData("detecting: ",  analyzer.Color_Result(red,blue, green, alpha));
        //ColorTypes result = analyzer.Color_Result(red,blue, green, alpha);
        //telemetry.addData("detecting: ", result);
    }

}


