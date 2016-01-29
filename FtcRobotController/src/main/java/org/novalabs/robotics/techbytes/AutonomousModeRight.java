package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach Shuster on 12/28/2015.
 * Tested with some success on 12/30/2015
 */
public class AutonomousModeRight extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
//     private List<HardwareController> controllerList = new ArrayList<HardwareController>();
//  List<HardwareController> failedControllerList = new ArrayList();
    private int Sleep_1 = 500;
    private int Sleep_2 = 500;
    private int Sleep_3 = 2000;
    private int Sleep_4 = 2000;
    private int Sleep_5 = 750;
    private int Sleep_6 = 3000;
    private void intermediate_halt() throws InterruptedException {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        sleep(500);
    }
    private float powerlevel = .5f;
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        waitForStart();
        // go straight
        leftMotor.setPower(.25);
        rightMotor.setPower(.25);
        sleep(Sleep_1);
        intermediate_halt();
        // right turn forty five degrees
        leftMotor.setPower(.0);
        rightMotor.setPower(.25);
        sleep(Sleep_2);
        intermediate_halt();
        // straight across the field
        leftMotor.setPower(.25);
        rightMotor.setPower(.25);
        sleep(Sleep_3);
        intermediate_halt();
        // backup
        leftMotor.setPower(-.25);
        rightMotor.setPower(-.25);
        sleep(Sleep_4);
        intermediate_halt();
        // right turn 90o
        leftMotor.setPower(0);
        rightMotor.setPower(.25);
        sleep(Sleep_5);
        intermediate_halt();
        // go up the mountain
        leftMotor.setPower(.25);
        rightMotor.setPower(.25);
        sleep(Sleep_6);
        intermediate_halt();
        // STTOOOPPPP!!!
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //stop
//        //go back a little
//        leftMotor.setPower(-.5);
//        rightMotor.setPower(-.5);
//        sleep(2000);
//        //turn right
//        leftMotor.setPower(.5);
//        rightMotor.setPower(-.5);
//        sleep(2500);
//        //go up mounta
//        leftMotor.setPower(.85);
//        rightMotor.setPower(.85);
//        sleep(2700);
    }


































//    private DcMotor leftMotor;
//    private DcMotor rightMotor;
//    private int loopRotations = 0;
//    private static int CurrentStage = 0;
//
//    public AutonomousModeRight() {
//        controllerList.add(new KickstandHardwareController());
//        controllerList.add(new NavigationColorSensor());
//        controllerList.add(new BeaconDetector());
//        controllerList.add(new GyroSensorAuto());
//}
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        for(HardwareController aController : controllerList) {
//            try {
//                aController.init(this);
//            } catch(Exception E){
//                failedControllerList.add(aController);
//            }
//        }
//        CurrentStage = 0;
//        leftMotor = hardwareMap.dcMotor.get("leftMotor");
//        rightMotor = hardwareMap.dcMotor.get("rightMotor");
//        leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//        rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//    }
//
//
//
//
//
//    @Override
//    public void loop() {
//        telemetry.addData("current stage: ", CurrentStage);
//
//        /////// Stage = Stage + 1;
//        if (CurrentStage == 0);{
//            leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//            leftMotor.setTargetPosition(10);
//            leftMotor.setPower(.25);
//            //rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
////            CurrentStage = CurrentStage + 1;
//
////        }else if (CurrentStage == 1) {
////            rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
////            rightMotor.setTargetPosition(560);
////            rightMotor.setPower(.25);
////            CurrentStage = CurrentStage + 1;
////        } else if (CurrentStage == 2) {
//
//
//            CurrentStage = CurrentStage + 1;
//        }
//
////        for(HardwareController aController : controllerList) {
////            try {
////                aController.loop(this);
////            } catch(Exception E){
////                telemetry.addData("Loop Error: ", aController.getClass().getSimpleName());
////            }
////        }
////        for(HardwareController aController : failedControllerList) {
////            telemetry.addData("Errors: ", aController.getClass().getSimpleName());
////        }
//
//
////        loopRotations = loopRotations + 1;
////        if (loopRotations == 1) {
////            leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
////            rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
////        } else if (loopRotations == 2) {
////            leftMotor.setTargetPosition(560);
////            rightMotor.setTargetPosition(560);
////        } else if (loopRotations == 3) {
////            rightMotor.setPower(.25);
////            leftMotor.setPower(.25);
////        }}
//
//
//
////        } else if (Stage == ) {
////            leftMotor.setPower(.25);
////            rightMotor.setPower(0);
//////            Stage = Stage + 1;
////        } else if (Stage == ) {
////            leftMotor.setPower(.5);
////            rightMotor.setPower(.5);
//////            Stage = Stage + 1;
////        } else if (Stage == ); {
////            leftMotor.setPower(0);
////            rightMotor.setPower(0);
////        }
////    }
////}
//
//////        }else if(loopRotations == 4) {
//////            leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//////            rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//////        }else if(loopRotations == 5){
//////            leftMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//////            rightMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
//////        }else if(loopRotations == 6){
//////            leftMotor.setTargetPosition(3 * 560);
//////            rightMotor.setTargetPosition(-3*560);
//////        }else if(loopRotations == 7){
//////            leftMotor.setPower(.05);
//////            rightMotor.setPower(.05);
//////        }else if(loopRotations==8){
//////            leftMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
//////            rightMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
////        }
////    }
//////}
//    }
}



















/*
goals today:
- work on autonomous
  1. Zach: left
           using encoders

  2. Tyler: right
            using "sleep" program

 */























