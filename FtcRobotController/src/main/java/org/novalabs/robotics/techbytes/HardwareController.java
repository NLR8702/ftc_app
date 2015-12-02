package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by tylerkim on 12/1/15.
 */
public interface HardwareController {
    public void init(OpMode opMode);
    public void loop(OpMode opMode);
}
