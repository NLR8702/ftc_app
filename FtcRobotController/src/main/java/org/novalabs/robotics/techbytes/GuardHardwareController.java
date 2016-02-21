package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Zach Shuster on 2/20/2016.
 */
public class GuardHardwareController implements HardwareController {
    private DcMotor guardMotor;
    private int stage=0;
    private int time=0;

    public void init(OpMode opMode) {
        guardMotor = opMode.hardwareMap.dcMotor.get("guardMotor");
        int stage=0;
        guardMotor.setPower(0);
        int time=0;
    }

    @Override
    public void loop(OpMode opMode) {

        opMode.telemetry.addData("time",time);
        opMode.telemetry.addData("stage",stage);
        opMode.telemetry.addData("currentPos",guardMotor.getCurrentPosition());

        if (opMode.gamepad2.a){
            guardMotor.setPower(.25);
        }else if(opMode.gamepad2.y){
            guardMotor.setPower(-.25);
        }else{
            guardMotor.setPower(0);
        }



         }
}

