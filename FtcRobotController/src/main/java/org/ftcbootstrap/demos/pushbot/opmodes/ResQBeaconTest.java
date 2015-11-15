package org.ftcbootstrap.demos.pushbot.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.ColorSensorComponent;
import org.ftcbootstrap.components.ServoComponent;
import org.ftcbootstrap.components.operations.motors.TankDriveToEncoder;
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
public class ResQBeaconTest extends ActiveOpMode {

    private PushBot robot;
    private TankDriveToODS tankDriveToODS;
    private TankDriveToEncoder tankDriveToEncoder;
    private ColorSensorComponent colorSensorComponent;
    private ServoComponent leftServoComponent;
    private ServoComponent rightServoComponent;

    private int step;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {

        //specify configuration name save from scan operation
        robot = PushBot.newConfig(hardwareMap, getTelemetryUtil());

        tankDriveToEncoder = new TankDriveToEncoder( this, robot.getLeftDrive(), robot.getRightDrive());
        tankDriveToODS = new TankDriveToODS( this, robot.getOds(), robot.getLeftDrive(), robot.getRightDrive() );
        colorSensorComponent = new ColorSensorComponent(this, robot.getMrColor(), ColorSensorComponent.ColorSensorDevice.MODERN_ROBOTICS_I2C);
        colorSensorComponent.enableLed(false);

        leftServoComponent = new ServoComponent(this,  robot.getLeftHand(),  0.8 , true);
        rightServoComponent = new ServoComponent(this,  robot.getRightHand(),  0.8);

        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();

    }


    @Override
    protected void onStart() throws InterruptedException  {
        super.onStart();
        step = 1;
        colorSensorComponent.enableLed(false);
       // getTelemetryUtil().setSortByTime(true);

        tankDriveToEncoder.startRunMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }


    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *  @throws InterruptedException
     */
    @Override
    protected void activeLoop()  throws InterruptedException {

        boolean targetReached = false;

        switch (step) {
            case 1:
                getTelemetryUtil().addData("step" + step + ": handleEncodedDrive", "forward");
                targetReached = tankDriveToEncoder.runToTarget(1, 3000, DriveDirection.DRIVE_BACKWARD);
                if (targetReached ) {
                    step++;
                }
                break;

            case 2:
                double power = .05;
                //distance assumes fixed brightness from the target
                //i.e. proximity
                double targetDistance = 0.05;
                getTelemetryUtil().addData("step" + step + ": handleDriveToODS", "in progress");
                targetReached =  tankDriveToODS.runToTarget("step" + step, power, targetDistance, DriveDirection.DRIVE_BACKWARD);
                if (targetReached) {
                    step++;
                }
                break;


            case 3:
                colorSensorComponent.addTelemetry("beacon color");
                boolean isRed = colorSensorComponent.isRed(5, 2, 2);
                getTelemetryUtil().addData("isRed", isRed);
                boolean isBlue = colorSensorComponent.isBlue(10, 2, 2);
                getTelemetryUtil().addData("isBlue", isBlue);
                if ( isRed) {
                    leftServoComponent.updateServoTargetPosition(0.3);
                    step++;
                }
                if ( isBlue) {
                    rightServoComponent.updateServoTargetPosition(0.3);
                    step++;
                }
                break;


            default:
                getTelemetryUtil().addData("step" + step + ": armFullyExtended", "in progress");
                setOperationsCompleted();
                break;
        }


        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();

    }





}
