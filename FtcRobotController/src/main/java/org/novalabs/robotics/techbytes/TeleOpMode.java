package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Zach Shuster on 11/15/2015.
 */
public class TeleOpMode extends OpMode {
    private DcMotor motorOne;
    private DcMotor motorTwo;
    private DcMotor motorThree;
    private DcMotor motorFour;
    private Servo sweeperServo;
    private Servo dumperServo;

    @Override
    public void init() {
        motorOne = hardwareMap.dcMotor.get("motorl");
        motorTwo = hardwareMap.dcMotor.get("motorr");
        motorThree = hardwareMap.dcMotor.get("amotor1");
        motorFour = hardwareMap.dcMotor.get("amotor2");
        sweeperServo = hardwareMap.servo.get("amotor3");
        dumperServo = hardwareMap.servo.get("amotor4");
    }


        @Override
        public void loop ()
        {
            double yl = gamepad1.left_stick_y;
            double rt = gamepad1.right_trigger;
            double lt = gamepad1.left_trigger;
            double yr = gamepad1.right_stick_y;

            telemetry.addData("rt", gamepad1.right_trigger);

            motorOne.setPower(yl);
            motorTwo.setPower(yr);


//        telemetry.addData("");
            if (gamepad2.y) {
                motorThree.setPower(-.25);
                motorFour.setPower(.25);

            } else if (gamepad2.a) {
                motorThree.setPower(.25);
                motorFour.setPower(-.25);

            } else {
                motorThree.setPower(0);
                motorFour.setPower(0);
            }
        }
    }


