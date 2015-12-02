package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Andrew Bolster on 10/25/2015.
 */
public class UltrasonicTestOp extends OpMode {

    private UltrasonicSensor ultrasonicSensor;
    private int Counter=0;
    private double ultrasonic;

    @Override
    public void init() {

        ultrasonicSensor = hardwareMap.ultrasonicSensor.get("Ult_1");
    }

    @Override
    public void loop() {

       // double ultrasonic = ultrasonicSensor.getUltrasonicLevel();
       String ultrasonicname = ultrasonicSensor.getDeviceName();
        String ultrasoicsenses = ultrasonicSensor.getConnectionInfo();
        ultrasonic = ultrasonicSensor.getUltrasonicLevel();



        telemetry.addData("Counter"    ,"Counter: " + Counter++);
        telemetry.addData("Sonic Value", "Sonic Value: " + ultrasonic);
        telemetry.addData("inf: ", ultrasonicname);
        telemetry.addData("Connection: ", ultrasoicsenses);
        // telemetry.addData("Inches"     , "Inches: " + ultrasonic / 2.78);
         telemetry.addData("Inches"     , "Inches: " + ultrasonic / 3.5);


    }



}
