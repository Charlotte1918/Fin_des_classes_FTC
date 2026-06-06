/* Copyright (c) 2025 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Il reste : refiner le prog. et vérifier sur ordi

@TeleOp(name = "Robot: Class Bot")
public class TeleopClassBot extends OpMode {
    // Moteurs de la base
    DcMotor gauche;
    DcMotor droit;

    // Moteur du bras
    DcMotor bras;

    // Servo de la pince
    private Servo pince;

    // Touch Sensor
    TouchSensor touchSensor;


    @Override
    public void init(HardwareMap hardwareMap) {

        // INIT la base
        gauche = hardwareMap.get(DcMotor.class, "gauche");
        droit = hardwareMap.get(DcMotor.class, "droit");

        gauche.setDirection(DcMotor.Direction.REVERSE);

        gauche.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        droit.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // INIT le bras
        bras = hardwareMap.get(DcMotor.class, "servo");

        // INIT la pince
        pince = hardwareMap.get(Servo.class, "pince");
        pince.setPosition(0)

        // INIT le touch sensor
        touchSensor = hardwareMap.get(TouchSensor.class, "touch sensor");

    }

    @Override
    public void loop() {

        // Mouvements de la base
        double forward = gamepad1.left_stick_y;
        double rotate = gamepad1.right_stick_x;

        double gauchePower = forward + rotate;
        double droitPower = forward - rotate;
        
        gauche.setPower(gauchePower);
        droit.setPower(droitPower);
        
        // Mouvements du bras
        if (gamepad1.dpad_up) { 
        bras.setPower(0.5);
        } else if (gamepad1.dpad_down && !touchSensor.isPressed()) {
        bras.setPower(-0.5);
        }

        // Mouvements de la pince
        if (gamepad1.a) {
        pince.setPosition(1);
        } else if (gamepad1.b) {
        pince.setPosition(0);
        }

        // Télémétrie
        telemetry.addData("Touch Sensor", touchSensor);
        telemetry.update()

    }
}
