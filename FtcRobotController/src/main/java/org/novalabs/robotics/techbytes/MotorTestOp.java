package org.novalabs.robotics.techbytes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p/>
 * Enables control of the robot via the gamepad
 * This is a comment from Tyler in order to commit something!
 */
// test
public class MotorTestOp extends OpMode {
    private long startTime = -1;
    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorOne;
    private DcMotorController controler;

    @Override
    public void init() {
        motorOne = hardwareMap.dcMotor.get("motor_1");
        motorOne.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        controler = hardwareMap.dcMotorController.get("motor_controller_1");
        controler.setMotorChannelMode(1, DcMotorController.RunMode.RUN_USING_ENCODERS);

    }

    /*
       * Code to run when the op mode is first enabled goes here
       * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
       */
    @Override
    public void init_loop() {
        startDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        runtime.reset();
        telemetry.addData("Null Op Init Loop", runtime.toString());
        startTime = -1;
        motorOne.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorOne.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        int encoderValue = motorOne.getCurrentPosition();
        telemetry.addData("1 Start", "NullOp started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());
        if (startTime == -1) {
            startTime = System.currentTimeMillis();
            //motorOne.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            motorOne.setPower(.1);
        }
//    if(System.currentTimeMillis ()>startTime+3500) {
//      motorOne.setPower(0);
//      motorOne.getCurrentPosition();
//    }
        telemetry.addData("EncoderValue", "encoder value: " + encoderValue);
        int currentPosition = motorOne.getCurrentPosition();
        telemetry.addData("currentPosition", "Current Position: " + currentPosition);

    }
}

