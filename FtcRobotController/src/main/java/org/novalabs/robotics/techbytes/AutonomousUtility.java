package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Zach Shuster on 1/31/2016.
 */
public class AutonomousUtility {
    private static float onward = (float) .5;
    private static float retreat = (float) -.5;
    private static float everest = (float) .5;
    private static float starboard = (float).5;
    private static float port = (float) .5;



    private static void sleep(long milliseconds)throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public static void goUpMountain(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(.5);
        leftMotor.setPower(.5);
        sleep(1000);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void backwards(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(-.5);
        leftMotor.setPower(-.5);
        sleep(2500);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void leftTurn(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(.5);
        leftMotor.setPower(0);
        sleep(500);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void rightTurn(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(0);
        leftMotor.setPower(.5);
        sleep(500);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void forwards(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(1);
        leftMotor.setPower(1);
        sleep(5000);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }



}

