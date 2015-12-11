package org.ftcbootstrap.components.operations.motors;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.OpModeComponent;
import org.ftcbootstrap.components.utils.DriveDirection;
import org.ftcbootstrap.components.utils.MotorDirection;


/**
 * Operation to assist with Tank Driving
 */

public class TankDrive extends OpModeComponent {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private boolean driving;

    private DriveDirection currentDirection;

    /**
     * Constructor for operation
     * Telemetry enabled by default.
     *
     * @param opMode
     * @param leftMotor  DcMotor
     * @param rightMotor DcMotor
     */
    public TankDrive(ActiveOpMode opMode,
                     DcMotor leftMotor,
                     DcMotor rightMotor) {


        super(opMode);
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

    }


    /**
     * @param name           name for debugging
     * @param power
     * @param driveDirection {@link DriveDirection}
     * @throws InterruptedException
     */
    public void drive(String name, double power, DriveDirection driveDirection)
            throws InterruptedException {

        this.driving = true;
        this.currentDirection = driveDirection;

        MotorDirection motorDirection = TankDrive.leftMotorDirectionOn(driveDirection);
        double powerWithDirection = TankDrive.leftPowerWithDirection(motorDirection, driveDirection, power);
        if (this.leftMotor != null) {
            if (isTelemetryEnabled(1)) {
                getOpMode().getTelemetryUtil().addData(name + ":Starting left motor", powerWithDirection);
            }
            this.leftMotor.setPower(powerWithDirection);
        }

        motorDirection = TankDrive.rightMotorDirectionOn(driveDirection);
        powerWithDirection = TankDrive.rightPowerWithDirection(motorDirection, driveDirection, power);


        if (driveDirection == DriveDirection.PIVOT_RIGHT) {
            powerWithDirection = 0f;
        }

        if (this.rightMotor != null) {
            if (isTelemetryEnabled(1)) {
                getOpMode().getTelemetryUtil().addData(name + ": A - Starting right motor", powerWithDirection);
            }
            this.rightMotor.setPower(powerWithDirection);
        }
    }

    /**
     * Check if the operation is running.   Do this before calling drive()
     *
     * @return boolean
     */
    public boolean isDriving() {
        return driving;
    }


    /**
     * normal the motors
     */
    public void stop() {

        this.leftMotor.setPower(0.f);
        this.rightMotor.setPower(0.f);

    }




    /**
     * Helper method to derive the left motor direction from the DriveDirection
     *
     * @param driveDirection {@link DriveDirection}
     * @return
     */
    public static MotorDirection leftMotorDirectionOn(DriveDirection driveDirection) {

        MotorDirection motorDirection = MotorDirection.MOTOR_FORWARD;
        if (driveDirection == DriveDirection.DRIVE_BACKWARD ||
                driveDirection == DriveDirection.SPIN_LEFT) {
            motorDirection = MotorDirection.MOTOR_BACKWARD;
        }
        return motorDirection;
    }

    /**
     * Helper method to derive the right motor direction from the  DriveDirection
     *
     * @param driveDirection {@link DriveDirection}
     * @return MotorDirection {@link MotorDirection}
     */
    public static MotorDirection rightMotorDirectionOn(DriveDirection driveDirection) {

        MotorDirection motorDirection = MotorDirection.MOTOR_FORWARD;
        if (driveDirection == DriveDirection.DRIVE_BACKWARD ||
                driveDirection == DriveDirection.SPIN_RIGHT) {
            motorDirection = MotorDirection.MOTOR_BACKWARD;
        }
        return motorDirection;
    }

    /**
     * Helper method to derive the left motor power the DriveDirection
     * Based of the vehicle Drive direction, calculate the value of a single motor to see if should move forward
     * backward , remaing in the same direction of the other motor, or come to a normal
     * <p/>
     * Examples:
     * Drive Forward = both motors move forward
     * Drive Forward = both motors move backward
     * SPIN = One motor moves forward while the other backward
     * PIVOT = One motor moves is normal  while the other moves
     *
     * @param motorDirection current left motor direction {@link MotorDirection}
     * @param driveDirection current vehicle drive direction {@link DriveDirection}
     * @param power          current absolute power
     * @return double current absolute power as a positive or negative number
     */
    public static double leftPowerWithDirection(MotorDirection motorDirection, DriveDirection driveDirection, double power) {

        double powerWithDirection = (motorDirection == MotorDirection.MOTOR_FORWARD) ? power : -power;
        if (driveDirection == DriveDirection.PIVOT_LEFT) {
            powerWithDirection = 0f;
        }
        return powerWithDirection;
    }


    /**
     * Helper method to derive the right motor power the DriveDirection
     * Based of the vehicle Drive direction, calculate the value of a single motor to see if should move forward
     * backward , remaing in the same direction of the other motor, or come to a normal
     * <p/>
     * Examples:
     * Drive Forward = both motors move forward
     * Drive Forward = both motors move backward
     * SPIN = One motor moves forward while the other backward
     * PIVOT = One motor moves is normal  while the other moves
     *
     * @param motorDirection current right motor direction {@link MotorDirection}
     * @param driveDirection current vehicle drive direction  {@link DriveDirection}
     * @param power          current absolute power
     * @return double current absolute power as a positive or negative number or zero value
     */
    public static double rightPowerWithDirection(MotorDirection motorDirection, DriveDirection driveDirection, double power) {

        double powerWithDirection = (motorDirection == MotorDirection.MOTOR_FORWARD) ? power : -power;
        if (driveDirection == DriveDirection.PIVOT_RIGHT) {
            powerWithDirection = 0f;
        }
        return powerWithDirection;
    }


    /**
     * @return DriveDirection current direction of vehicle  {@link DriveDirection}
     */
    public DriveDirection getCurrentDirection() {
        return currentDirection;
    }


}
