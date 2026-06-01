package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class GamePadTest extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        double speedForward = -gamepad1.left_stick_y / 2.0;
        double difX = gamepad1.left_stick_x - gamepad1.left_stick_y;
        double sumTriggers = gamepad1.right_trigger + gamepad1.left_trigger;

        telemetry.addData("left_x", gamepad1.left_stick_x);
        telemetry.addData("left_y", speedForward);
        telemetry.addData("right_x", gamepad1.right_stick_x);
        telemetry.addData("right_y", gamepad1.right_stick_y);
        telemetry.addData("a button", gamepad1.a);
        telemetry.addData("b button", gamepad1.b);
        telemetry.addData("dif_x", difX);
        telemetry.addData("sum_triggers", sumTriggers);

    }
}
