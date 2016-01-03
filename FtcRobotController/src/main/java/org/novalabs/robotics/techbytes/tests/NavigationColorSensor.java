package org.novalabs.robotics.techbytes.tests;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

import org.novalabs.robotics.techbytes.ColorAnalyzer;
import org.novalabs.robotics.techbytes.HardwareController;

/**
 * Created by tylerkim on 12/30/15.
 */
public class NavigationColorSensor extends OpMode {
    ColorSensor sensorRGB;
   // DeviceInterfaceModule cdim;
    ColorAnalyzer analyzer = new ColorAnalyzer();

    @Override
    public void init() {
        sensorRGB = hardwareMap.colorSensor.get("nxt");
        // bEnabled represents the state of the LED.
        boolean bEnabled = true;

        // turn the LED on in the beginning, just so user will know that the sensor is active.
        sensorRGB.enableLed(true);

        // cdim = hardwareMap.deviceInterfaceModule.get("dim");
        //sensorRGB.enableLed(true);
    }

    @Override
    public void loop() {
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F,0F,0F};
        Color.RGBToHSV(sensorRGB.red(), sensorRGB.green(), sensorRGB.blue(), hsvValues);

        // send the info back to driver station using telemetry function.
        telemetry.addData("Clear", sensorRGB.alpha());
        telemetry.addData("Red  ", sensorRGB.red());
        telemetry.addData("Green", sensorRGB.green());
        telemetry.addData("Blue ", sensorRGB.blue());
        telemetry.addData("Hue", hsvValues[0]);

    }
}

