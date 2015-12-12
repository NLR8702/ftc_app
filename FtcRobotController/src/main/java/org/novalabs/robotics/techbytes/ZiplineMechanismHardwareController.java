package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tylerkim on 12/11/15.
 */
public class ZiplineMechanismHardwareController implements HardwareController {
    private Servo rightZiplineServo;
    int rightZiplineState=0;
    private Servo leftZiplineServo;
    int leftZiplineState=0;
    int rightZiplineRetracted = 40;
    int rightZiplineMiddle = 80;
    int rightZiplineFull = 254;
    int leftZiplineRetracted = 254;
    int leftZiplineMiddle = 210;
    int leftZiplineFull = 31;
    @Override
    public void init(OpMode opMode) {
        rightZiplineServo = opMode.hardwareMap.servo.get("rightZiplineMotor");
        leftZiplineServo = opMode.hardwareMap.servo.get("leftZiplineMotor");
        rightZiplineState = 0;
        leftZiplineState = 0;
        rightZiplineServo.setPosition(rightZiplineRetracted/255);
        leftZiplineServo.setPosition(leftZiplineRetracted/255);
    }

    @Override
    public void loop(OpMode opMode) {
        if(opMode.gamepad2.right_trigger == 1 && opMode.gamepad2.dpad_right){

            if(rightZiplineState == 0){
                rightZiplineServo.setPosition(rightZiplineMiddle/255);
                rightZiplineState = 1;
            }else if(rightZiplineState == 1) {
                rightZiplineServo.setPosition(rightZiplineFull/255);
                rightZiplineState = 2;
            }else { // state is 2
                rightZiplineServo.setPosition(rightZiplineRetracted/255);
                rightZiplineState = 0;
            }
        }

        if(opMode.gamepad2.left_trigger == 1 && opMode.gamepad2.dpad_left){

            if(leftZiplineState == 0){
                leftZiplineServo.setPosition(leftZiplineMiddle/255);
                leftZiplineState = 1;
            }else if(leftZiplineState == 1) {
                leftZiplineServo.setPosition(leftZiplineFull/255);
                leftZiplineState = 2;
            }else {  // state is 2
                leftZiplineServo.setPosition(leftZiplineRetracted/255);
                leftZiplineState = 0;
            }
        }
//        if(opMode.gamepad2.right_trigger == 1 && opMode.gamepad2.dpad_right && rightZiplineState != 2) {
//            rightZiplineState = rightZiplineState + 1;
//        } else if (opMode.gamepad2.left_trigger == 1 && opMode.gamepad2.dpad_right && rightZiplineState != 0); {
//            rightZiplineState = rightZiplineState - 1;
//        }
//
//        if(opMode.gamepad2.left_trigger == 1 && opMode.gamepad2.dpad_left && leftZiplineState !=2) {
//            leftZiplineState = leftZiplineState + 1;
//        } else if (opMode.gamepad2.right_trigger == 1 && opMode.gamepad2.dpad_left && leftZiplineState != 0);{
//            leftZiplineState = leftZiplineState - 1;
//        }
//
//        if( rightZiplineState == 0){
//            rightZiplineServo.setPosition(rightZiplineRetracted/255);
//        } else if(rightZiplineState == 1){
//            rightZiplineServo.setPosition(rightZiplineMiddle/255);
//        } else if(rightZiplineState == 2);{
//            rightZiplineServo.setPosition(rightZiplineFull/255);
//        }
//
//        if( leftZiplineState == 0) {
//            leftZiplineServo.setPosition(leftZiplineRetracted /255);
//        } else if(leftZiplineState == 1) {
//            leftZiplineServo.setPosition(leftZiplineMiddle /255);
//        } else if(leftZiplineState == 2) {
//            leftZiplineServo.setPosition((leftZiplineFull/255));
//        }


    }
}
