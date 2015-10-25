package org.novalabs.robotics.techbytes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TeleOp Mode
 * <p/>
 * Enables control of the robot via the gamepad
 */
public class PullUpBar_Op extends OpMode {

    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorOne;
    private DcMotor motorTwo;
    private DcMotor motorThree;
    private DcMotor motorFour;


    public void init() {

        motorOne = hardwareMap.dcMotor.get("motor_1");
        motorTwo =  hardwareMap.dcMotor.get("motor_2");
        motorThree = hardwareMap.dcMotor.get("motor_3");
        motorFour = hardwareMap.dcMotor.get("motor_4");
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


    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        telemetry.addData("1 Start", "NullOp started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());

            motorOne.setPower(gamepad1.left_trigger);
            motorTwo.setPower(gamepad1.left_trigger);
            motorThree.setPower(gamepad1.right_trigger);
            motorFour.setPower(gamepad1.right_trigger);
        telemetry.addData("leftTrigger", gamepad1.left_trigger);
    }


}


