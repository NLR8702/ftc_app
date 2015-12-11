package org.ftcbootstrap.components.operations.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.OpModeComponent;
import org.ftcbootstrap.components.utils.MotorDirection;


/**
 * Operation to assist with running a DcMotor with the encoder
 */

public class MotorToEncoder extends OpModeComponent {

    private DcMotor motor;
    private double targetEncoderDistance;
    private int startingEncoderPosition;
    private boolean running = false;
    private String name;
    //defaulted but can be changed in the app
    private int encoderResetThreshold = 3;
    private int encoderTargetThreshold = 3;

    /**
     * Constructor for operation
     * Telemetry enabled by default.
     *
     * @param name   String used for debugging
     * @param opMode
     * @param motor
     */
    public MotorToEncoder(String name, ActiveOpMode opMode, DcMotor motor) {

        super(opMode);
        this.motor = motor;
        this.name = name;
        try {
            this.resetEncoder();
        } catch (InterruptedException e) {
            opMode.getTelemetryUtil().addData("exception on resetting encoder", e.getMessage());
        }
    }

    /**
     * default to forward direction
     *
     * @param power
     * @param targetEncoderDistance
     * @return boolean target reached
     * @throws InterruptedException
     */
    public boolean runToTarget(double power,
                            double targetEncoderDistance) throws InterruptedException {
        return runToTarget(power, targetEncoderDistance, MotorDirection.MOTOR_FORWARD);
    }


    /**
     * @param power
     * @param targetEncoderDistance
     * @param direction             {@link MotorDirection}
     * @return boolean target reached
     * @throws InterruptedException
     */
    public boolean runToTarget(double power,
                            double targetEncoderDistance,
                            MotorDirection direction) throws InterruptedException {

        if (this.isRunning()) {

            boolean reached =  this.targetReached();
            if ( reached) {
                stop();  // TODO : not needed when supporting RUN_TO_POSITION
            }
            return reached;
        }
        running = true;
        this.targetEncoderDistance = targetEncoderDistance;
        this.startingEncoderPosition = motor.getCurrentPosition();

        double powerWithDirection = (direction == MotorDirection.MOTOR_FORWARD) ? power : -power;

        if (isTelemetryEnabled(1)) {
            getOpMode().getTelemetryUtil().addData(name + ":Starting motor ", powerWithDirection);
        }

        if (motor != null) {
            motor.setPower(powerWithDirection);
        }
        return false;


    }

    /**
     * check if current encoder position is close to target
     * (choosing within targetEncoderValue of 3 as"close enough")
     *
     * @return boolean target reached
     */
    public boolean targetReached() {

        boolean reached = false;

        if (motor != null) {
            int position = Math.abs(motor.getCurrentPosition() - this.startingEncoderPosition);

            if (position >=
                    (this.targetEncoderDistance - encoderTargetThreshold)) {
                reached = true;
                if (isTelemetryEnabled(1)) {
                    getOpMode().getTelemetryUtil().addData(name + ": Target Reached ", position);
                }
            }
        }

        return reached;

    }


    /**
     * Stop the motor
     *
     * @throws InterruptedException
     */
    public void stop() throws InterruptedException {
        if (isTelemetryEnabled(1)) {
            getOpMode().getTelemetryUtil().addData(name + ":stopping motor ", "normal");
        }
        running = false;
        motor.setPower(0);

    }

    /**
     * Check if the operation is running.   Do this before calling runToTarget()
     *
     * @return
     */
    public boolean isRunning() {
        return running;
    }


    private void resetEncoder() throws InterruptedException {

        motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        while (Math.abs(motor.getCurrentPosition()) > encoderResetThreshold) {
            getOpMode().waitForNextHardwareCycle();
        }

    }



    public void startRunMode(DcMotorController.RunMode runMode) throws InterruptedException {
        motor.setMode(runMode);
        getOpMode().waitOneFullHardwareCycle();

    }


    /**
     * set the threshold to measure whether the encoder is fully reset
     * fully reset usuall implies the that the motor position is zero but that may be off
     * from time to time due to noise.
     *
     * @param encoderResetThreshold
     */
    public void setEncoderResetThreshold(int encoderResetThreshold) {
        this.encoderResetThreshold = encoderResetThreshold;
    }

    /**
     * set the threshold to measure whether the encoder is close to the target position
     *
     * @param encoderTargetThreshold
     */
    public void setEncoderTargetThreshold(int encoderTargetThreshold) {
        this.encoderTargetThreshold = encoderTargetThreshold;
    }


}
