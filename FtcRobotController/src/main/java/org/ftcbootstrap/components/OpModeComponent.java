package org.ftcbootstrap.components;

import org.ftcbootstrap.ActiveOpMode;

/**
 * OpModeComponent for reading and data from the Optical distance sensor
 */

public class OpModeComponent {

    private ActiveOpMode opMode;
    private int telemetryLogLevel;



    /**
     * Constructor for component
     * @param opMode
     */
    public OpModeComponent(ActiveOpMode opMode) {

        this.opMode = opMode;

    }


    /**
     *
     * @param logLevel  any number to control the level of telemetry
     * @return boolean
     */
    public boolean isTelemetryEnabled(int logLevel ) {
        return telemetryLogLevel <= logLevel;
    }

    /**
     *
     * @param logLevel  any number to control the level of telemetry
     */
    public void setTelemetryLogLevel(int logLevel) {
        this.telemetryLogLevel = logLevel;
    }

    protected ActiveOpMode getOpMode() {
        return opMode;
    }


}
