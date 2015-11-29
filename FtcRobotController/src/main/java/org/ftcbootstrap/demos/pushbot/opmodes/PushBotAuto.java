package org.ftcbootstrap.demos.pushbot.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.TankDriveToEncoder;
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
public class PushBotAuto extends ActiveOpMode {

    private PushBot robot;

    private TankDriveToEncoder tankDriveToEncoder;

    private int step;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {

        //specify configuration name save from scan operation
        robot = PushBot.newConfig(hardwareMap, getTelemetryUtil());

        tankDriveToEncoder = new TankDriveToEncoder(this, robot.getLeftDrive(), robot.getRightDrive());

        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();

    }

    @Override
    protected void onStart() throws InterruptedException {
        super.onStart();
        step = 1;
        getTelemetryUtil().setSortByTime(true);

        tankDriveToEncoder.startRunMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }


    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *
     * @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {

        getTelemetryUtil().addData("Current Step: ", step);

        getTelemetryUtil().addData("left motor position: ",  robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().addData("right motor position: ",  robot.getRightDrive().getCurrentPosition());
        getTelemetryUtil().addData("left y", gamepad1.left_stick_y);
        getTelemetryUtil().addData("right y", gamepad1.right_stick_y);

        boolean targetReached = false;

        float gp1_right_stick_x = (gamepad1.left_trigger - .5f) * 2;
        float gp1_right_stick_y = (gamepad1.right_trigger - .5f) * 2;

        robot.getLeftDrive().setPower(gp1_right_stick_y);
        switch (step) {
            case 1:
                //full power , forward for 3000
                getTelemetryUtil().addData("step" + step + ": handleDriveOperation", "DRIVE_FORWARD");
                targetReached = tankDriveToEncoder.runToTarget(1, 3000, DriveDirection.DRIVE_FORWARD);
                if (targetReached) {
                    step++;
                }
                break;


            case 2:
                //turn left
                getTelemetryUtil().addData("step" + step + ": handleDriveOperation", "SPIN_LEFT");
                targetReached = tankDriveToEncoder.runToTarget(1, 2000, DriveDirection.SPIN_LEFT);
                if (targetReached) {
                    step++;
                }
                break;

            case 3:
                //turn right
                getTelemetryUtil().addData("step" + step + ": handleDriveOperation", "SPIN_RIGHT");
                targetReached = tankDriveToEncoder.runToTarget(1, 2300, DriveDirection.SPIN_RIGHT);
                if (targetReached) {
                    step++;
                }
                break;

            default:
                setOperationsCompleted();
                break;

        }

        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();

    }



}
