package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tylerkim on 12/1/15.
 */
public class DumperHardwareController implements HardwareController {
    private Servo DumperMotor;


    @Override
    public void init(OpMode opMode) {
        DumperMotor = opMode.hardwareMap.servo.get("dumpMotor");


        // :)

    }

    @Override
    public void loop(OpMode opMode) {


        if (opMode.gamepad2.dpad_up){
            DumperMotor.setPosition(1);
        }else if (opMode.gamepad2.dpad_down){
            DumperMotor.setPosition(0);
        }


    }
}
//