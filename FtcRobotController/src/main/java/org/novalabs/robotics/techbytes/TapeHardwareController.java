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
    @Override
    public void init(OpMode opMode) {
        TapeMotor = opMode.hardwareMap.dcMotor.get("tapeMotor");
        tapeGuide = opMode.hardwareMap.servo.get("tapeGuide");


    }

    @Override
    public void loop(OpMode opMode) {
        if(opMode.gamepad2.a){
            TapeMotor.setPower(-0.5);
        } else if(opMode.gamepad2.y){
            TapeMotor.setPower(0.5);
        } else
            TapeMotor.setPower(0);
        if(opMode.gamepad2.dpad_up){
            tapeGuide.setPosition(1);
        } else if(opMode.gamepad2.dpad_down){
            tapeGuide.setPosition(0);
        }



    }
}
