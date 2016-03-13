package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tylerkim on 12/1/15.
 */
public class DumperHardwareController implements HardwareController {
    private Servo DumperMotor;
    private float increment;
    private float dumperPosition;


    @Override
    public void init(OpMode opMode) {
        DumperMotor = opMode.hardwareMap.servo.get("dumpMotor");
        DumperMotor.setPosition(1);
        dumperPosition=1;
        increment = .005f;


        //

    }

    @Override
    public void loop(OpMode opMode) {


//        if (opMode.gamepad2.dpad_up) {
//            DumperMotor.setPosition(1);
//        } else if (opMode.gamepad2.dpad_down) {
//            DumperMotor.setPosition(0);
        if (opMode.gamepad2.dpad_up && dumperPosition > (0 + increment)) {
            dumperPosition = dumperPosition - increment;

        } else if (opMode.gamepad2.dpad_down && dumperPosition < (1 - increment)) {
            dumperPosition = dumperPosition + increment;
        }
        DumperMotor.setPosition(dumperPosition);
    }
}
//