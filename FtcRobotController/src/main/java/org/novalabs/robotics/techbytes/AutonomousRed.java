package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Zach Shuster on 1/21/2016.
 */
public class AutonomousRed extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
//    private void turnleft;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");

        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        waitForStart();
        leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        leftMotor.setTargetPosition(100);
        rightMotor.setTargetPosition(100);
//        leftMotor.setPower(.25);
//        rightMotor.setPower(.25);
//        sleep(5000);
//        leftMotor.setTargetPosition(30);
//        rightMotor.setTargetPosition(0);
//        leftMotor.setPower(0);
//        rightMotor.setPower(0);



    }
}

