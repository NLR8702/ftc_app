package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tmanz on 10/29/2015.
 */
    public class TS_OpticalDistanceIsFun extends OpMode {
    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();
    OpticalDistanceSensor opticalSensor;
    @Override
    public void init() {
       opticalSensor = hardwareMap.opticalDistanceSensor.get("optical");
    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {

    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        telemetry.addData("rawdata",opticalSensor.getLightDetectedRaw());
        telemetry.addData("cleandata",opticalSensor.getLightDetected());
            }
}
