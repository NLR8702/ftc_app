package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tylerkim on 12/1/15.
 */
public class FrameworkTestOpMode extends OpMode {
    private List<HardwareController> controllerList = new ArrayList<HardwareController>();
    public FrameworkTestOpMode(){
        DriveHardwareController drive = new DriveHardwareController();
        controllerList.add(new TapeHardwareController(drive));
        controllerList.add(drive);
        controllerList.add(new DumperHardwareController());
        controllerList.add(new ZiplineMechanismHardwareController());


    }

    @Override
    public void init() {
        for(HardwareController aController : controllerList) {
           try {
               aController.init(this);
           } catch(Exception E){
               telemetry.addData("Cannot Find Device: ", aController.toString());
               aController = null;
           }
        }

    }

    @Override
    public void loop() {
        for(HardwareController aController : controllerList) {
            if(aController != null){
                aController.loop(this);

            }
        }

    }
}

