package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Zach Shuster on 12/28/2015.
 * Tested with some success on 12/30/2015
 */
public class AutonomousMode extends OpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private int loopRotations = 0;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    @Override
    public void loop() {
        loopRotations = loopRotations + 1;
        if (loopRotations == 1) {
            leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        } else if (loopRotations == 2) {
            leftMotor.setTargetPosition(560);
            rightMotor.setTargetPosition(560);
        } else if (loopRotations == 3) {
            rightMotor.setPower(.25);
            leftMotor.setPower(.25);
        }}}
////        }else if(loopRotations == 4) {
////            leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
////            rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
////        }else if(loopRotations == 5){
////            leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
////            rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
////        }else if(loopRotations == 6){
////            leftMotor.setTargetPosition(3 * 560);
////            rightMotor.setTargetPosition(-3*560);
////        }else if(loopRotations == 7){
////            leftMotor.setPower(.05);
////            rightMotor.setPower(.05);
////        }else if(loopRotations==8){
////            leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
////            rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//        }
//    }
////}
