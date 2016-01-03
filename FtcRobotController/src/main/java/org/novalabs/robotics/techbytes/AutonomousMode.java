package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach Shuster on 12/28/2015.
 * Tested with some success on 12/30/2015
 */
public class AutonomousMode extends OpMode {
//    private List<HardwareController> controllerList = new ArrayList<HardwareController>();
//    List<HardwareController> failedControllerList = new ArrayList();
    private DcMotor leftMotor;
    private DcMotor rightMotor;
   // private int loopRotations = 0;
    private static int Stage = 0;

    public AutonomousMode(){
//        controllerList.add(new KickstandHardwareController());
//        controllerList.add(new NavigationColorSensor());
//        controllerList.add(new BeaconDetector());
    }
    @Override
    public void init() {
//        for(HardwareController aController : controllerList) {
//            try {
//                aController.init(this);
//            } catch(Exception E){
//                failedControllerList.add(aController);
//            }
//        }

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }


    @Override
    public void loop() {
//        for(HardwareController aController : controllerList) {
//            try {
//                aController.loop(this);
//            } catch(Exception E){
//                telemetry.addData("Loop Error: ", aController.getClass().getSimpleName());
//            }
//        }
//        for(HardwareController aController : failedControllerList) {
//            telemetry.addData("Errors: ", aController.getClass().getSimpleName());
//        }


//        loopRotations = loopRotations + 1;
//        if (loopRotations == 1) {
//            leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//            rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//        } else if (loopRotations == 2) {
//            leftMotor.setTargetPosition(560);
//            rightMotor.setTargetPosition(560);
//        } else if (loopRotations == 3) {
//            rightMotor.setPower(.25);
//            leftMotor.setPower(.25);
//        }}
        Stage = Stage + 1;
        if (Stage == 1) {
            leftMotor.setPower(.25);
            rightMotor.setPower(.25);
            Stage = Stage + 1;
    } else if (Stage == 2) {
            leftMotor.setPower(.25);
            rightMotor.setPower(0);
            Stage = Stage + 1;
        } else if (Stage == 3) {
            leftMotor.setPower(.5);
            rightMotor.setPower(.5);
            Stage = Stage + 1;
        } else if (Stage == 4); {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
    }
}

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
