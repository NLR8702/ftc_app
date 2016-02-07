package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tylerkim on 12/1/15.
 * Ha! no longer default file template now!
 */
public class FrameworkTestOpMode extends OpMode {
    private List<HardwareController> controllerList = new ArrayList<HardwareController>();
    List<HardwareController> failedControllerList = new ArrayList();


    public FrameworkTestOpMode(){
        controllerList.add(new DriveHardwareController());
        controllerList.add(new TapeHardwareController());
        controllerList.add(new DumperHardwareController());
        controllerList.add(new ZiplineMechanismHardwareController());


    }

    @Override
    public void init() {
        for(HardwareController aController : controllerList) {
           try {
               aController.init(this);
           } catch(Exception E){
               telemetry.addData("Init Error: ", aController.getClass().getSimpleName());
               telemetry.addData("Init Error: ",E.getStackTrace());
               failedControllerList.add(aController);
           }
        }
    }

    @Override
       public void loop() {
        for(HardwareController aController : controllerList) {
            try {
                aController.loop(this);
            } catch(Exception E){
                telemetry.addData("Loop Error: ", aController.getClass().getSimpleName());

            }
        }
        for(HardwareController aController : failedControllerList) {
            telemetry.addData("Errors: ", aController.getClass().getSimpleName());
            //telemetry.addData("Errors: ", aController.getClass().getSimpleName());

        }
    }
}

