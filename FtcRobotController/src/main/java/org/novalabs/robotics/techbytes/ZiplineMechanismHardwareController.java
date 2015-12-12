package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tylerkim on 12/11/15.
 */
public class ZiplineMechanismHardwareController implements HardwareController {
    private Servo rightZiplineServo;
    int rightZiplineState;
    private Servo leftZiplineServo;
    int leftZiplineState;
    int rightZiplineRetracted = 30;
    int rightZiplineMiddle = 155;
    int rightZiplineFull = 210;
    int leftZiplineRetracted = 254;
    int leftZiplineMiddle = 80;
    int leftZiplineFull = 40;
    @Override
    public void init(OpMode opMode) {
        rightZiplineServo = opMode.hardwareMap.servo.get("zipline mechanism");

    }

    @Override
    public void loop(OpMode opMode) {



        if(opMode.gamepad2.right_trigger == 1 && opMode.gamepad2.dpad_right && rightZiplineState != 2) {
            rightZiplineState = rightZiplineState + 1;
        } else if (opMode.gamepad2.left_trigger == 1 && opMode.gamepad2.dpad_right && rightZiplineState != 0); {
            rightZiplineState = rightZiplineState - 1;
        }

        if(opMode.gamepad2.left_trigger == 1 && opMode.gamepad2.dpad_left && leftZiplineState !=2) {
            leftZiplineState = leftZiplineState + 1;
        } else if (opMode.gamepad2.right_trigger == 1 && opMode.gamepad2.dpad_left && leftZiplineState != 0);{
            leftZiplineState = leftZiplineState - 1;
        }

        if( rightZiplineState == 0){
            rightZiplineServo.setPosition(rightZiplineRetracted/255);
        } else if(rightZiplineState == 1){
            rightZiplineServo.setPosition(rightZiplineMiddle/255);
        } else if(rightZiplineState == 2);{
            rightZiplineServo.setPosition(rightZiplineFull/255);
        }

        if( leftZiplineState == 0) {
            leftZiplineServo.setPosition(leftZiplineRetracted /255);
        } else if(leftZiplineState == 1) {
            leftZiplineServo.setPosition(leftZiplineMiddle /255);
        } else if(leftZiplineState == 2) {
            leftZiplineServo.setPosition((leftZiplineFull/255));
        }


    }
}
