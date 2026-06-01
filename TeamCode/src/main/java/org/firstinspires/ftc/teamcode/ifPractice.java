package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ifPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        double leftY = gamepad1.left_stick_y;


        if (leftY < 0.1 && leftY > -0.1) {
            telemetry.addData("leftStick", "is In Dead Zone");
        }

        if (!gamepad1.a) {
            leftY *= 0.5;
        }

        telemetry.addData("leftStickValue", leftY);

    }
}
