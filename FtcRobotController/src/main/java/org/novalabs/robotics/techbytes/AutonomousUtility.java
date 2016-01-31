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
    private static double first_forward = .25;
    private static int Right_90Sleep = 1050;



    private static void sleep(long milliseconds)throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    public static void goUpMountain(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(.35);
        leftMotor.setPower(.35);
        sleep(3000);
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
        leftMotor.setPower(0);
        rightMotor.setPower(.25);
        sleep(Right_90Sleep);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void first_forwards(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(first_forward);
        leftMotor.setPower(first_forward);
        sleep(500);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }


    public static void stop(DcMotor leftMotor, DcMotor rightMotor){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }


}

