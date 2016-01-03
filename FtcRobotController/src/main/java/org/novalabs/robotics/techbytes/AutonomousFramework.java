package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tylerkim on 12/1/15.
 */
public class AutonomousFramework extends OpMode {
    private List<HardwareController> controllerList = new ArrayList<HardwareController>();
    public AutonomousFramework(){
//        controllerList.add(new KickstandHardwareController());
        controllerList.add(new NavigationColorSensor());
        controllerList.add(new BeaconDetector());
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

