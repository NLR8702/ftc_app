package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tylerkim on 12/1/15.
 */
public class DumperHardwareController implements HardwareController {
    private Servo DumperMotor;
    private Servo RightSweeperMotor;
    private Servo LeftSweeperMotor;
    private float LeftSweeperPosition;
    private float RightSweeperPosition;
    @Override
    public void init(OpMode opMode) {
        DumperMotor = opMode.hardwareMap.servo.get("dumpMotor");
        RightSweeperMotor = opMode.hardwareMap.servo.get("rightSweeperMotor");
        LeftSweeperMotor = opMode.hardwareMap.servo.get("leftSweeperMotor");
        RightSweeperPosition = 1f;
        LeftSweeperPosition = 0f;
        // :)

    }

    @Override
    public void loop(OpMode opMode) {


        if (opMode.gamepad2.dpad_up){
            DumperMotor.setPosition(1);
        }else if (opMode.gamepad2.dpad_down){
            DumperMotor.setPosition(0);
        }

        if (opMode.gamepad2.dpad_left && RightSweeperPosition < .9) {
            RightSweeperPosition = RightSweeperPosition + .1f;

        } else if (!opMode.gamepad2.dpad_left && RightSweeperPosition > .1) {
            RightSweeperPosition = RightSweeperPosition - .1f;
        }
        RightSweeperMotor.setPosition(RightSweeperPosition);

        if (opMode.gamepad2.dpad_right && LeftSweeperPosition > .1) {
            LeftSweeperPosition = LeftSweeperPosition - .1f;

        } else if (!opMode.gamepad2.dpad_right && LeftSweeperPosition < .9){
            LeftSweeperPosition = LeftSweeperPosition + .1f;
        }
        LeftSweeperMotor.setPosition(LeftSweeperPosition);



    }
}
//