package org.ftcbootstrap.components.operations.motors;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.ODSComponent;
import org.ftcbootstrap.components.OpModeComponent;
import org.ftcbootstrap.components.utils.DriveDirection;


/**
 * Operation to assist with Tank Driving while using an ODS for
 * stopping on a line, line following, stopping before a wall
 */
public class TankDriveToODS extends OpModeComponent {

    private TankDrive tankDrive;
    private ODSComponent odsComponent;

    /**
     * Constructor for operation
     * Telemetry enabled by default.
     * @param opMode
     * @param ods
     * @param leftMotor DcMotor
     * @param rightMotor DcMotor
     */
    public TankDriveToODS(ActiveOpMode opMode,
                          OpticalDistanceSensor ods,
                          DcMotor leftMotor,
                          DcMotor rightMotor) {


        super(opMode);
        this.odsComponent = new ODSComponent(opMode , ods);
        this.tankDrive = new TankDrive(opMode, leftMotor, rightMotor);

    }


    /**
     *
     * @param name for debugging
     * @param power
     * @param targetProximity
     * @param driveDirection  {@link DriveDirection}
     * @return boolean target reached
     * @throws InterruptedException
     */
    public boolean runToTarget(String name, double power, double targetProximity, DriveDirection driveDirection)
            throws InterruptedException {

        if (this.isDriving()) {

            boolean reached =  this.targetReached();
            if ( reached) {
                stop();
            }
            return reached;
        }

        this.odsComponent.setTarget(name, targetProximity);
        tankDrive.drive(name, power, driveDirection);

        return false;


    }

    /**
     * Check if the operation is running.   Do this before calling drive()
     * @return boolean
     */
    public boolean isDriving() {
        return this.tankDrive.isDriving();
    }


    /**
     * stop the motors
     */
    public void stop()   {

        this.tankDrive.stop();

    }


    /**
     * check if current ods reading registers as close to target or as matched target brightness
     * @return boolean
     */
    public boolean targetReached() {

        return odsComponent.targetReached();
    }


    /**
     *
     * @return DriveDirection current direction of vehicle
     */
    public DriveDirection getCurrentDirection() {
        return tankDrive.getCurrentDirection();
    }
}
