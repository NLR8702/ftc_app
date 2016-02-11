package org.ftcTeam;

import com.qualcomm.ftcrobotcontroller.opmodes.NullOp;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

import org.ftcTeam.opmodes.GamePadDriveOpMode;
import org.ftcbootstrap.demos.TelemetryTest;
import org.ftcbootstrap.demos.pushbot.opmodes.PushBotAuto;
import org.novalabs.robotics.techbytes.UltrasonicTestOp;


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

    manager.register("Ultrasonic Test", UltrasonicTestOp.class);

    
  }
}
