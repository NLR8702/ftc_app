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



    public void init() {

        motorOne = hardwareMap.dcMotor.get("motor_1");
        motorTwo = hardwareMap.dcMotor.get("motor_2");
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
    public void loop()

    {
        telemetry.addData("1 Start", "NullOp started at " + startDate);
        telemetry.addData("2 Status", "running for " + runtime.toString());

//        telemetry.addData("");
        if (gamepad1.y) {
            motorOne.setPower(-.25);
            motorTwo.setPower(.25);

        }else if (gamepad1.a){
            motorOne.setPower(.25);
            motorTwo.setPower(-.25);

        } else {
            motorOne.setPower(0);
            motorTwo.setPower(0);}






    }


}


