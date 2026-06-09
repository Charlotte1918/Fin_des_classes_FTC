package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Class Bot Java")
public class ClassBot extends OpMode {

    // Indique comment les Moteurs De La Base seront nommés dans le programme
    DcMotor gauche;
    DcMotor droit;

    // Indique comment le Moteur Du Bras seront nommés dans le programme
    DcMotor bras;

    // Servo de la pince
    private Servo pince;

    // Touch Sensor
    TouchSensor touchSensor;

    @Override
    public void init() {

        // Indique quels DcMotors de la config seront ceux de la base
        gauche = hardwareMap.get(DcMotor.class, "gauche");
        droit = hardwareMap.get(DcMotor.class, "droit");

        // La direction d'un des moteurs de la base est inversée
        gauche.setDirection(DcMotor.Direction.REVERSE);

        // Les moteurs se fieront à leurs encoders
        gauche.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        droit.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Indique quel DcMotor de la config sera celui du bras
        bras = hardwareMap.get(DcMotor.class, "bras");

        // Indique quel Servo de la config sera celui de la pince
        pince = hardwareMap.get(Servo.class, "pince");
        pince.setPosition(0);

        // Indique quel TouchSensor de la config sera celui du bras
        touchSensor = hardwareMap.get(TouchSensor.class, "touch sensor");

    }

    @Override
    public void loop() {
        // Mouvements de la base

        // Le robot avance selon le y du joystick gauche
        double forward = gamepad1.left_stick_y;
        // Le robot tourne selon le x du joystick droit
        double rotate = gamepad1.right_stick_x;

        // On définit quel sera la puissance appliqué à chaque moteur
        double gauchePower = forward + rotate;
        double droitPower = forward - rotate;

        // Les moteurs reçoivent la puissance calculée ci-dessus
        gauche.setPower(gauchePower);
        droit.setPower(droitPower);

        // Mouvements du bras
        if (gamepad1.dpad_up) {
            // Si dpad_up est pressé, le bras monte
            bras.setPower(0.5);
        } else if (gamepad1.dpad_down && !touchSensor.isPressed()) {
            // Sinon et si dpad_down est pressé et que le touch sensor n'est pas appuyé, le bras descent
            bras.setPower(-0.5);
        }

        // Mouvements de la pince
        if (gamepad1.a) {
            // Si A est pressé, la pince ouvre
            pince.setPosition(1);
        } else if (gamepad1.b) {
            // Sinon et si B est pressé, la pince ferme
            pince.setPosition(0);
        }

        // Télémétrie : on envoie des informations du programme pour que nous puissons les voir sur le driver hub
        telemetry.addData("Touch Sensor", touchSensor);
        // On update la télémetrie pour pouvoir les nouvelles informations à chaque cycle
        telemetry.update();

    }
}
