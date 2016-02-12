package org.novalabs.robotics.techbytes;

import com.qualcomm.ftcrobotcontroller.FtcRobotControllerActivity;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.ftcTeam.Team8702Bot;
import org.ftcbootstrap.ActiveOpMode;
import org.ftcbootstrap.components.operations.motors.GamePadTankDrive;
import org.ftcbootstrap.components.phone.AccelerometerComponent;
import org.ftcbootstrap.demos.demobot.DemoBot;

/**
 * Created by Andrew Bolster on 10/25/2015.
 */
public class UltrasonicTestOp extends ActiveOpMode {

    private UltrasonicSensor ultrasonicSensor1;
    private UltrasonicSensor ultrasonicSensor2;

    private int Counter=0;
    private Team8702Bot robot;
    private double ultrasonic1;
    private double ultrasonic2;
    private GamePadTankDrive tankDrive;
    private AccelerometerComponent accelerometer;

    protected void onInit() {

        robot = Team8702Bot.newConfig(hardwareMap, getTelemetryUtil());


        getTelemetryUtil().addData("Init", getClass().getSimpleName() + " initialized.");
        getTelemetryUtil().sendTelemetry();

    }

    @Override
    public void onStart() throws InterruptedException {
        super.onStart();
        ultrasonicSensor1 = hardwareMap.ultrasonicSensor.get("ultrasonic1");
        ultrasonicSensor2 = hardwareMap.ultrasonicSensor.get("ultrasonic2");
        tankDrive = new GamePadTankDrive(this,gamepad1, robot.getLeftDrive(), robot.getRightDrive());

        FtcRobotControllerActivity activity = (FtcRobotControllerActivity) hardwareMap.appContext;
        accelerometer =
                activity.getAccelerometerComponent();

        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();
    }

    @Override
    public void activeLoop() {

        tankDrive.update();
       // double ultrasonic1 = ultrasonicSensor1.getUltrasonicLevel();
       String ultrasonicname = ultrasonicSensor1.getDeviceName();
        String ultrasoicsenses = ultrasonicSensor1.getConnectionInfo();
        ultrasonic1 = ultrasonicSensor1.getUltrasonicLevel();
        ultrasonic2 = ultrasonicSensor2.getUltrasonicLevel();
        float x = accelerometer.getX();
        float y = accelerometer.getY();
        float z = accelerometer.getZ();


        getTelemetryUtil().addData("Counter"    ,"Counter: " + Counter++);
        getTelemetryUtil().addData("Sonic Value1", "(In Centimeters)" + ultrasonic1);
        getTelemetryUtil().addData("Sonic Value2", "(In Centimeters)" + ultrasonic2);
        getTelemetryUtil().addData("inf: ", ultrasonicname);
        getTelemetryUtil().addData("Connection: ", ultrasoicsenses);
        getTelemetryUtil().addData("X value: ", x);
        getTelemetryUtil().addData("Y value: ", y);
        getTelemetryUtil().addData("Z value: ", z);


        //send any telemetry that may have been added in the above operations
        getTelemetryUtil().sendTelemetry();
        // telemetry.addData("Inches"     , "Inches: " + ultrasonic1 / 2.78);

    }

}
