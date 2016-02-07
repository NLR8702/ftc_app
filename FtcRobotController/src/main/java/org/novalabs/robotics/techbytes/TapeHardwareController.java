package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by tylerkim on 12/1/15.
 */
public class TapeHardwareController implements HardwareController {
    private DcMotor TapeMotor;
    private Servo tapeGuide;
    private float tapePosition;
    private float increment;
    private Servo tapeLock;
    private OpMode opMode;
    //private DriveHardwareController driveController;
    public Telemetry telemetry = new Telemetry();

    public TapeHardwareController() {
    }

    @Override
    public void init(OpMode opMode) {
        this.opMode = opMode;
        TapeMotor = opMode.hardwareMap.dcMotor.get("tapeMotor");
        tapeGuide = opMode.hardwareMap.servo.get("tapeGuide");
        //tapeLock= opMode.hardwareMap.servo.get("tapeLock");


        increment=.05f;
        tapePosition=.5f;

        tapeLock.setPosition(0.5);
    }

    @Override
    public void loop(OpMode opMode) {

        double ry= opMode.gamepad2.right_stick_y;
        double ly= opMode.gamepad2.left_stick_y;

        TapeMotor.setPower(ry);
//        tapeGuide.setPosition(ly*.5);

//        if (opMode.gamepad2.a) {
//            TapeMotor.setPower(1);
//            driveController.forwardSlow();
//        } else if (opMode.gamepad2.y) {
//            TapeMotor.setPower(-.5);
//            driveController.normal();
//        } else
//            TapeMotor.setPower(0);


        if (opMode.gamepad2.b && tapePosition < (1-increment)) {
            tapePosition = tapePosition + increment;

        } else if (opMode.gamepad2.x && tapePosition > (0+increment)) {
            tapePosition = tapePosition - increment;
        }
        if (opMode.gamepad2.left_bumper) {
            tapeLock.setPosition(0.4);
        } else if (opMode.gamepad2.right_bumper) {
            tapeLock.setPosition(0);
        }
        telemetry.addData("tapePosition", tapePosition);
        tapeGuide.setPosition(tapePosition);
    }

}



