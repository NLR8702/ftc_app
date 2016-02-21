package org.ftcTeam.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcTeam.Team8702Bot;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.TankDriveToEncoder;
import org.ftcbootstrap.components.utils.DriveDirection;
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
public class AutonomousMode extends ActiveOpMode {

    public final int PULSES_PER_REVOLUTION_AM60 = 1680;

    static final double PULSE_PER_90_DEGREE = 1940;
    static final double PULSE_PER_FOOT = 1120;
    protected Team8702Bot robot;

    protected TankDriveToEncoder tankDriveToEncoder;
    private int step;
    private int stepCounter;
    public final int PULSES_PER_90=1940;
    public final int GO_FORWARD_3_FEET=3360;

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
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        robot.getLeftDrive().setDirection(DcMotor.Direction.REVERSE);

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
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        waitOneFullHardwareCycle();
        int target = 0;
        getTelemetryUtil().addData("status", "about to go forward 36 inchs");
        getTelemetryUtil().sendTelemetry();

        target = Inches_to_Pulse(12);
        move(0.3, target);

        getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().sendTelemetry();


        waitOneFullHardwareCycle();

        target = Degrees_to_Pulse(45);
        spinLeft(0.3, target);

        //target = target + Inches_to_Pulse(12);
       // target = target + Inches_to_Pulse(12);
        waitOneFullHardwareCycle();
        target = Inches_to_Pulse(36);
        move(0.3, target);

        waitOneFullHardwareCycle();
        target = Inches_to_Pulse(12);
        move(-0.3, -target);


        waitOneFullHardwareCycle();
        target = Degrees_to_Pulse(90);
        spinRight(0.3, target);



        this.setOperationsCompleted();
    }

//    private void skipStep() {
//        step++;
//        stepCounter = 0;
//    }
//
////    private void handleSingleMotorOperation(double power, int encoderDistance, DriveDirection driveDirection) throws InterruptedException {
////
////        boolean targetReached = false;
////
////        stepCounter++;
////        if ( stepCounter == 1) {
////            //  getTelemetryUtil().addData("step" + step + " A: Left Position", robot.getLeftDrive().getCurrentPosition());
////        }
////        if (stepCounter == 5 ) {
//            stepCounter = 0;
//        }
//
//
//        targetReached = tankDriveToEncoder.runToTarget(power, encoderDistance, driveDirection, DcMotorController.RunMode.RUN_USING_ENCODERS);
//
////                leftMotorToEncoder.runToTarget(power, encoderDistance,
////                motorDirection, DcMotorController.RunMode.RUN_USING_ENCODERS);
//
//        if (targetReached) {
//            getTelemetryUtil().addData("step" + step + " A: Left target reached ", robot.getLeftDrive().getCurrentPosition());
//            getTelemetryUtil().addData("step" + step + " A: Right Position", robot.getRightDrive().getCurrentPosition());
//            step++;
//            stepCounter = 0;
//        }

 //   }

    protected void move(double power, int deltaTarget) throws InterruptedException {
        int leftCurrent = robot.getLeftDrive().getCurrentPosition();
        int rightCurrent = robot.getRightDrive().getCurrentPosition();
        int leftTarget  = leftCurrent + deltaTarget;
        int rightTarget = rightCurrent + deltaTarget;
        robot.getLeftDrive().setTargetPosition(leftTarget);
        robot.getRightDrive().setTargetPosition(rightTarget);

        waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(power);

        waitOneFullHardwareCycle();

        getTelemetryUtil().addData("status", "running");
        getTelemetryUtil().sendTelemetry();

        int lastLeftPos = Integer.MIN_VALUE;
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
            getTelemetryUtil().addData("leftpos", leftPos);

        while ( leftPos <= (leftTarget - 2)  || leftPos >= (leftTarget + 2) ) {
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


//        robot.getLeftDrive().setPower(0.0);
//        robot.getRightDrive().setPower(0.0);
//        waitOneFullHardwareCycle();
    }


    protected void spinLeft(double power, int deltaTarget) throws InterruptedException {
        int leftCurrent = robot.getLeftDrive().getCurrentPosition();
        int rightCurrent = robot.getRightDrive().getCurrentPosition();
        int leftTarget  = leftCurrent + deltaTarget;
        int rightTarget = rightCurrent + deltaTarget;
        robot.getLeftDrive().setTargetPosition(leftTarget);
        robot.getRightDrive().setTargetPosition(rightCurrent);

//        robot.getLeftDrive().setTargetPosition(target);
//        robot.getRightDrive().setTargetPosition(0);

        waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(0);

        waitOneFullHardwareCycle();

        getTelemetryUtil().addData("status", "running");
        getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        //int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
       // int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((leftPos < (leftTarget - 100)) || ((leftPos >= (leftTarget - 10)) &&  (lastLeftPos != leftPos ))) {
            getTelemetryUtil().addData("leftpos", leftPos);
            getTelemetryUtil().addData("lastLefPos", lastLeftPos);
            //getTelemetryUtil().addData("rightpos", rightPos);
            //getTelemetryUtil().addData("lastRightPos", lastRightPos);
            getTelemetryUtil().sendTelemetry();

            sleep(100);

            lastLeftPos = leftPos;
            //lastRightPos = rightPos;
            leftPos = robot.getLeftDrive().getCurrentPosition();
            //rightPos = robot.getRightDrive().getCurrentPosition();
        }
        getTelemetryUtil().addData("status", "at position");
        getTelemetryUtil().sendTelemetry();


//        robot.getLeftDrive().setPower(0.0);
//        robot.getRightDrive().setPower(0.0);
//        waitOneFullHardwareCycle();
    }


    protected void spinRight(double power, int deltaTarget) throws InterruptedException {
        int leftCurrent = robot.getLeftDrive().getCurrentPosition();
        int rightCurrent = robot.getRightDrive().getCurrentPosition();
        int leftTarget  = leftCurrent + deltaTarget;
        int rightTarget = rightCurrent + deltaTarget;
        robot.getLeftDrive().setTargetPosition(leftCurrent);
        robot.getRightDrive().setTargetPosition(rightTarget);



        waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(0);
        robot.getRightDrive().setPower(power);

        waitOneFullHardwareCycle();

        getTelemetryUtil().addData("status", "running");
        getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((rightPos < (rightTarget - 100)) || ((rightPos >= (rightTarget - 10)) &&  (lastRightPos != rightPos ))) {
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
    }


    protected void goBack(double power, int deltaTarget) throws InterruptedException {

        int leftCurrent = robot.getLeftDrive().getCurrentPosition();
        int rightCurrent = robot.getRightDrive().getCurrentPosition();
        int leftTarget  = leftCurrent - deltaTarget;
        int rightTarget = rightCurrent - deltaTarget;
        robot.getLeftDrive().setTargetPosition(leftTarget);
        robot.getRightDrive().setTargetPosition(rightTarget);

        //robot.getLeftDrive().setTargetPosition(target);
        //robot.getRightDrive().setTargetPosition(target);

        waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(power);

        waitOneFullHardwareCycle();

        getTelemetryUtil().addData("status", "running");
        getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((leftPos < (leftTarget - 100)) || ((leftPos >= (leftTarget - 10)) &&  (lastLeftPos != leftPos ))) {
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


        getTelemetryUtil().addData("status", "at position");
        getTelemetryUtil().sendTelemetry();


//        robot.getLeftDrive().setPower(0.0);
//        robot.getRightDrive().setPower(0.0);
//        waitOneFullHardwareCycle();
    }



}

    public  int Degrees_to_Pulse(int degrees){
        return (int)(degrees * PULSE_PER_90_DEGREE / 90.0);
    }

    public  int Inches_to_Pulse(int inches){
        return (int)(inches * PULSE_PER_FOOT / 12.0);
    }
}
