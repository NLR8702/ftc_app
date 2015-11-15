package org.ftcbootstrap.demos.beginner;

import com.qualcomm.ftcrobotcontroller.opmodes.NullOp;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

import org.ftcbootstrap.demos.TelemetryTest;
import org.ftcbootstrap.demos.beginner.opmodes.MyFirstBotOpMode1;
import org.ftcbootstrap.demos.beginner.opmodes.MyFirstBotOpMode2;
import org.ftcbootstrap.demos.beginner.opmodes.MyFirstBotOpMode3;
import org.ftcbootstrap.demos.beginner.opmodes.MyFirstBotOpMode4;
import org.ftcbootstrap.demos.beginner.opmodes.MyFirstBotOpMode5;
import org.ftcbootstrap.demos.beginner.opmodes.MyFirstBotOpMode6;


/**
 * Register Op Modes
 */
public class MyFirstBotRegistry implements OpModeRegister {

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

    manager.register("MyFirstBotOpMode1", MyFirstBotOpMode1.class);
    manager.register("MyFirstBotOpMode2", MyFirstBotOpMode2.class);
    manager.register("MyFirstBotOpMode3", MyFirstBotOpMode3.class);
    manager.register("MyFirstBotOpMode4", MyFirstBotOpMode4.class);
    manager.register("MyFirstBotOpMode5", MyFirstBotOpMode5.class);
    manager.register("MyFirstBotOpMode6", MyFirstBotOpMode6.class);

    manager.register("TelemetryTest", TelemetryTest.class);

    
  }
}
