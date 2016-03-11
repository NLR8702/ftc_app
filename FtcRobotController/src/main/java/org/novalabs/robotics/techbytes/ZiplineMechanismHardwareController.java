package org.novalabs.robotics.techbytes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by tylerkim on 12/11/15.
 */
public class ZiplineMechanismHardwareController implements HardwareController {
    enum ButtonState{
        UP, DOWN
    }
    private static final String TAG = "Zipline";
    private Servo rightZiplineServo;
    int rightZiplineState=0;
    private Servo leftZiplineServo;
    int leftZiplineState=0;
    double rightZiplineRetracted = 1;
    double rightZiplineMiddle = .13;
    double rightZiplineFull = 0;
    double leftZiplineRetracted =0;
    double leftZiplineMiddle =.86;
    double leftZiplineFull =1;
    ButtonState rightbuttonState;
    ButtonState leftbuttonState;

    @Override
    public void init(OpMode opMode) {
        rightbuttonState= ButtonState.UP;
        leftbuttonState= ButtonState.UP;
        rightZiplineServo = opMode.hardwareMap.servo.get("rightZiplineMotor");
        leftZiplineServo = opMode.hardwareMap.servo.get("leftZiplineMotor");
        rightZiplineState = 0;
        leftZiplineState = 0;
        rightZiplineServo.setPosition(rightZiplineRetracted);
        leftZiplineServo.setPosition(leftZiplineRetracted);
    }

    @Override
    public void loop(OpMode opMode) {
        opMode.telemetry.addData("Zipline rightstate", rightZiplineState);

        if (rightbuttonState == ButtonState.UP && opMode.gamepad2.right_trigger == 1 && opMode.gamepad2.dpad_right) {

            if (rightZiplineState == 0) {
                rightZiplineState = 1;
            } else if (rightZiplineState == 1) {
                rightZiplineState = 0;

//            } else if (rightZiplineState == 2) {
//                rightZiplineState = 0;
            }
            rightbuttonState = ButtonState.DOWN;
        } else if (rightbuttonState == ButtonState.DOWN && opMode.gamepad2.dpad_right == false) {

            rightbuttonState = ButtonState.UP;
        }
        if (rightZiplineState == 0) {
            rightZiplineServo.setPosition(rightZiplineRetracted);
        } else if (rightZiplineState == 1) {
            rightZiplineServo.setPosition(rightZiplineMiddle);
        } else if (rightZiplineState == 0) {
            rightZiplineServo.setPosition(rightZiplineFull);
        }
        opMode.telemetry.addData("right servo: ", rightZiplineServo.getPosition());


        opMode.telemetry.addData("Zipline leftstate", leftZiplineState);
        if (leftbuttonState == ButtonState.UP && opMode.gamepad2.left_trigger == 1 && opMode.gamepad2.dpad_left) {

            if (leftZiplineState == 0) {
                leftZiplineState = 1;
            } else if (leftZiplineState == 1) {
                leftZiplineState = 0;

//            } else if (leftZiplineState ==2 ) {
//                leftZiplineState = 0;
            }
            leftbuttonState = ButtonState.DOWN;
        } else if (leftbuttonState == ButtonState.DOWN && opMode.gamepad2.dpad_left == false) {

            leftbuttonState = ButtonState.UP;
        }
        if (leftZiplineState == 0) {
            leftZiplineServo.setPosition(leftZiplineRetracted);
        } else if (leftZiplineState == 1) {
            leftZiplineServo.setPosition(leftZiplineMiddle);
        } else if (leftZiplineState == 2) {
            leftZiplineServo.setPosition(leftZiplineFull);
        }
        opMode.telemetry.addData("left servo: ", leftZiplineServo.getPosition());

    }

    //    @Override
    public String getName() {
        return "ZiplineMechanismHardwareController";
    }
}
