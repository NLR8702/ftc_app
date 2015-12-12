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
    public FrameworkTestOpMode(){
        DriveHardwareController drive = new DriveHardwareController();
        controllerList.add(new TapeHardwareController(drive));
        controllerList.add(drive);
        controllerList.add(new DumperHardwareController());
       // controllerList.add(new KickstandHardwareController());

    }

    @Override
    public void init() {
        for(HardwareController aController : controllerList) {
            aController.init(this);
        }

    }

    @Override
    public void loop() {
        for(HardwareController aController : controllerList) {
            aController.loop(this);
        }

    }
}

