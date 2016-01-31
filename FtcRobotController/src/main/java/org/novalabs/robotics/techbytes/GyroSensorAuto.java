package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.Hardware;

import static java.lang.Thread.*;

/**
 * Created by tylerkim on 1/3/16.
 */
public class GyroSensorAuto implements HardwareController {
    GyroSensor gyroSensor;

    int heading = 0;
    int xVal, yVal, zVal = 0;


    @Override
    public void init(OpMode opMode) {
        gyroSensor = opMode.hardwareMap.gyroSensor.get("gyro");
        // calibrate the gyro.
        gyroSensor.calibrate();

    }

    @Override
    public void loop(OpMode opMode) {

        // get the heading info.
        // the Modern Robotics' gyro sensor keeps
        // track of the current heading for the Z axis only.
        heading = gyroSensor.getHeading();
        // get the x, y, and z values (rate of change of angle).
        xVal = gyroSensor.rawX();
        yVal = gyroSensor.rawY();
        zVal = gyroSensor.rawZ();
        opMode.telemetry.addData("1. x", String.format("%03d", xVal));
        opMode.telemetry.addData("2. y", String.format("%03d", yVal));
        opMode.telemetry.addData("3. z", String.format("%03d", zVal));
        opMode.telemetry.addData("4. h", String.format("%03d", heading));

        //telemetry.addData("X value ", gyroSensor.rawX());
        //telemetry.addData("Y value ", gyroSensor.rawY());
        //telemetry.addData("Z value ", gyroSensor.rawZ());
    }
}



