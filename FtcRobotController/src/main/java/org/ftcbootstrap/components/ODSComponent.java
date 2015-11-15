package org.ftcbootstrap.components;

import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.ftcbootstrap.ActiveOpMode;

/**
 * OpMode Component for reading and data from the Optical distance sensor
 */

public class ODSComponent extends OpModeComponent {


    private double targetValue;
    private OpticalDistanceSensor ods;

    /**
     * Constructor for component
     * Telemetry enabled by default.
     * @param opMode
     * @param ods
     */
    public ODSComponent(ActiveOpMode opMode,
                        OpticalDistanceSensor ods) {


        super(opMode);
        this.ods = ods;

    }


    /**
     * Target Value will be the reflected light intensity at a fixed distance to and object.
     * Target Value will be a measure of distance for fixed reflective intensity of an object
     *
     * @param name  supplied for telemetry
     * @param targetValue
     * @throws InterruptedException
     */
    public void setTarget(String name, double targetValue)
            throws InterruptedException {

        this.targetValue = targetValue;


    }

    /**
     * @return boolean designated whether the odsReading matches the target value
     */
    public boolean targetReached() {

        double odsReading = this.ods.getLightDetected();
        boolean result = odsReading >= this.targetValue;

        if (isTelemetryEnabled(1)) {
            getOpMode().getTelemetryUtil().addData(
                     ": WithinRange?", "odsReading:" + odsReading +
                            "targetProximity: " + this.targetValue +
                            "result: " + result);
        }

        return result;
    }


}
