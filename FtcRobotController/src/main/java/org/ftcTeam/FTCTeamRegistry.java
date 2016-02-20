package org.ftcTeam;

import com.qualcomm.ftcrobotcontroller.opmodes.NullOp;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

import org.ftcTeam.opmodes.AutonomousMode;
import org.ftcTeam.opmodes.EncoderMotorTest8702;
import org.ftcTeam.opmodes.GamePadDriveOpMode;
import org.ftcbootstrap.demos.TelemetryTest;
import org.ftcbootstrap.demos.navbot.opmodes.EncoderMotorTest;
import org.ftcbootstrap.demos.pushbot.opmodes.PushBotAuto;
import org.novalabs.robotics.techbytes.AutonomousBlue;
import org.novalabs.robotics.techbytes.FrameworkTestOpMode;
import org.novalabs.robotics.techbytes.UltrasonicTestOp;
import org.novalabs.robotics.techbytes.tests.AutonomousSleep;


/**
 * Register Op Modes
 */
public class FTCTeamRegistry implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */

    manager.register("NullOp", NullOp.class);

    manager.register("GamePadDriveOpMode", GamePadDriveOpMode.class);


    manager.register("TelemetryTest", TelemetryTest.class);

    manager.register("PushBotTest", PushBotAuto.class);

    manager.register("Ultrasonic Test1", UltrasonicTestOp.class);

    manager.register("FrameworkTestOpMode", FrameworkTestOpMode.class);
    manager.register("Navigation", org.novalabs.robotics.techbytes.tests.NavigationColorSensor.class);
    manager.register("AutonomousBlue", AutonomousBlue.class);
    manager.register("AutonomousTest", AutonomousSleep.class);
    manager.register("UltrasonicTest", UltrasonicTestOp.class);
    manager.register("EncoderTest", EncoderMotorTest8702.class);
    manager.register("EncoderMotor8702", EncoderMotorTest8702.class);
    manager.register("AutonomusMode", AutonomousMode.class);

    //manager.register("MR Gyro Test", MRGyroTest.class);

    //manager.register("AdafƒruitRGBExample", AdafruitRGBExample.class);
    //manager.register("ColorSensorDriver", ColorSensorDriver.class);

    //manager.register("IrSeekerOp", IrSeekerOp.class);
    //manager.register("CompassCalibration", CompassCalibration.class);
    //manager.register("I2cAddressChangeExample", LinearI2cAddressChange.class);


    //manager.register("NxtTeleOp", NxtTeleOp.class);

    //manager.register("LinearK9TeleOp", LinearK9TeleOp.class);
    //manager.register("LinearIrExample", LinearIrExample.class);


    //manager.register ("PushBotManual1", PushBotManual1.class);
    //manager.register ("PushBotAutoSensors", PushBotAutoSensors.class);
    //manager.register ("PushBotIrEvent", PushBotIrEvent.class);

    //manager.register ("PushBotManualSensors", PushBotManualSensors.class);
    //manager.register ("PushBotOdsDetectEvent", PushBotOdsDetectEvent.class);
    //manager.register ("PushBotOdsFollowEvent", PushBotOdsFollowEvent.class);
    //manager.register ("PushBotTouchEvent", PushBotTouchEvent.class);

    //manager.register("PushBotDriveTouch", PushBotDriveTouch.java);
    //manager.register("PushBotIrSeek", PushBotIrSeek.java);
    //manager.register("PushBotSquare", PushBotSquare.java);
  }
}
