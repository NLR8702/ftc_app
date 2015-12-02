package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Zach Shuster on 11/15/2015.
 */
public class TeleOpMode extends OpMode {
    private DcMotor motorLeftFront;
    private DcMotor motorLeftBack;
    private DcMotor motorRightFront;
    private DcMotor motorRightBack;
    private DcMotor motorTape;
    private Servo sweeperServo;
    private Servo dumperServo;
    private Servo positionServo;
    private Servo kickstandservo;
    private float sweepPosition;
    private float kickPosition;

    @Override
    public void init() {
        motorLeftFront = hardwareMap.dcMotor.get("motorlf");
        motorLeftBack = hardwareMap.dcMotor.get("motorlb");
        motorRightFront = hardwareMap.dcMotor.get("motorrf");
        motorRightBack = hardwareMap.dcMotor.get("motorrb");
        motorTape = hardwareMap.dcMotor.get("amoter1");
        sweeperServo = hardwareMap.servo.get("amotor2");
        dumperServo = hardwareMap.servo.get("amotor3");
        positionServo = hardwareMap.servo.get("amoter4");
        kickstandservo = hardwareMap.servo.get("amoter5");
        kickPosition = 0f;
        sweepPosition = .5f;
    }
        @Override
        public void loop()

        {
            double yl = gamepad1.left_stick_y;
            double rt = gamepad1.right_trigger;
            double lt = gamepad1.left_trigger;
            double yr = gamepad1.right_stick_y;


            telemetry.addData("rt", gamepad1.right_trigger);

            motorLeftFront.setPower(yl);
            motorLeftBack.setPower(yl);
            motorRightFront.setPower(yr);
            motorRightBack.setPower(yr);



            if (gamepad2.dpad_down) {
                dumperServo.setPosition(1);

            } else if (gamepad2.dpad_up) {
                dumperServo.setPosition(0);
                }




// telemetry.addData("");
            if (gamepad2.y) {
                motorTape.setPower(-.25);


            } else if (gamepad2.a) {
                motorTape.setPower(.25);

            } else {
                motorTape.setPower(0);
            }




            if (gamepad2.dpad_left && sweepPosition < .9) {
                sweepPosition = sweepPosition + .1f;

            } else if (gamepad2.dpad_right && sweepPosition > .1) {
                sweepPosition=sweepPosition-.1f;
            }
            sweeperServo.setPosition(sweepPosition);

            if (gamepad1.right_bumper && kickPosition < .9) {
                kickPosition = kickPosition + .1f;

            } else if (gamepad1.left_bumper && kickPosition > .1) {
                kickPosition=kickPosition-.1f;
            }
            sweeperServo.setPosition(kickPosition);
            kickstandservo.setPosition(kickPosition);
        }

    }





