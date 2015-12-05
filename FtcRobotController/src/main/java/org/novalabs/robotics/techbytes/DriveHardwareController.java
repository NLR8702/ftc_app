package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by tylerkim on 12/1/15.
 */
public class DriveHardwareController implements HardwareController {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    @Override
    public void init(OpMode opMode) {
        leftMotor = opMode.hardwareMap.dcMotor.get("leftMotor");
        rightMotor = opMode.hardwareMap.dcMotor.get("rightMotor");


    }

    @Override
    public void loop(OpMode opMode) {
        double  rt = opMode. gamepad1.right_trigger;

        leftMotor.setPower(opMode.gamepad1.left_stick_y);
        rightMotor.setPower(opMode.gamepad1.right_stick_y);

        if (opMode.gamepad1.a) {
            rightMotor.setPower(opMode.gamepad1.left_stick_y);
            leftMotor.setPower(opMode.gamepad1.left_stick_y);
        } else {
            leftMotor.setPower(opMode.gamepad1.left_stick_y);
            rightMotor.setPower(opMode.gamepad1.right_stick_y);
        }





//        if (opMode.gamepad1.a) {
//            rightMotor.setPower(.2);
//            leftMotor.setPower(.2);
//        } else { leftMotor.setPower(opMode.gamepad1.left_stick_y);
//            rightMotor.setPower(opMode.gamepad1.right_stick_y);}

    }
}
