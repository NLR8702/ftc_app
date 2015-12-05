package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tylerkim on 12/1/15.
 */
public class TapeHardwareController implements HardwareController {
    private DcMotor TapeMotor;
    private Servo tapeGuide;
    private float tapePosition;
    @Override
    public void init(OpMode opMode) {
        TapeMotor = opMode.hardwareMap.dcMotor.get("tapeMotor");
        tapeGuide = opMode.hardwareMap.servo.get("tapeGuide");
        tapePosition=.5f;

    }

    @Override
    public void loop(OpMode opMode) {
        if (opMode.gamepad2.a) {
            TapeMotor.setPower(-.5);
        } else if (opMode.gamepad2.y) {
            TapeMotor.setPower(1);
        } else
            TapeMotor.setPower(0);


        if (opMode.gamepad2.b && tapePosition < .8) {
            tapePosition = tapePosition + .1f;

        } else if (opMode.gamepad2.x && tapePosition > .1) {
            tapePosition = tapePosition - .1f;
        }
        tapeGuide.setPosition(tapePosition);
    }
    }

