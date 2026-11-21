package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class PeuebaShooter extends OpMode {
    DcMotorEx shooter;
    int Speed = 1500;
    @Override
    public void init() {
        shooter = hardwareMap.get(DcMotorEx.class,"shooter");

    }

    @Override
    public void loop() {
        if (gamepad1.dpadUpWasPressed()) {
            Speed = Speed + 50;
        }
        else if (gamepad1.dpadDownWasPressed()) {
            Speed = Speed - 50;
        }
        if (gamepad1.right_trigger_pressed) {
            shooter.setVelocity(Speed);
        }
        else {
            shooter.setVelocity(0);
        }

        telemetry.addData("velocidad",Speed);
    }
}
