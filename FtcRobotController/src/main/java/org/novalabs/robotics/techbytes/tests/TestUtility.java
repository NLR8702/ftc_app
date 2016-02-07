package org.novalabs.robotics.techbytes.tests;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Zach Shuster on 1/31/2016.
 */
public class TestUtility {
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

    public static void Straight(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(0.25);
        leftMotor.setPower(0.25);
        sleep(6500);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void RightTurn(DcMotor leftMotor, DcMotor rightMotor) throws InterruptedException {
        rightMotor.setPower(0.25);
        leftMotor.setPower(0);
        sleep(2250);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void LeftTurn(DcMotor leftMotor, DcMotor rightMotor)
        throws InterruptedException {
        rightMotor.setPower(0);
        leftMotor.setPower(0.5);
        sleep(175);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
    public static void ShortStraight(DcMotor leftMotor, DcMotor rightMotor)
        throws InterruptedException {
        rightMotor.setPower(0.5);
        leftMotor.setPower(0.5);
        sleep(4500);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }


    public static void stop(DcMotor leftMotor, DcMotor rightMotor){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }


}

