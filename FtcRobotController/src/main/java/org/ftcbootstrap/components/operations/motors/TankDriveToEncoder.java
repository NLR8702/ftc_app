package org.ftcbootstrap.components.operations.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.OpModeComponent;
import org.ftcbootstrap.components.utils.DriveDirection;
import org.ftcbootstrap.components.utils.MotorDirection;


/**
 * Operation to assist with Tank Driving will using an encoder
 */
public class TankDriveToEncoder extends OpModeComponent {

    private MotorToEncoder leftMotorToEncoder;
    private MotorToEncoder rightMotorEncoder;

    /**
     * Constructor for operation
     * Telemetry enabled by default.
     * @param opMode
     * @param leftMotor DcMotor
     * @param rightMotor DcMotor
     */
    public TankDriveToEncoder(ActiveOpMode opMode,
                              DcMotor leftMotor,
                              DcMotor rightMotor) {

        super(opMode);
        leftMotorToEncoder =  new MotorToEncoder("left motor" , opMode, leftMotor );
        rightMotorEncoder = new MotorToEncoder( "right motor" , opMode, rightMotor );

    }


    /**
     *
     * @param power
     * @param encoderDistance
     * @param driveDirection  {@link DriveDirection}
     * @return boolean target reached
     * @throws InterruptedException
     */
    public boolean runToTarget(double power, double encoderDistance, DriveDirection driveDirection) throws InterruptedException {

        boolean targetReached = false;

        MotorDirection motorDirection = TankDrive.leftMotorDirectionOn(driveDirection);
        if (driveDirection == DriveDirection.PIVOT_LEFT) {
            targetReached = leftMotorToEncoder.runToTarget(0f, encoderDistance, motorDirection);
        }
        else {
            targetReached =  leftMotorToEncoder.runToTarget(power, encoderDistance, motorDirection);
        }

        //stop the other motor if this target is reached
        if ( targetReached) {
            rightMotorEncoder.stop();
            return true;
        }

        motorDirection = TankDrive.rightMotorDirectionOn(driveDirection);
        if (driveDirection == DriveDirection.PIVOT_RIGHT) {
            targetReached = rightMotorEncoder.runToTarget(0f, encoderDistance, motorDirection);
        }
        else {
            targetReached = rightMotorEncoder.runToTarget(power, encoderDistance, motorDirection);
        }

        //stop the other motor if this target is reached
        if ( targetReached) {
            leftMotorToEncoder.stop();
            return true;
        }

        return false;

    }

    /**
     * Check if the operation is running.   Do this before calling runToTarget()
     * @return boolean
     */
    public boolean isDriving() {
        return leftMotorToEncoder.isRunning() || rightMotorEncoder.isRunning();
    }


    /**
     * stop the motors
     */
    public void stop()  throws InterruptedException {

        leftMotorToEncoder.stop();
        rightMotorEncoder.stop();

    }


    /**
     * check if current encoder position is close to target
     * @return boolean
     */
    public boolean targetReached() {

        return leftMotorToEncoder.targetReached() || rightMotorEncoder.targetReached();
    }

    public void startRunMode(DcMotorController.RunMode runMode) throws InterruptedException {

        leftMotorToEncoder.startRunMode(runMode);
        rightMotorEncoder.startRunMode(runMode);
    }

}
