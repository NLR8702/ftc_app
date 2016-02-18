package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ftcTeam.Team8702Bot;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.TankDriveToEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zach Shuster on 12/28/2015.
 * Tested with some success on 12/30/2015
 */
public class AutonomousBlue extends ActiveOpMode {
    private Team8702Bot robot;
    private TankDriveToEncoder tankDriveToEncoder;

    @Override
    protected void onInit() {
        //specify configuration name save from scan operation
        robot = Team8702Bot.newConfig(hardwareMap, getTelemetryUtil());

        tankDriveToEncoder = new TankDriveToEncoder(this, robot.getRightDrive(), robot.getLeftDrive());
        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RESET_ENCODERS);
        robot.getRightDrive().setDirection(DcMotor.Direction.REVERSE);

    }
    @Override
    protected void onStart() throws InterruptedException  {
        super.onStart();


        //telemetry rows written for everthing added ( not grouped by key)
        getTelemetryUtil().setSortByTime(true);
    }

    @Override
    protected void activeLoop() throws InterruptedException {

        waitOneFullHardwareCycle();
        robot.getLeftDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        robot.getRightDrive().setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        AutonomousUtility.Forwards(this, robot, 12);
        AutonomousUtility.turnLeft(this, robot, 90);
    }
}


































