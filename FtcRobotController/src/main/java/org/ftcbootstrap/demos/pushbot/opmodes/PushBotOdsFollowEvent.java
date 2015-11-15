package org.ftcbootstrap.demos.pushbot.opmodes;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.TankDriveToODS;
import org.ftcbootstrap.components.utils.DriveDirection;
import org.ftcbootstrap.demos.pushbot.PushBot;

/**
 * Note: This Exercise assumes that you have used your Robot Controller App to "scan" your hardware and
 * saved the configuration named: "Pushbot" and creating a class by the same name: {@link PushBot}.
 * <p/>
 * Note:  It is assumed that the proper registry is used for this set of demos. To confirm please
 * search for "Enter your custom registry here"  in  {@link com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity}
 * <p/>
 * Summary:
 * <p/>
 * Refactored from the original Qaulcomm PushBot examples to demonstrate the use of the latest
 * reusable components and operations
 * See:
 * <p/>
 * {@link org.ftcbootstrap.components},
 * <p/>
 * {@link org.ftcbootstrap.components.operations.servos},
 * <p/>
 * {@link org.ftcbootstrap.components.operations.motors}
 * <p/>
 * Also see: {@link PushBot} for the saved configuration
 */
public class PushBotOdsFollowEvent extends ActiveOpMode {

    private PushBot robot;
    private TankDriveToODS tankDriveToODS;
    private DriveDirection lineFollowDirection = DriveDirection.PIVOT_LEFT;
    //  full power , forward until target brightness >= .1
    private double power = 0.2;
    //brightness assumes fixed distance from the target
    //i.e. line follow or stop on white line
    private double targetBrightness = 0.8;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {
        //specify configuration name save from scan operation
        robot = PushBot.newConfig(hardwareMap, getTelemetryUtil());
        tankDriveToODS = new TankDriveToODS(this, robot.getOds(), robot.getLeftDrive(), robot.getRightDrive());
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();
    }


    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *  @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {

        boolean targetReached = false;

        targetReached =  tankDriveToODS.runToTarget("starting line follow", power, targetBrightness, lineFollowDirection);
        if (targetReached) {
            targetReached =   tankDriveToODS.runToTarget("turning away from line", power, targetBrightness, directionAwayFromLine());
        }
        else if (tankDriveToODS.getCurrentDirection() != lineFollowDirection) {
            targetReached =   tankDriveToODS.runToTarget("turning to line", power, targetBrightness, lineFollowDirection);
        }

        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();

    }


    private DriveDirection directionAwayFromLine() {

        return tankDriveToODS.getCurrentDirection() == DriveDirection.PIVOT_LEFT ?
                DriveDirection.PIVOT_RIGHT : DriveDirection.PIVOT_LEFT;

    }


}
