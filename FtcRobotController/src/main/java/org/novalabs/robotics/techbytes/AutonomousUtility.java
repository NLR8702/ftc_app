package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftcTeam.Team8702Bot;
import org.ftcbootstrap.ActiveOpMode;

/**
 * Created by Zach Shuster on 1/31/2016.
 */
public class AutonomousUtility {


    static final double PULSE_PER_90_DEGREE = 1940;
    static final double PULSE_PER_FOOT = 1120;
//  Operation Methods
    private static void sleep(long milliseconds)throws InterruptedException {
        Thread.sleep(milliseconds);
    }
    private static int Degrees_to_Pulse(int degrees){
        return (int)(degrees * PULSE_PER_90_DEGREE / 90.0);
    }

    private static int Inches_to_Pulse(int inches){
        return (int)(inches * PULSE_PER_FOOT / 12.0);
    }

//  Turn Methods
    public static void turnRight(ActiveOpMode opMode, Team8702Bot robot, int degree ) throws InterruptedException {
       double rightPower = 0.3;
       int pulse = Degrees_to_Pulse(degree);
        //turnRightWithPulse(opMode, robot, rightPower, pulse);
        spinRight(opMode, robot, 0.3, pulse);

    }
    public static void turnLeft(ActiveOpMode opMode, Team8702Bot robot, int degree) throws InterruptedException {
        double leftPower = 0.3;
        int pulse = Degrees_to_Pulse(degree);
        turnRightWithPulse(opMode, robot, leftPower, pulse );
    }


    private static void turnRightWithPulse(ActiveOpMode opMode, Team8702Bot robot, double power, int target ) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(target);
        robot.getRightDrive().setTargetPosition(0);

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        opMode.waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(0);

        opMode.waitOneFullHardwareCycle();

        opMode.getTelemetryUtil().addData("status", "running");
        opMode.getTelemetryUtil().sendTelemetry();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();

        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((leftPos < (target - 100)) || ((leftPos >= (target - 10)) &&  (lastLeftPos != leftPos ))) {
            opMode.getTelemetryUtil().addData("leftpos", leftPos);
            opMode.getTelemetryUtil().addData("lastLefPos", lastLeftPos);
            opMode.getTelemetryUtil().addData("rightpos", rightPos);
            opMode.getTelemetryUtil().addData("lastRightPos", lastRightPos);
            opMode.getTelemetryUtil().sendTelemetry();

            sleep(100);

            lastLeftPos = leftPos;
            lastRightPos = rightPos;
            leftPos = robot.getLeftDrive().getCurrentPosition();
            rightPos = robot.getRightDrive().getCurrentPosition();
        }


        opMode.getTelemetryUtil().addData("status", "at position");
        opMode.getTelemetryUtil().sendTelemetry();


        robot.getLeftDrive().setPower(0.0);
        robot.getRightDrive().setPower(0.0);
        opMode.waitOneFullHardwareCycle();
    }
    public static void Forwards(ActiveOpMode opMode, Team8702Bot robot, int inches) throws InterruptedException {
        double power = 0.3;
        int pulse = Inches_to_Pulse(inches);
        ForwardsWithPulse(opMode, robot, power, pulse);
    }

    // new method
    private void Forward(ActiveOpMode opMode, Team8702Bot robot,double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(target);
        robot.getRightDrive().setTargetPosition(target);

        opMode.waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(power);

        opMode.waitOneFullHardwareCycle();

        opMode.getTelemetryUtil().addData("status", "running");
        opMode.getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((leftPos < (target - 100)) || ((leftPos >= (target - 10)) &&  (lastLeftPos != leftPos ))) {
            opMode.getTelemetryUtil().addData("leftpos", leftPos);
            opMode.getTelemetryUtil().addData("lastLefPos", lastLeftPos);
            opMode.getTelemetryUtil().addData("rightpos", rightPos);
            opMode.getTelemetryUtil().addData("lastRightPos", lastRightPos);
            opMode.getTelemetryUtil().sendTelemetry();

            sleep(100);

            lastLeftPos = leftPos;
            lastRightPos = rightPos;
            leftPos = robot.getLeftDrive().getCurrentPosition();
            rightPos = robot.getRightDrive().getCurrentPosition();
        }


        opMode.getTelemetryUtil().addData("status", "at position");
        opMode.getTelemetryUtil().sendTelemetry();


//        robot.getLeftDrive().setPower(0.0);
//        robot.getRightDrive().setPower(0.0);
//        opMode.waitOneFullHardwareCycle();
    }

    private static void ForwardsWithPulse(ActiveOpMode opMode, Team8702Bot robot, double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(target);
        robot.getRightDrive().setTargetPosition(target);

        opMode.waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(power);

        opMode.waitOneFullHardwareCycle();

        opMode.getTelemetryUtil().addData("status", "running");
        opMode.getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((leftPos < (target - 100)) || ((leftPos >= (target - 10)) &&  (lastLeftPos != leftPos ))) {
            opMode.getTelemetryUtil().addData("leftpos", leftPos);
            opMode.getTelemetryUtil().addData("lastLefPos", lastLeftPos);
            opMode.getTelemetryUtil().addData("rightpos", rightPos);
            opMode.getTelemetryUtil().addData("lastRightPos", lastRightPos);
            opMode.getTelemetryUtil().sendTelemetry();

            sleep(100);

            lastLeftPos = leftPos;
            lastRightPos = rightPos;
            leftPos = robot.getLeftDrive().getCurrentPosition();
            rightPos = robot.getRightDrive().getCurrentPosition();
        }


        opMode.getTelemetryUtil().addData("status", "at position");
        opMode.getTelemetryUtil().sendTelemetry();


        robot.getLeftDrive().setPower(0.0);
        robot.getRightDrive().setPower(0.0);
        opMode.waitOneFullHardwareCycle();
    }

    public static void Stop(Team8702Bot robot){
        robot.getLeftDrive().setPower(1);
        robot.getRightDrive().setPower(1);

    }

    private static void spinRight(ActiveOpMode opMode, Team8702Bot robot, double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(target);
        robot.getRightDrive().setTargetPosition(0);

        opMode.waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(power);
        robot.getRightDrive().setPower(0);

        opMode.waitOneFullHardwareCycle();

        opMode.getTelemetryUtil().addData("status", "running");
        opMode.getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((leftPos < (target - 100)) || ((leftPos >= (target - 10)) &&  (lastLeftPos != leftPos ))) {
            opMode.getTelemetryUtil().addData("leftpos", leftPos);
            opMode.getTelemetryUtil().addData("lastLefPos", lastLeftPos);
            opMode.getTelemetryUtil().addData("rightpos", rightPos);
            opMode.getTelemetryUtil().addData("lastRightPos", lastRightPos);
            opMode.getTelemetryUtil().sendTelemetry();

            sleep(100);

            lastLeftPos = leftPos;
            lastRightPos = rightPos;
            leftPos = robot.getLeftDrive().getCurrentPosition();
            rightPos = robot.getRightDrive().getCurrentPosition();
        }
        opMode.getTelemetryUtil().addData("status", "at position");
        opMode.getTelemetryUtil().sendTelemetry();


//        robot.getLeftDrive().setPower(0.0);
//        robot.getRightDrive().setPower(0.0);
//        waitOneFullHardwareCycle();
    }

    // NEW GOOD METHOD
    private void turnLeftWithPulse(ActiveOpMode opMode, Team8702Bot robot, double power, int target) throws InterruptedException {
        robot.getLeftDrive().setTargetPosition(0);
        robot.getRightDrive().setTargetPosition(target);

        opMode.waitOneFullHardwareCycle();

        robot.getLeftDrive().setPower(0);
        robot.getRightDrive().setPower(power);

        opMode.waitOneFullHardwareCycle();

        opMode.getTelemetryUtil().addData("status", "running");
        opMode.getTelemetryUtil().sendTelemetry();

        int lastLeftPos = robot.getLeftDrive().getCurrentPosition();
        int lastRightPos = robot.getRightDrive().getCurrentPosition();

        int leftPos = robot.getLeftDrive().getCurrentPosition();
        int rightPos = robot.getRightDrive().getCurrentPosition();
        // while (robot.getLeftDrive().getCurrentPosition() < 2000 ||  (robot.getLeftDrive().getCurrentPosition() < (2000 - 100)) &&  (lastPos != robot.getLeftDrive().getCurrentPosition() )) {
        while ((rightPos < (target - 100)) || ((rightPos >= (target - 10)) &&  (lastRightPos != rightPos ))) {
            opMode.getTelemetryUtil().addData("leftpos", leftPos);
            opMode.getTelemetryUtil().addData("lastLefPos", lastLeftPos);
            opMode.getTelemetryUtil().addData("rightpos", rightPos);
            opMode.getTelemetryUtil().addData("lastRightPos", lastRightPos);
            opMode.getTelemetryUtil().sendTelemetry();

            sleep(100);

            lastLeftPos = leftPos;
            lastRightPos = rightPos;
            leftPos = robot.getLeftDrive().getCurrentPosition();
            rightPos = robot.getRightDrive().getCurrentPosition();
        }
    }

}

