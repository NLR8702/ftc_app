package org.ftcTeam.opmodes.autonomous;

import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcTeam.opmodes.AutonomousMode;

/**
 * Created by tylerkim on 2/20/16.
 */
public class AutonomousTest extends AutonomousMode {
    @Override
    protected void activeLoop() throws InterruptedException {

        sleep(5000);

        waitOneFullHardwareCycle();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        waitOneFullHardwareCycle();
        int target = robot.pulsesPerInch(24);
        getTelemetryUtil().addData("status", "about to go forward 36 inchs");
        getTelemetryUtil().sendTelemetry();
        move(0.3, target);



        this.setOperationsCompleted();
    }

}
