package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

/**
 * Created by tylerkim on 12/30/15.
 */
public class NavigationColorSensor implements HardwareController {
    ColorSensor sensorRGB;
    DeviceInterfaceModule cdim;
    ColorAnalyzer analyzer = new ColorAnalyzer();


    @Override
    public void init(OpMode opMode) {
        sensorRGB = opMode.hardwareMap.colorSensor.get("nxt");
        cdim = opMode.hardwareMap.deviceInterfaceModule.get("dim");
        sensorRGB.enableLed(true);
        // bEnabled represents the state of the LED.
        boolean bEnabled = true;

        // turn the LED on in the beginning, just so user will know that the sensor is active.
        sensorRGB.enableLed(true);


    }

    @Override
    public void loop(OpMode opMode) {
        int red = sensorRGB.red();
        int blue = sensorRGB.blue();
        int green = sensorRGB.green();
        int clear = sensorRGB.alpha();

        float[] percentageArray = analyzer.getPercentage(red, blue, green);
        opMode.telemetry.addData("Red  ", percentageArray[1]);
        opMode.telemetry.addData("Green", percentageArray[2]);
        opMode.telemetry.addData("Blue ", percentageArray[3]);

    }
}

