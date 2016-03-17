package org.ftcTeam.opmodes.autonomous;

import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcTeam.opmodes.AutonomousMode;

/**
 * Created by tylerkim on 2/20/16.
 */
public class InsideBlue extends AutonomousMode {
    @Override
    protected void activeLoop() throws InterruptedException {

        sleep(5000);

        waitOneFullHardwareCycle();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        waitOneFullHardwareCycle();
        int target = 0;
        getTelemetryUtil().addData("status", "about to go forward 36 inchs");
        getTelemetryUtil().sendTelemetry();
        move(0.3, 15);

        waitOneFullHardwareCycle();
        int fiveFeetForward = robot.pulsesPerInch(-60);
        move(0.3, fiveFeetForward);

        waitOneFullHardwareCycle();
        int ninetyDegreeTurn = robot.pulsesPerDegree(90);
        spinRight(0.3, ninetyDegreeTurn);

        waitOneFullHardwareCycle();
        int pushDebrie = robot.pulsesPerInch(-60);
        move(0.3, pushDebrie);

        waitOneFullHardwareCycle();
        int Park = robot.pulsesPerInch(0);
        move(0.0, Park);

        this.setOperationsCompleted();
    }

}
