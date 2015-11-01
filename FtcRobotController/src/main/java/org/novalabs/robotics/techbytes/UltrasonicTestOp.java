package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Andrew Bolster on 10/25/2015.
 */
public class UltrasonicTestOp extends OpMode {

    private UltrasonicSensor ultrasonicSensor;

    @Override
    public void init() {
ultrasonicSensor = hardwareMap.ultrasonicSensor.get("Ult_1");
    }

    @Override
    public void loop() {
        double ultrasonic = ultrasonicSensor.getUltrasonicLevel();
        telemetry.addData("", "Inches: " + ultrasonic / 2.78);

    }



}
