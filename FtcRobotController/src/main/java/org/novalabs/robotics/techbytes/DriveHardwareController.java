package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tylerkim on 12/1/15.
 */
public class DriveHardwareController implements HardwareController {
    private final int FORWARD_SLOW = 0;
    private final int NORMAL = 1;

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private AtomicInteger state = new AtomicInteger(0);

    @Override
    public void init(OpMode opMode) {
        state.set(NORMAL);
        leftMotor = opMode.hardwareMap.dcMotor.get("leftMotor");
        rightMotor = opMode.hardwareMap.dcMotor.get("rightMotor");
//        leftMotor.setDirection(DcMotor.Direction.REVERSE);


    }

    @Override
    public void loop(OpMode opMode) {
        double rt = opMode.gamepad1.right_trigger;

        if (opMode.gamepad1.left_stick_y != 0 || opMode.gamepad1.right_stick_y != 0) {
            state.set(NORMAL);
        }

        if (opMode.gamepad1.a) {
            state.set(NORMAL);
        }

        if (state.get() == NORMAL) {
//            leftMotor.setPower(opMode.gamepad1.left_stick_y*.5);
//            rightMotor.setPower(opMode.gamepad1.right_stick_y*.5);
            if (opMode.gamepad1.a) {
                double leftPower = opMode.gamepad1.left_stick_y * - .5;
                double rightPower = opMode.gamepad1.left_stick_y *  .5;
                rightMotor.setPower(leftPower);
                leftMotor.setPower(rightPower);
            } else {
                double leftPower = opMode.gamepad1.right_stick_y * .5;
                double rightPower = opMode.gamepad1.left_stick_y * - .5;
                rightMotor.setPower(rightPower);
                leftMotor.setPower(leftPower);
                opMode.telemetry.addData("leftMotor", leftPower);
                opMode.telemetry.addData("leftMotor", rightPower);
            }
        }
        if (state.get() == FORWARD_SLOW) {
            leftMotor.setPower(-.08);
            rightMotor.setPower(-.08);
        }//        if (opMode.gamepad1.a) {
//            rightMotor.setPower(.2);w
//            leftMotor.setPower(.2);
//        } else { leftMotor.setPower(opMode.gamepad1.left_stick_y);
//            rightMotor.setPower(opMode.gamepad1.right_stick_y);}


        }


    public void forwardSlow() {
        this.state.set(FORWARD_SLOW);
    }

    public void normal() {
        this.state.set(NORMAL);
    }



}
