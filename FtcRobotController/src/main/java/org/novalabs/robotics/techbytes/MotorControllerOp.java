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
public class MotorControllerOp extends OpMode {

    private String startDate;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorOne;
    private DcMotor motorTwo;
    private DcMotor motorThree;
    private DcMotor motorFour;


    public void init() {
        motorOne = hardwareMap.dcMotor.get("motor_1");
        motorTwo = hardwareMap.dcMotor.get("motor_2");
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

        double yl = gamepad1.left_stick_y;
        double xl = gamepad1.left_stick_x;
        double rm = (xl-1)/2;
        double lm = (xl+1)/2;
//        if (y < -1) {
//            y = -1;
//        }
//        if (y > 1) {
//            y = 1;
//        }

        telemetry.addData("1 Start", "NullOp started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());
        telemetry.addData("left-trigger", gamepad1.left_trigger);
        telemetry.addData("right-trigge", gamepad1.right_trigger);
        telemetry.addData("gamepad 2 right trigger", gamepad2.right_trigger);
        motorOne.setPower(yl*(rm));
        motorTwo.setPower(yl*(lm));

        //        motorThree.setPower(yr);
//        motorFour.setPower(yr);
        //motorThree.setPower(.5);
        //motorFour.setPower(.5);
        telemetry.addData("1 Start", "NullOp started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());
//        telemetry.addData("");
        if (gamepad1.y) {
            motorThree.setPower(.5);
            motorFour.setPower(.5);

        }else if (gamepad1.a) {
            motorThree.setPower(-.5);
            motorFour.setPower(-.5);

        } else {
            motorThree.setPower(0);
            motorFour.setPower(0);}




    }


}


