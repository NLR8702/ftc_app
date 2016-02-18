package org.novalabs.robotics.techbytes.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.novalabs.robotics.techbytes.AutonomousUtility;

/**
 * Created by Zach Shuster on 12/28/2015.
 * Tested with some success on 12/30/2015
 */
public class AutonomousSleep extends LinearOpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
//     private List<HardwareController> controllerList = new ArrayList<HardwareController>();
//  List<HardwareController> failedControllerList = new ArrayList();
    private int Sleep_1 = 500;
    private int right_fortyfive_sleep = 500;
    private int Sleep_3 = 2000;
    private int Sleep_4 = 750;



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

        TestUtility.Straight(leftMotor, rightMotor);
        intermediate_halt();
        TestUtility.RightTurn(leftMotor, rightMotor);
        intermediate_halt();


    }


































//    private DcMotor leftMotor;
//    private DcMotor rightMotor;
//    private int loopRotations = 0;
//    private static int CurrentStage = 0;
//
//    public AutonomousBlue() {
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























