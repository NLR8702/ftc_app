package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by tylerkim on 12/5/15.

 */
public class KickstandHardwareController implements HardwareController {
    private DcMotor KickstandMotor;

    @Override
    public void init(OpMode opMode) {
        KickstandMotor = opMode.hardwareMap.dcMotor.get("KickstandMotor");

    }

    @Override
    public void loop(OpMode opMode) {
        if(opMode.gamepad1.left_bumper) {
            KickstandMotor.setPower(.25);
        }
        else KickstandMotor.setPower(0);

    }
}
