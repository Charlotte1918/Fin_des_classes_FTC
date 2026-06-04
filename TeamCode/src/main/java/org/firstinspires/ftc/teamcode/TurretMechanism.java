package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.openftc.apriltag.AprilTagDetection;

public class TurretMechanism {

    private DcMotorEx turret;

    private double kP = 0.0001;
    private double kD = 0.0000;
    private double goalX = 0;
    private double lastError = 0;
    private double angleTolerance = 0.2;
    private final double MAX_POWER = 0.6;
    private double power = 0;

    private final ElapsedTime timer = new ElapsedTime();

    private void init(HardwareMap hardwareMap) {
        turret = hardwareMap.get(DcMotorEx.class, "turret");
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void setkP(double newKP) { kP = newKP; }
    public double getkP() { return kP; }

    public void setkD(double newkD) { kD = newkD; }
    public double getkD() { return kP; }

    public void resetTimer() { timer.reset(); }

    public void update(AprilTagDetection currentID) {
        double deltaTime = timer.seconds();
        timer.reset();

        if (currentID == null) {
            turret.setPower(0);
            lastError = 0;
            return;
        }

        // --------- start PD controller ----------

        double error = goalX - currentID.ftcPose.bearing;
    }
}
