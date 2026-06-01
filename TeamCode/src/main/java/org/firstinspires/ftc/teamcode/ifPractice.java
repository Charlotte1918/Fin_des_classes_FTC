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
        boolean aButton = gamepad1.a;
        double leftY = gamepad1.left_stick_y;

        if (aButton) {
            telemetry.addData("A Button", "Pressed !");
        } else {
            telemetry.addData("A Button", "NOT pressed !");
        }

        telemetry.addData("A Button State", aButton);

        if (leftY < 0) {
            telemetry.addData("leftString", "is Negative");
        } else {
            telemetry.addData("leftStick", "is Positive !");
        }
        telemetry.addData("leftStickValue", leftY);

    }
}
