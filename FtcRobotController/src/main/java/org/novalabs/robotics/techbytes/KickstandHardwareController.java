package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by tylerkim on 12/5/15.

 */
public class KickstandHardwareController implements HardwareController {
    private DcMotor KickstandMotor;
    private int loopRotations=0;

    @Override
    public void init(OpMode opMode) {
        KickstandMotor = opMode.hardwareMap.dcMotor.get("KickstandMotor");
        KickstandMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    }
    @Override
    public void loop(OpMode opMode) {
        opMode.telemetry.addData("loopRotations: ", loopRotations);
        loopRotations = loopRotations + 1;
        if (loopRotations == 1) {
            KickstandMotor.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        } else if (loopRotations == 2) {
            KickstandMotor.setTargetPosition(6 * 560);
        } else if (loopRotations == 3) {
            KickstandMotor.setPower(.25);


        }
    }
}
