package org.ftcTeam.opmodes.autonomous;

import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcTeam.opmodes.AutonomousMode;

/**
 * Created by tylerkim on 2/20/16.
 */
public class OneBlueBlue extends AutonomousMode {
    @Override
    protected void activeLoop() throws InterruptedException {



        waitOneFullHardwareCycle();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        waitOneFullHardwareCycle();
        int target = 0;
        getTelemetryUtil().addData("status", "about to go forward 36 inchs");
        getTelemetryUtil().sendTelemetry();

        target = Inches_to_Pulse(12);
        move(0.3, target);

        getTelemetryUtil().addData("currentPos", robot.getLeftDrive().getCurrentPosition());
        getTelemetryUtil().sendTelemetry();


        waitOneFullHardwareCycle();

        target = Degrees_to_Pulse(45);
        spinLeft(0.3, target);

        //target = target + Inches_to_Pulse(12);
        // target = target + Inches_to_Pulse(12);
        waitOneFullHardwareCycle();
        target = Inches_to_Pulse(36);
        move(0.3, target);

        waitOneFullHardwareCycle();
        target = Inches_to_Pulse(12);
        move(-0.3, -target);


        waitOneFullHardwareCycle();
        target = Degrees_to_Pulse(90);
        spinRight(0.3, target);



        this.setOperationsCompleted();
    }

}
