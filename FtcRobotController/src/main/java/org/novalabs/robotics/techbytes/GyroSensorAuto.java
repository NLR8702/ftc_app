package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by tylerkim on 1/3/16.
 */
public class GyroSensorAuto implements HardwareController {
    GyroSensor gyroSensor;

    @Override
    public void init(OpMode opMode) {
        gyroSensor = opMode.hardwareMap.gyroSensor.get("gyro");
    }

    @Override
    public void loop(OpMode opMode) {
        gyroSensor.calibrate();
        while (gyroSensor.isCalibrating()) {
            gyroSensor.rawX();
            gyroSensor.rawY();
            gyroSensor.rawZ();

            opMode.telemetry.addData("X value: ", gyroSensor.rawX());
            opMode.telemetry.addData("Y value: ", gyroSensor.rawY());
            opMode.telemetry.addData("Z value: ", gyroSensor.rawZ());
        }

    }
}

