package org.ftcTeam;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.ftcbootstrap.RobotConfiguration;
import org.ftcbootstrap.components.utils.TelemetryUtil;

/**
 * FTCTeamRobot Saved Configuration
 * <p/>
 * It is assumed that there is a configuration on the phone running the Robot Controller App with the same name as this class and
 * that  configuration is the one that is marked 'activated' on the phone.
 * It is also assumed that the device names in the 'init()' method below are the same  as the devices named for the
 * saved configuration on the phone.
 */
public class Team8702Bot extends RobotConfiguration {
    static final double ANDYMARK60 = 60;
    static final double ANDYMARK40 = 40;
    static final double ROBOT_MOTOR = ANDYMARK60;
    static final double PULSES_PER_REVOLUTION = 28 * ROBOT_MOTOR;
    static final double INCHES_PER_REVOLUTION = 18.625;


    //sensors
//    private TouchSensor touch;

    //motors
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor guardMotor;

    /**
     * Factory method for this class
     *
     * @//    private TouchSensor touch;
param hardwareMap
     * @param telemetryUtil
     * @return
     */
    public static Team8702Bot newConfig(HardwareMap hardwareMap, TelemetryUtil telemetryUtil) {

        Team8702Bot config = new Team8702Bot();
        config.init(hardwareMap, telemetryUtil);
        return config;

    }
    public int pulsesPerInch(double inches) {
        return (int) (inches * PULSES_PER_REVOLUTION/INCHES_PER_REVOLUTION);
    }

    /**
     * Assign your class instance variables to the saved device names in the hardware map
     *
     * @param hardwareMap
     * @param telemetryUtil
     */
    @Override
    protected void init(HardwareMap hardwareMap, TelemetryUtil telemetryUtil) {

        setTelemetry(telemetryUtil);

        leftMotor = (DcMotor) getHardwareOn("leftMotor", hardwareMap.dcMotor);
        rightMotor = (DcMotor) getHardwareOn("rightMotor", hardwareMap.dcMotor);
        guardMotor=(DcMotor) getHardwareOn("guardMotor", hardwareMap.dcMotor);
//        rightMotor.setDirection(DcMotor.Direction.REVERSE);


    }





    /**
     * @return DcMotor
     */
    public DcMotor getLeftDrive() {
        return leftMotor;
    }

    /**
     * @return DcMotor
     */
    public DcMotor getRightDrive() {
        return rightMotor;
    }


    public DcMotor getGuardMotor() {
        return guardMotor;
    }
}
