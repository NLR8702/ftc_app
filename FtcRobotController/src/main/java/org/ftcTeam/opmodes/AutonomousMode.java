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
    private Team8702Bot robot;

    private TankDriveToEncoder tankDriveToEncoder;
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
        robot.getRightDrive().setDirection(DcMotor.Direction.REVERSE);

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
        int target = Inches_to_Pulse(24);  // 1120 = 1ft, 93 = 1inch, attempting to go 15inches

       // getTelemetryUtil().addData("status", "about to go forward 1");
       // getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
       // getTelemetryUtil().sendTelemetry();


        goForward(0.3, target);

        waitOneFullHardwareCycle();


        target = target+Degrees_to_Pulse(45); // attempting to go turn 45 degrees 970 * 2 = 90 degrees

        //getTelemetryUtil().addData("status", "about to go forward 2");
       // getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
       // getTelemetryUtil().sendTelemetry();
//        spinLeft(0.3, target);

//        waitOneFullHardwareCycle();


        spinLeft(0.3, target);

        waitOneFullHardwareCycle();
        target = target + Inches_to_Pulse(48);
        goForward(0.3, target);

        waitOneFullHardwareCycle();
        target = target + Inches_to_Pulse(12);
        goBack(0.3, target);

        waitOneFullHardwareCycle();
        target = target + Degrees_to_Pulse(90);
        spinRight(0.1, target);



/*

        target = target+(GO_FORWARD_3_FEET)+2240+(186); // forward 3 + 2 ft + 2 inches

        getTelemetryUtil().addData("status", "about to go forward 3");
        getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().sendTelemetry();
        goForward(0.3, target);

        waitOneFullHardwareCycle();
        target = target+2240+93; // forward 2ft + 1 inch

        getTelemetryUtil().addData("status", "about to go forward 4");
        getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().sendTelemetry();
        goForward(-0.3, target);

        waitOneFullHardwareCycle();
        target = target+PULSES_PER_90;

        getTelemetryUtil().addData("status", "about to go forward 5");
        getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().sendTelemetry();
        spinLeft(0.3, target);

        waitOneFullHardwareCycle();
        target = target+(GO_FORWARD_3_FEET-93);

        getTelemetryUtil().addData("status", "about to go forward 6");
        getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().sendTelemetry();
        goForward(0.3, target);
*/

//        waitOneFullHardwareCycle();
//        sleep(5000);

/*        waitOneFullHardwareCycle();
        target = target+10000;

        getTelemetryUtil().addData("status", "about to go forward 7");
        getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().sendTelemetry();
        goForward(0.03, target);*/
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


//        robot.getLeftDrive().setPower(0.0);
//        robot.getRightDrive().setPower(0.0);
//        waitOneFullHardwareCycle();
    }


    private void spinRight(double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(target);
        robot.getRightDrive().setTargetPosition(0);

        waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(0);

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


//        robot.getLeftDrive().setPower(0.0);
//        robot.getRightDrive().setPower(0.0);
//        waitOneFullHardwareCycle();
    }


    private void spinLeft(double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(0);
        robot.getRightDrive().setTargetPosition(target);

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
        while ((rightPos < (target - 100)) || ((rightPos >= (target - 10)) &&  (lastRightPos != rightPos ))) {
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


    private void goBack(double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(target);
        robot.getRightDrive().setTargetPosition(target);
        robot.getLeftDrive().setDirection(DcMotor.Direction.REVERSE);
        robot.getRightDrive().setDirection(DcMotor.Direction.REVERSE);

        waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(-power);
        robot.getRightDrive().setPower(-power);

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
