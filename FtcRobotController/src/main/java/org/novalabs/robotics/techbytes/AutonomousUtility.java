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




    private static void sleep(long milliseconds)throws InterruptedException {
        Thread.sleep(milliseconds);
    }
    private static int Degrees_to_Pulse(int degrees){

        int x = 1940*degrees/90;
        return x;
    }
    private static int Inches_to_Pulse(int inches){
        int xx = 1120*inches/12;
        return xx;
    }

    public static void GoUpMountain(Team8702Bot robot, int inches) throws InterruptedException {


    }
    public static void Backwards(Team8702Bot robot, int inches) throws InterruptedException {

    }

    public static void turnRight(ActiveOpMode opMode, Team8702Bot robot, int degree ) throws InterruptedException {
       double rightPower = 2.3;
       int pulse = Degrees_to_Pulse(degree);


        turnRightWithPulse(opMode, robot, rightPower, pulse);


    }
    private static void turnRightWithPulse(ActiveOpMode opMode, Team8702Bot robot, double power, int target ) throws InterruptedException {
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


        robot.getLeftDrive().setPower(0.0);
        robot.getRightDrive().setPower(0.0);
        opMode.waitOneFullHardwareCycle();
    }

    private static void turnLeftWithPulse(ActiveOpMode opMode, Team8702Bot robot, double power, int target) throws InterruptedException {
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
    }

    public static void turnLeft(ActiveOpMode opMode, Team8702Bot robot, int degree) throws InterruptedException {
        double leftPower = 0.3;
        int pulse = Degrees_to_Pulse(degree);
        turnRightWithPulse(opMode, robot, leftPower, pulse );
    }
    public static void Forwards(ActiveOpMode opMode, Team8702Bot robot, int inches) throws InterruptedException {
        double power = 0.3;
        int pulse = Inches_to_Pulse(inches);
        ForwardsWithPulse(opMode, robot, power, pulse);
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

    public static void stop(DcMotor leftMotor, DcMotor rightMotor){

    }


}

