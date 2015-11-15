package org.ftcbootstrap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.ftcbootstrap.components.utils.TelemetryUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * OpMode Abstract class that offers specialized telemetry and error handling to ensure that a LinearOpMode
 * runs smoothly in its "active" hardware state
 */
public abstract class ActiveOpMode extends LinearOpMode {

    private TelemetryUtil telemetryUtil = new TelemetryUtil(this);
    private boolean operationsCompleted;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    abstract protected void onInit();

    /**
     * override to this method to perform one time operations after start is pressed
     */
    protected void onStart() throws InterruptedException {
        this.resetStartTime();
        getTelemetryUtil().reset();
    }


    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *
     * @throws InterruptedException
     */
    abstract protected void activeLoop() throws InterruptedException;

    /**
     * @return
     */
    public TelemetryUtil getTelemetryUtil() {
        return telemetryUtil;
    }


    /**
     * Override this method only if you need to do something outside of onInit() and activeLoop()
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {

        try {
            onInit();
        } catch (Throwable e) {
            handleOpmodeException(e);
        }

        waitForStart();

        onStart();


        while (opModeIsActive() && !operationsCompleted) {

            try {
                activeLoop();
            } catch (Throwable e) {
                handleOpmodeException(e);
            }

            waitOneFullHardwareCycle();
        }
    }


    /**
     * Clear data from the telemetry cache
     */
    public void clearTelemetryData() {
        telemetry.clearData();
        try {
            if (opModeIsActive()) {
                waitForNextHardwareCycle();
            }
        } catch (InterruptedException e) {
        }
    }


    /**
     * Send telemetry to the Driver Station App
     *
     * @param key
     * @param message Note.  {@link TelemetryUtil#addData(String, String)}  only puts the data in a queue for later ordering.
     * <p/>
     * To send the data on to the  Driver Station use: {@link TelemetryUtil#sendTelemetry()}
     */
    public void sendTelemetryData(String key, String message) {
        telemetry.addData(key, message);

    }

    protected void handleOpmodeException(Throwable e) throws InterruptedException {
        getTelemetryUtil().addData("Opmode Exception", e.getMessage());
        String stckTrace = stackTraceAsString(e);
        getTelemetryUtil().addData("Opmode Stacktrace", stckTrace.substring(0, 200));
        // DbgLog.msg(e.getLocalizedMessage());
        //if( e instanceof Exception) {
        //DbgLog.error(stckTrace);

        //}


        getTelemetryUtil().sendTelemetry();
        if (e instanceof InterruptedException) {
            throw (InterruptedException) e;
        }
    }

    /**
     * call to prevent leave the loop calling activeLoop()
     */
    protected void setOperationsCompleted() {
        this.operationsCompleted = true;
        getTelemetryUtil().addData("Opmode Status", "Operations completed");
    }

    private String stackTraceAsString(Throwable e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();

    }

}
