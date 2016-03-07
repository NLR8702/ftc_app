package org.ftcTeam.opmodes.autonomous;

import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcTeam.opmodes.AutonomousMode;

/**
 * Created by tylerkim on 2/20/16.
 */
public class TwoBlueRed extends AutonomousMode {
    @Override
    protected void activeLoop() throws InterruptedException {

        robot.getGuardMotor().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getGuardMotor().setTargetPosition(1000);
        robot.getGuardMotor().setPower(0.25);
        sleep(1000);
        waitOneFullHardwareCycle();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        waitOneFullHardwareCycle();
        int target = 0;
        move(0.3, robot.pulsesPerInch(15));

//        waitOneFullHardwareCycle();
//        spinLeft(0.3, Degrees_to_Pulse(45));git
//
//        waitOneFullHardwareCycle();
//        move(0.3, Inches_to_Pulse(45));
//
//        waitOneFullHardwareCycle();
//        spinRight(0.3, Degrees_to_Pulse(225));
//
//        waitOneFullHardwareCycle();
//        spinLeft(0.3, Degrees_to_Pulse(90));
//
//        waitOneFullHardwareCycle();
//        move(-.3, -Inches_to_Pulse(12));

        this.setOperationsCompleted();
    }

}
