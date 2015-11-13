package org.novalabs.robotics.techbytes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Andrew Bolster on 10/25/2015.
 */


public class AutoControlOp extends AutoControlTelemetry {




    enum STATE {
        START,
        READY,
        RUNNING_TO_ENCODER,
        STOP
    }

    private DcMotor v_motor_left_drive;

    private DcMotor v_motor_right_drive;

    private STATE currentState = STATE.START;

    private UltrasonicSensor ultrasonicSensor;

    int encoderValue = v_motor_left_drive.getCurrentPosition();

    @Override
    public void start() {
        super.start();
        //reset_drive_encoders();
    }

    @Override
    public void init() {
        super.init();
        currentState = STATE.START;
        //ultrasonicSensor = hardwareMap.ultrasonicSensor.get("Ult_1");
    }

    @Override
    public void loop() {
        telemetry.addData("EncoderValue", "encoder value: " + encoderValue);
        telemetry.addData("State", "State" + currentState);
        if (currentState == STATE.START) {
            // Reset driver encoders
            reset_drive_encoders();
            currentState = STATE.READY;
        } else if (currentState == STATE.READY) {

            run_using_encoders();
            set_drive_power(0.5, 0.5);
            currentState = STATE.RUNNING_TO_ENCODER;

        } else if (currentState == STATE.RUNNING_TO_ENCODER) {
            if (have_drive_encoders_reached(2880, 2880)) {


                reset_drive_encoders();

                set_drive_power(0.0f, 0.0f);

                currentState = STATE.STOP;
            }
        } else if (currentState == STATE.STOP) {

        }
    }


//        double ultrasonic = ultrasonicSensor.getUltrasonicLevel() / 2.78;
//        telemetry.addData("", "Inches: " + ultrasonic);
}

