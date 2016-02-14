package org.ftcTeam.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcTeam.Team8702Bot;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.MotorToEncoder;
import org.ftcbootstrap.components.operations.motors.TankDriveToEncoder;
import org.ftcbootstrap.components.utils.DriveDirection;
import org.ftcbootstrap.components.utils.MotorDirection;
import org.ftcbootstrap.demos.navbot.NavBot;

/**
 * Note: This Exercise assumes that you have used your Robot Controller App to "scan" your hardware and
 * saved the configuration named: "NavBot" and creating a class by the same name: {@link NavBot}.
 * <p/>
 * Note:  It is assumed that the proper registry is used for this set of demos. To confirm please
 * search for "Enter your custom registry here"  in  {@link com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity}
 * <p/>
 * Summary:
 * <p/>
 *
 * See:
 * <p/>
 * {@link org.ftcbootstrap.components},
 * <p/>
 * {@link org.ftcbootstrap.components.operations.servos},
 * <p/>
 * {@link org.ftcbootstrap.components.operations.motors}
 * <p/>
 * Also see: {@link NavBot} for the saved configuration
 */
public class EncoderMotorTest8702 extends ActiveOpMode {

    public final int PULSES_PER_REVOLUTION_AM60 = 1680;

    private Team8702Bot robot;

    private TankDriveToEncoder tankDriveToEncoder;
    private int step;
    private int stepCounter;

    /**
     * Implement this method to define the code to run when the Init button is pressed on the Driver station.
     */
    @Override
    protected void onInit() {

        //specify configuration name save from scan operation
        robot = Team8702Bot.newConfig(hardwareMap, getTelemetryUtil());

        tankDriveToEncoder = new TankDriveToEncoder(this, robot.getRightDrive(), robot.getLeftDrive());
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();

    }

    @Override
    protected void onStart() throws InterruptedException  {
        super.onStart();
        step = 1;

        //telemetry rows written for everthing added ( not grouped by key)
        getTelemetryUtil().setSortByTime(true);
    }


    /**
     * Implement this method to define the code to run when the Start button is pressed on the Driver station.
     * This method will be called on each hardware cycle just as the loop() method is called for event based Opmodes
     *  @throws InterruptedException
     */
    @Override
    protected void activeLoop() throws InterruptedException {

        waitOneFullHardwareCycle();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        robot.getRightDrive().setDirection(DcMotor.Direction.REVERSE);

        waitOneFullHardwareCycle();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        waitOneFullHardwareCycle();

        int target = 1680;
        goForward(0.3, target);
        goForward(0.3, target);



//        robot.getRightDrive().setPower(0.0);

        setOperationsCompleted();
    }

    private void skipStep() {
        step++;
        stepCounter = 0;
    }

    private void handleSingleMotorOperation(double power, int encoderDistance, DriveDirection driveDirection) throws InterruptedException {

        boolean targetReached = false;

        stepCounter++;
        if ( stepCounter == 1) {
            //  getTelemetryUtil().addData("step" + step + " A: Left Position", robot.getLeftDrive().getCurrentPosition());
        }
        if (stepCounter == 5 ) {
            stepCounter = 0;
        }


        targetReached = tankDriveToEncoder.runToTarget(power, encoderDistance, driveDirection, DcMotorController.RunMode.RUN_USING_ENCODERS);

//                leftMotorToEncoder.runToTarget(power, encoderDistance,
//                motorDirection, DcMotorController.RunMode.RUN_USING_ENCODERS);

        if (targetReached) {
            getTelemetryUtil().addData("step" + step + " A: Left target reached ", robot.getLeftDrive().getCurrentPosition());
            getTelemetryUtil().addData("step" + step + " A: Right Position", robot.getRightDrive().getCurrentPosition());
            step++;
            stepCounter = 0;
        }

    }

    private void goForward(double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(target);
        robot.getRightDrive().setTargetPosition(target);

        waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(0.3);
        robot.getRightDrive().setPower(0.3);

        waitOneFullHardwareCycle();

        getTelemetryUtil().addData("status", "running");
        getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((leftPos < (target - 100)) || ((leftPos >= (target - 10)) &&  (lastLeftPos != leftPos ))) {
            getTelemetryUtil().addData("leftpos", leftPos);
            getTelemetryUtil().addData("lastLefPos", lastLeftPos);
            getTelemetryUtil().addData("rightpos", rightPos);
            getTelemetryUtil().addData("lastRightPos", lastRightPos);
            getTelemetryUtil().sendTelemetry();

            sleep(100);

            lastLeftPos = leftPos;
            lastRightPos = rightPos;
            leftPos = robot.getLeftDrive().getCurrentPosition();
            rightPos = robot.getRightDrive().getCurrentPosition();
        }


        getTelemetryUtil().addData("status", "at position");
        getTelemetryUtil().sendTelemetry();


        robot.getLeftDrive().setPower(0.0);
        robot.getRightDrive().setPower(0.0);
        waitOneFullHardwareCycle();
    }



}
