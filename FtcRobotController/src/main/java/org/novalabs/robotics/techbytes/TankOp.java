package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Zach Shuster on 11/15/2015.
 */
public class TankOp extends OpMode {
    private DcMotor motorOne;
    private DcMotor motorTwo;
    private DcMotor motorThree;
//    private DcMotor motorFour;

    @Override
    public void init() {
        motorOne = hardwareMap.dcMotor.get("left_drive");
        motorTwo = hardwareMap.dcMotor.get("right_drive");
        motorThree = hardwareMap.dcMotor.get("motor_3");
//        motorFour = hardwareMap.dcMotor.get("motor_4");

    }

    @Override
    public void loop() {
        double yl = gamepad1.left_stick_y;
        double rt = gamepad1.right_trigger;
        double lt = gamepad1.left_trigger;
        double yr = gamepad1.right_stick_y;

        telemetry.addData("rt", gamepad1.right_trigger);

        motorOne.setPower(yl);
        motorTwo.setPower(yr);


        if (gamepad1.left_bumper) {
            motorThree.setPower(.5);

        } else if (gamepad1.right_bumper) {
            motorThree.setPower(-.5);
        } else {
            motorThree.setPower(0);

        }
    }
}