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
        controllerList.add(new TapeHardwareController());
        controllerList.add(new DriveHardwareController());
        controllerList.add(new DumperHardwareController());
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

