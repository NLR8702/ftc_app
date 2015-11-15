package org.ftcbootstrap.demos.beginner.opmodes;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.TankDrive;
import org.ftcbootstrap.components.utils.DriveDirection;
import org.ftcbootstrap.demos.beginner.MyFirstBot;

/**
 * Note: This Exercise assumes that you have used your Robot Controller App to "scan" your hardware and
 * saved the configuration named: "MyFirstBot" and creating a class by the same name: {@link MyFirstBot}.
 * <p/>
 * Note:  It is assumed that the proper registry is used for this set of demos. To confirm please
 * search for "Enter your custom registry here"  in  {@link com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity}
 * <p/>
 * Summary:  Use an Operation class to perform a tank drive on two motors
 * See:  {@link TankDrive}
 */
public class MyFirstBotOpMode4 extends ActiveOpMode {

    private MyFirstBot robot;
    private TankDrive tankDrive;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {

        robot = MyFirstBot.newConfig(hardwareMap, getTelemetryUtil());

        //create an operation to perform a tank drive
        tankDrive = new TankDrive(this, robot.getMotor1(), robot.getMotor2());
        tankDrive.setTelemetryLogLevel(1);

        //Note The Telemetry Utility is designed to let you organize all telemetry data before sending it to
        //the Driver station via the sendTelemetry command
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();

    }

    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *
     * @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {

        //drive for 3 seconds

        double elapsedTime = getRuntime();
        getTelemetryUtil().addData("runtime",elapsedTime);

        //start the vehicle
        if (!tankDrive.isDriving()) {
            tankDrive.drive("driving vehicle", 1, DriveDirection.DRIVE_FORWARD);
        }
        //shut it down after 3 seconds
        if (elapsedTime > 3) {
            robot.getMotor1().setPower(0);
            tankDrive.stop();
            setOperationsCompleted();
        }

        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();


    }

}
